package com.nayeem.example.demo.slumber.dto;

import com.nayeem.example.demo.slumber.entity.User;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class UserListResponse implements Serializable {
    private static final long serialVersionUID = 9010488316081263691L;
    int page;
    int total;
    int size;
    List<User> users;
}
