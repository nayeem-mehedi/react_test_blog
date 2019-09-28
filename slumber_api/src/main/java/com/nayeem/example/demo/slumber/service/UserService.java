package com.nayeem.example.demo.slumber.service;

import com.nayeem.example.demo.slumber.dto.UserPostRequest;
import com.nayeem.example.demo.slumber.dto.UserApprovalRequest;
import com.nayeem.example.demo.slumber.entity.User;

import java.util.List;

public interface UserService {
    List<User> getAllBloggers();

    List<User> getAllActiveBloggers();

    List<User> getAllAdmins();

    User getUserById(Long id);

    User getActiveBloggerById(Long id);

    User getUserByUsername(String username);

    User getActiveUserByUsername(String username);

    User saveUser(UserPostRequest userPostRequest);

    User updateUser(UserApprovalRequest userApprovalRequest);
}
