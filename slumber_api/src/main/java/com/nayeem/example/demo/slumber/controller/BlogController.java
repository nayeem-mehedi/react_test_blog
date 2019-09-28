package com.nayeem.example.demo.slumber.controller;

import com.nayeem.example.demo.slumber.dto.*;
import com.nayeem.example.demo.slumber.entity.Article;
import com.nayeem.example.demo.slumber.entity.Comment;
import com.nayeem.example.demo.slumber.entity.User;
import com.nayeem.example.demo.slumber.service.ArticleService;
import com.nayeem.example.demo.slumber.service.CommentService;
import com.nayeem.example.demo.slumber.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;

@RestController
@RequestMapping(value = "/articles")
@CrossOrigin(origins = {"http://localhost:3000", "http://localhost:4200"})
public class BlogController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ArticleService articleService;
    @Autowired
    CommentService commentService;
    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyRole('ADMIN','BLOGGER')")
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getAllApprovedArticles() {
        List<Article> articles = articleService.getAllApprovedArticles();
        return ResponseEntity.ok(ArticlesResponse.builder().articles(articles).page(1).size(articles.size()).total(articles.size()).build());
    }

    @PreAuthorize("hasAnyRole('ADMIN','BLOGGER')")
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<?> getApprovedArticleById(@PathVariable String id) {
        Article article = null;
        if (StringUtils.isNumeric(id)) {
            article = articleService.getApprovedArticleById(Long.parseLong(id));
        }
        return ResponseEntity.ok(ArticleResponse.builder().article(article).build());
    }

    @PreAuthorize("hasAnyRole('BLOGGER')")
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> publishArticle(@RequestBody ArticlePublishRequest articlePublishRequest) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User user = userService.getUserByUsername(username);

        Article article = null;
        if (articlePublishRequest != null) {
            articlePublishRequest.setUser(user);
            article = articleService.saveArticle(articlePublishRequest);
        }
        return ResponseEntity.ok(ArticleResponse.builder().article(article).build());
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/articles/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable String id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User user = userService.getUserByUsername(username);

        Article article = null;
        if (StringUtils.isNumeric(id)) {
            article = articleService.deleteArticle(Long.parseLong(id),user.getId());
        }
        return ResponseEntity.ok(ArticleResponse.builder().article(article).build());
    }

    //COMMENTS

    @PreAuthorize("hasAnyRole('BLOGGER')")
    @RequestMapping(method = RequestMethod.GET, value = "/{id}/comments")
    public ResponseEntity<?> getArticleComments(@PathVariable String id) {

        if (StringUtils.isNumeric(id)) {
            List<Comment> comments = null;
            comments = commentService.getAllCommentsByArticleId(Long.parseLong(id));
            return ResponseEntity.ok(CommentsResponse.builder().comments(comments).page(1).size(comments.size()).total(comments.size()).build());
        }else{
            return ResponseEntity.badRequest().body(ErrorResponse.builder().code(HttpStatus.BAD_REQUEST.value()).message("Invalid  parameter").cause("id").build());
        }

    }

    @PreAuthorize("hasAnyRole('BLOGGER')")
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}/comments/{comment_id}")
    public ResponseEntity<?> getArticleCommentsById(@PathVariable String id, @PathVariable String comment_id) { //TODO Find other option to avoid {id}
        Comment comment = null;
        if (StringUtils.isNumeric(id) && StringUtils.isNumeric(comment_id)) {
            comment = commentService.getComment(Long.parseLong(comment_id));
        }
        return ResponseEntity.ok(CommentResponse.builder().comment(comment).build());
    }


    @PreAuthorize("hasAnyRole('BLOGGER')")
    @RequestMapping(method = RequestMethod.POST, value = "/{id}/comments")
    public ResponseEntity<?> publishArticleComment(@PathVariable String id, @RequestBody CommentRequest commentRequest) { //TODO Find other option to avoid {id}
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User user = userService.getUserByUsername(username);
        Comment comment = null;
        if (StringUtils.isNumeric(id) && commentRequest != null) {
            Article article = articleService.getArticleById(Long.parseLong(id));
            if(article != null){
                commentRequest.setArticle(article);
                commentRequest.setUser(user);
                comment = commentService.saveComment(commentRequest);
            }
        }
        return ResponseEntity.ok(CommentResponse.builder().comment(comment).build());
    }

    @PreAuthorize("hasAnyRole('BLOGGER')")
    @RequestMapping(method = RequestMethod.PUT, value = "/{id}/comments")
    public ResponseEntity<?> editArticleComment(@PathVariable String id, @RequestBody CommentRequest commentRequest) { //TODO Find other option to avoid {id}
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User user = userService.getUserByUsername(username);
        Comment comment = null;
        if (StringUtils.isNumeric(id) && commentRequest != null) {
            commentRequest.setUser(user);
            comment = commentService.updateComment(commentRequest);
        }
        return ResponseEntity.ok(CommentResponse.builder().comment(comment).build());
    }

    @PreAuthorize("hasAnyRole('BLOGGER')")
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}/comments/{comment_id}")
    public ResponseEntity<?> deleteArticleComment(@PathVariable String id, @PathVariable String comment_id) { //TODO Find other option to avoid {id}
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();

        User user = userService.getUserByUsername(username);
        Comment comment = null;
        if (StringUtils.isNumeric(id) && StringUtils.isNumeric(comment_id)) {
            comment = commentService.deleteComment(Long.parseLong(comment_id), user.getId());
        }
        return ResponseEntity.ok(CommentResponse.builder().comment(comment).build());
    }
}