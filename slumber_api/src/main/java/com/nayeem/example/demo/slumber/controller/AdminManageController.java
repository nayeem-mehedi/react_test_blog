package com.nayeem.example.demo.slumber.controller;

import com.nayeem.example.demo.slumber.dto.*;
import com.nayeem.example.demo.slumber.entity.Article;
import com.nayeem.example.demo.slumber.entity.User;
import com.nayeem.example.demo.slumber.service.ArticleService;
import com.nayeem.example.demo.slumber.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@PreAuthorize("hasRole('ADMIN')")
@RestController
@RequestMapping(value = "/manage")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200"})
public class AdminManageController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ArticleService articleService;

    @Autowired
    private UserService userService;

    //ADMIN

    @RequestMapping(method = RequestMethod.GET, value = "/admins")
    public ResponseEntity<?> getAllAdmins() {
        return ResponseEntity.ok(UserListResponse.builder().users(userService.getAllAdmins()).build());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/admins")
    public ResponseEntity<?> saveAdmin(@RequestBody UserPostRequest userPostRequest) {
        if(userPostRequest != null){
            userPostRequest.setRole("ADMIN");
            userService.saveUser(userPostRequest);
        }
        return ResponseEntity.ok(UserListResponse.builder().users(userService.getAllAdmins()).build());
    }

    //BLOGGERS

    @RequestMapping(method = RequestMethod.GET, value = "/bloggers") //only Users no articles
    public ResponseEntity<?> getAllBloggers() {
        List<User> bloggers = userService.getAllBloggers().stream().map(user -> {
            user.setArticles(null);
            return user;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(UserListResponse.builder().users(bloggers).build());
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/bloggers", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> updateUser(@RequestBody UserApprovalRequest userApprovalRequest) {
        User updatedUser = null;

        if (userApprovalRequest != null) {
            updatedUser = userService.updateUser(userApprovalRequest);
        }
        return ResponseEntity.ok(UserResponse.builder().user(updatedUser).build());
    }

    @PreAuthorize("hasAnyRole('ADMIN','BLOGGER')")
    @RequestMapping(method = RequestMethod.GET, value = "/bloggers/{id}")
    public ResponseEntity<?> getUser(@PathVariable String id){
        User user = null;
        if(StringUtils.isNumeric(id)) {
            user = userService.getUserById(Long.parseLong(id));
        }
        return ResponseEntity.ok(UserResponse.builder().user(user).build());
    }

    //ARTICLES

    @RequestMapping(method = RequestMethod.GET, value = "/articles")
    public ResponseEntity<?> getAllPendingArticles() {
        List<Article> articles = articleService.getAllPendingArticles();
        return ResponseEntity.ok(ArticlesResponse.builder().articles(articles).page(1).size(articles.size()).total(articles.size()).build());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/articles/{id}")
    public ResponseEntity<?> getArticleById(@PathVariable String id) {
        Article article = null;
        if (StringUtils.isNumeric(id)) {
            article = articleService.getArticleById(Long.parseLong(id));
        }
        return ResponseEntity.ok(ArticleResponse.builder().article(article).build());
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/articles", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<?> changeArticleState(@RequestBody ArticleApprovalChangeRequest articleApprovalChangeRequest) {
        Article article = null;
        if (articleApprovalChangeRequest != null && articleApprovalChangeRequest.getId() != null) {
            article = articleService.updateArticle(articleApprovalChangeRequest);
        }
        return ResponseEntity.ok(ArticleResponse.builder().article(article).build());
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/articles/{id}")
    public ResponseEntity<?> deleteArticleById(@PathVariable String id) {
        Article article = null;
        if (StringUtils.isNumeric(id)) {
            article = articleService.deleteArticle(Long.parseLong(id));
        }
        return ResponseEntity.ok(ArticleResponse.builder().article(article).build());
    }

}
