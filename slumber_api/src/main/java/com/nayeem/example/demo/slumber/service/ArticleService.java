package com.nayeem.example.demo.slumber.service;

import com.nayeem.example.demo.slumber.dto.ArticleApprovalChangeRequest;
import com.nayeem.example.demo.slumber.dto.ArticlePublishRequest;
import com.nayeem.example.demo.slumber.entity.Article;

import java.util.List;

public interface ArticleService {
    List<Article> getAllArticles();

    List<Article> getAllApprovedArticles();

    List<Article> getAllPendingArticles();

    Article getApprovedArticleById(Long id);

    Article getArticleById(Long id);

    Article saveArticle(ArticlePublishRequest articlePublishRequest);

    Article deleteArticle(Long id);

    Article deleteArticle(Long id, Long userId);

    Article updateArticle(ArticleApprovalChangeRequest articleApprovalChangeRequest);
}
