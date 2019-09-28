package com.nayeem.example.demo.slumber.dao;

import com.nayeem.example.demo.slumber.entity.Reaction;

import java.util.List;

public interface ReactionDao {
    List<Reaction> getAllLikesByArticleId(Long articleId);

    Reaction getReactionById(Long id);

    Reaction getReactionByArticleIdUserId(Long articleId, Long userId);

    Reaction saveLike(Reaction reaction);

    Reaction deleteLike(Long id);
}
