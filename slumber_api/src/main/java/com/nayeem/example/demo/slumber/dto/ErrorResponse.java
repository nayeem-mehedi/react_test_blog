package com.nayeem.example.demo.slumber.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
    private Integer code;
    private String message;
    private String cause;
}
