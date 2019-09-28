package com.nayeem.example.demo.slumber.service.impl;

import com.nayeem.example.demo.slumber.constant.UserStatus;
import com.nayeem.example.demo.slumber.dao.UserDao;
import com.nayeem.example.demo.slumber.dto.UserApprovalRequest;
import com.nayeem.example.demo.slumber.dto.UserPostRequest;
import com.nayeem.example.demo.slumber.entity.User;
import com.nayeem.example.demo.slumber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService, UserService {

    @Autowired
    UserDao userDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    public User saveUser(UserPostRequest userPostRequest) {
        if (userPostRequest != null) {
            User user = new User();
            user.setUsername(userPostRequest.getUsername());
            user.setPassword(passwordEncoder.encode(userPostRequest.getPassword()));
            user.setName(userPostRequest.getName());
            user.setRole(userPostRequest.getRole());
            if (userPostRequest.getRole().equals("ADMIN")) {
                user.setStatus(UserStatus.ACTIVE.getValue());
            } else {
                user.setStatus(UserStatus.PENDING.getValue());
            }
            user.setCreated(new Date());
            user.setUpdated(new Date());

            userDao.saveUser(user);
            return user;
        }
        return null;
    }

    public User updateUser(UserApprovalRequest userApprovalRequest) {
        if (userApprovalRequest != null && userApprovalRequest.getId() != null) {
            User user = userDao.getUserById(userApprovalRequest.getId());
            if (user != null) {
                if (userApprovalRequest.getStatus() != null) {
                    user.setStatus(userApprovalRequest.getStatus());
                }
                user.setUpdated(new Date());
                userDao.saveUser(user);
                return user;
            }
        }
        return null;
    }

    @Override
    public List<User> getAllBloggers() {
        return userDao.getAllBloggers();
    }

    @Override
    public List<User> getAllActiveBloggers() {
        return userDao.getAllActiveBloggers();
    }

    @Override
    public List<User> getAllAdmins() {
        return userDao.getAllAdmins();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getActiveUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthority(user.getRole()));
    }

    @Override
    public User getUserByUsername(String username) {
        User user = userDao.getUserByUsername(username);
        if (user != null) {
            return user;
        }
        return null;
    }

    @Override
    public User getActiveUserByUsername(String username) {
        User user = userDao.getActiveUserByUsername(username);
        if (user != null) {
            return user;
        }
        return null;
    }

    private Set<SimpleGrantedAuthority> getAuthority(String role) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
        return authorities;
    }

    @Override
    public User getUserById(Long id) {
        if (id != null) {
            return userDao.getUserById(id);
        }
        return null;
    }

    @Override
    public User getActiveBloggerById(Long id) {
        return userDao.getActiveBloggerById(id);
    }


}
