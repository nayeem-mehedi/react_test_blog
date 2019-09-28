package com.nayeem.example.demo.slumber.controller;

import com.nayeem.example.demo.slumber.dto.UserListResponse;
import com.nayeem.example.demo.slumber.dto.UserResponse;
import com.nayeem.example.demo.slumber.entity.User;
import com.nayeem.example.demo.slumber.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/bloggers")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200"})
public class UserController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyRole('BLOGGER')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAllActiveUsers() {
        List<User> bloggers = userService.getAllActiveBloggers().stream().map(user -> {
            user.setArticles(null);
            return user;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(UserListResponse.builder().users(bloggers).build());
    }

    @PreAuthorize("hasAnyRole('BLOGGER')")
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<?> getUser(@PathVariable String id) {
        User user = null;
        if (StringUtils.isNumeric(id)) {
            user = userService.getActiveBloggerById(Long.parseLong(id));
        }
        return ResponseEntity.ok(UserResponse.builder().user(user).build());
    }

    @PreAuthorize("hasAnyRole('BLOGGER')")
    @RequestMapping(method = RequestMethod.GET, value = "/me")
    public ResponseEntity<?> getSelf() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User user = userService.getUserByUsername(username);

        return ResponseEntity.ok(UserResponse.builder().user(user).build());
    }
}
