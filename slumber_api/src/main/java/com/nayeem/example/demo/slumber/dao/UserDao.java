package com.nayeem.example.demo.slumber.dao;

import com.nayeem.example.demo.slumber.entity.User;

import java.util.List;

public interface UserDao {
    User getUserById(Long id);

    User getActiveBloggerById(Long id);

    User getUserByUsername(String username);

    User getActiveUserByUsername(String username);

    User saveUser(User user);

    List<User> getAllBloggers();

    List<User> getAllActiveBloggers();

    List<User> getAllAdmins();
}
