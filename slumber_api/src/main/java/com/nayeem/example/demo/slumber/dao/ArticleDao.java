package com.nayeem.example.demo.slumber.dao;

import com.nayeem.example.demo.slumber.entity.Article;

import java.util.List;

public interface ArticleDao {
    Article getArticleById(Long id);

    Article getArticleByIdAndUserId(Long id, Long userId);

    Article getApprovedArticleById(Long id);

    Article saveArticle(Article article);

    Article updateArticle(Article article);

    List<Article> getAllArticles();

    List<Article> getAllArticlesByApprovalState(int approvalState);
}
