package com.nayeem.example.demo.slumber.service.impl;

import com.nayeem.example.demo.slumber.dao.ArticleDao;
import com.nayeem.example.demo.slumber.dao.CommentDao;
import com.nayeem.example.demo.slumber.dto.CommentRequest;
import com.nayeem.example.demo.slumber.entity.Article;
import com.nayeem.example.demo.slumber.entity.Comment;
import com.nayeem.example.demo.slumber.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentDao commentDao;

    @Autowired
    ArticleDao articleDao;

    @Override
    public List<Comment> getAllCommentsByArticleId(Long articleId) {
        return commentDao.getAllCommentsByArticleId(articleId);
    }

    @Override
    public Comment saveComment(CommentRequest commentRequest) {
        Comment comment = new Comment();
        comment.setBody(commentRequest.getBody());
        comment.setComment_article(commentRequest.getArticle());
        comment.setComment_user(commentRequest.getUser());
        comment.setCreated(new Date());
        comment.setUpdated(new Date());

        commentDao.saveComment(comment);
        return comment;
    }

    @Override
    public Comment updateComment(CommentRequest commentRequest) {
        Comment comment = commentDao.getCommentByIdAndUserId(commentRequest.getId(), commentRequest.getUser().getId());
        if (comment != null) {
            if (commentRequest.getBody() != null) {
                comment.setBody(commentRequest.getBody());
            }
            comment.setUpdated(new Date());
            commentDao.saveComment(comment);
        }
        return comment;
    }

    @Override
    public Comment deleteComment(Long id, Long userId) {
        Comment comment = commentDao.getCommentByIdAndUserId(id, userId);
        return commentDao.deleteComment(comment);
    }

    @Override
    public Comment getComment(Long id) {
        return commentDao.getComment(id);
    }

//    @Override
//    public Comment getCommentByIdAndUserId(Long id, Long userId) {
//        return null;
//    }
}
