package com.nayeem.example.demo.slumber.dto;

import com.nayeem.example.demo.slumber.entity.User;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class UserResponse implements Serializable {
    private static final long serialVersionUID = -2236228740513573013L;
    User user;
}
