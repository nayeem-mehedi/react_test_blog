package com.nayeem.example.demo.slumber.dao.impl;

import com.nayeem.example.demo.slumber.dao.ReactionDao;
import com.nayeem.example.demo.slumber.entity.Reaction;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

public class ReactionDaoImpl implements ReactionDao {

    @Autowired
    EntityManager entityManager;

    @Override
    public List<Reaction> getAllLikesByArticleId(Long articleId) {
        String hql = "FROM Reaction R WHERE R.article_id = :articleId";
        return entityManager
                .createQuery(hql, Reaction.class)
                .setParameter("articleId", articleId).getResultList();
    }

    @Override
    public Reaction getReactionById(Long id) {
        return entityManager.find(Reaction.class, id);
    }

    @Override
    public Reaction getReactionByArticleIdUserId(Long articleId, Long userId) {
        String hql = "FROM Reaction R WHERE R.article_id = :articleId AND R.user_id = :userId";
        return entityManager
                .createQuery(hql, Reaction.class)
                .setParameter("articleId", articleId)
                .setParameter("userId", userId).getSingleResult();
    }

    @Override
    public Reaction saveLike(Reaction reaction) {
        if (reaction.getId() != null) {
            entityManager.persist(reaction);
        } else {
            entityManager.merge(reaction);
        }
        return reaction;
    }

    @Override
    public Reaction deleteLike(Long id) {
        Reaction reaction = entityManager.find(Reaction.class, id);
        entityManager.remove(reaction);
        return reaction;
    }
}
