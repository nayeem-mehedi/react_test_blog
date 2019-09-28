package com.nayeem.example.demo.slumber.dao.impl;

import com.nayeem.example.demo.slumber.constant.ArticleApprovalState;
import com.nayeem.example.demo.slumber.dao.ArticleDao;
import com.nayeem.example.demo.slumber.entity.Article;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Transactional
@Repository
public class ArticleDaoImpl implements ArticleDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Article getArticleById(Long id) {
        return entityManager.find(Article.class, id);
    }

    @Override
    public Article getArticleByIdAndUserId(Long id, Long userId) {
        String hql = "FROM Article A WHERE A.id = :id AND A.author = :userId";
        return entityManager
                .createQuery(hql, Article.class)
                .setParameter("id", id)
                .setParameter("author", userId).getSingleResult();
    }

    @Override
    public Article getApprovedArticleById(Long id) {
        String hql = "FROM Article A WHERE A.id = :id AND A.approval_state = :approval_state AND A.delete_flag = :delete_flag";
        return entityManager
                .createQuery(hql, Article.class)
                .setParameter("id", id)
                .setParameter("approval_state", ArticleApprovalState.APPROVED.getValue())
                .setParameter("delete_flag", 0).getSingleResult();
    }

    @Override
    public Article saveArticle(Article article) {
        entityManager.persist(article);
        return article;
    }

    @Override
    public Article updateArticle(Article article) {
        return entityManager.merge(article);
    }

    @Override
    public List<Article> getAllArticles() {
        String hql = "FROM Article A";
        return entityManager.createQuery(hql, Article.class).getResultList();
    }

    @Override
    public List<Article> getAllArticlesByApprovalState(int approvalState) {
        String hql = "FROM Article A WHERE A.approval_state = :approval_state AND A.delete_flag = :delete_flag";
        return entityManager
                .createQuery(hql, Article.class)
                .setParameter("approval_state", approvalState)
                .setParameter("delete_flag", 0).getResultList();
    }
}
