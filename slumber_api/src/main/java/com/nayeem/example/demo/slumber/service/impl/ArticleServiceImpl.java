package com.nayeem.example.demo.slumber.service.impl;

import com.nayeem.example.demo.slumber.constant.ArticleApprovalState;
import com.nayeem.example.demo.slumber.dao.ArticleDao;
import com.nayeem.example.demo.slumber.dto.ArticleApprovalChangeRequest;
import com.nayeem.example.demo.slumber.dto.ArticlePublishRequest;
import com.nayeem.example.demo.slumber.entity.Article;
import com.nayeem.example.demo.slumber.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleDao articleDao;

    //TODO Articles pagable

    @Override
    public List<Article> getAllArticles() {
        return articleDao.getAllArticles();
    }

    @Override
    public List<Article> getAllApprovedArticles() {
        return articleDao.getAllArticlesByApprovalState(ArticleApprovalState.APPROVED.getValue());
    }

    @Override
    public List<Article> getAllPendingArticles() {
        return articleDao.getAllArticlesByApprovalState(ArticleApprovalState.PENDING.getValue());
    }

    @Override
    public Article getApprovedArticleById(Long id) {
        return articleDao.getApprovedArticleById(id);
    }

    @Override
    public Article getArticleById(Long id) {
        return articleDao.getArticleById(id);
    }

    @Override
    public Article saveArticle(ArticlePublishRequest articlePublishRequest) {
        Article article = new Article();
        article.setTitle(articlePublishRequest.getTitle());
        article.setSummary(articlePublishRequest.getSummary());
        article.setCoverImage(articlePublishRequest.getCoverImage());
        article.setBody(articlePublishRequest.getBody());
        article.setApproval_state(ArticleApprovalState.PENDING.getValue());
        article.setDelete_flag(0);
        article.setUser(articlePublishRequest.getUser());
        article.setCreated(new Date());
        article.setUpdated(new Date());

        articleDao.saveArticle(article);
        return article;
    }

    @Override
    public Article deleteArticle(Long id) {
        Article article = articleDao.getArticleById(id);
        if (article != null) {
            article.setDelete_flag(1);
            articleDao.updateArticle(article);
            return article;
        }
        return null;
    }

    @Override
    public Article deleteArticle(Long id, Long userId) {
        Article article = articleDao.getArticleByIdAndUserId(id, userId);
        if (article != null) {
            article.setDelete_flag(1);
            articleDao.updateArticle(article);
            return article;
        }
        return null;
    }

    @Override
    public Article updateArticle(ArticleApprovalChangeRequest articleApprovalChangeRequest) {
        Article article = articleDao.getArticleById(articleApprovalChangeRequest.getId());
        if (article != null) {
            if (articleApprovalChangeRequest.getApproval_state() != null) {
                article.setApproval_state(articleApprovalChangeRequest.getApproval_state());
            }
            articleDao.updateArticle(article);
            return article;
        }
        return null;
    }
}
