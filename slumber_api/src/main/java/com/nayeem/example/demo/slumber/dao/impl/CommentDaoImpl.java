package com.nayeem.example.demo.slumber.dao.impl;

import com.nayeem.example.demo.slumber.dao.CommentDao;
import com.nayeem.example.demo.slumber.entity.Comment;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository
public class CommentDaoImpl implements CommentDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Comment> getAllCommentsByArticleId(Long articleId) {
        String hql = "FROM Comment C Where C.article_id = :articleId";
        return entityManager.createQuery(hql, Comment.class).setParameter("articleId", articleId).getResultList();
    }

    @Override
    public Comment saveComment(Comment comment) {
        if(comment.getId() == null){
            entityManager.persist(comment);
        }else{
            entityManager.merge(comment);
        }
        return comment;
    }

    @Override
    public Comment deleteComment(Comment comment) {
        entityManager.remove(comment);
        return comment;
    }

    @Override
    public Comment getComment(Long id) {
        return entityManager.find(Comment.class, id);
    }

    @Override
    public Comment getCommentByIdAndUserId(Long id, Long userId) {
        String hql ="FROM Comment C Where C.user_id = :userId";
        return entityManager.createQuery(hql, Comment.class).setParameter("userId", userId).getSingleResult();
    }
}
