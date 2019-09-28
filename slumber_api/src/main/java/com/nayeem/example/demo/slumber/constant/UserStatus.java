package com.nayeem.example.demo.slumber.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserStatus {
    PENDING(1),
    ACTIVE(2),
    DEACTIVATE(3);

    private final int value;
    //TODO HasValid Check UserStatus
}
