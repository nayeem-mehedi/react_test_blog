package com.nayeem.example.demo.slumber.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ReactionType {
    LIKE(1),
    DISLIKE(2);

    private final int value;
    //TODO HasValid Check ReactionType
}