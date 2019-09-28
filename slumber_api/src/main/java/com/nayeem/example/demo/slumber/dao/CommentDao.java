package com.nayeem.example.demo.slumber.dao;

import com.nayeem.example.demo.slumber.entity.Comment;

import java.util.List;

public interface CommentDao {
    List<Comment> getAllCommentsByArticleId(Long articleId);

    Comment saveComment(Comment comment);

    Comment deleteComment(Comment comment);

    Comment getComment(Long id);

    Comment getCommentByIdAndUserId(Long id, Long userId);
}
