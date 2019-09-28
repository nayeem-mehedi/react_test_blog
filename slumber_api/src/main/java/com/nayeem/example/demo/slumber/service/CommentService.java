package com.nayeem.example.demo.slumber.service;

import com.nayeem.example.demo.slumber.dto.CommentRequest;
import com.nayeem.example.demo.slumber.entity.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> getAllCommentsByArticleId(Long articleId);

    Comment saveComment(CommentRequest commentRequest);

    Comment updateComment(CommentRequest commentRequest);

    Comment deleteComment(Long id, Long userId);

    Comment getComment(Long id);
//
//    Comment getCommentByIdAndUserId(Long id, Long userId);
}
