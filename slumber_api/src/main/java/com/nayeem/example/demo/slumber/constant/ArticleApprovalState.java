package com.nayeem.example.demo.slumber.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ArticleApprovalState {
    PENDING(1),
    APPROVED(2);

    private final int value;
    //TODO HasValid Check ArticleApprovalState
}