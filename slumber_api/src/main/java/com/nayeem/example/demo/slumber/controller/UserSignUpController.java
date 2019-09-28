package com.nayeem.example.demo.slumber.controller;

import com.nayeem.example.demo.slumber.dto.UserPostRequest;
import com.nayeem.example.demo.slumber.dto.UserResponse;
import com.nayeem.example.demo.slumber.entity.User;
import com.nayeem.example.demo.slumber.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200"})
public class UserSignUpController {

    @Autowired
    UserService userService;

    @RequestMapping(method = RequestMethod.POST, value = "/signup", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> createUser(@RequestBody UserPostRequest userPostRequest) {
        User newUser = null;
        if (userPostRequest != null) {
            newUser = userService.saveUser(userPostRequest);
        }
        return ResponseEntity.ok(UserResponse.builder().user(newUser).build());
    }
}
