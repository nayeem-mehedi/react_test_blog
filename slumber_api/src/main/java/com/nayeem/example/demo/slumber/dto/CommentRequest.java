package com.nayeem.example.demo.slumber.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nayeem.example.demo.slumber.entity.Article;
import com.nayeem.example.demo.slumber.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequest implements Serializable {
    private static final long serialVersionUID = -781830557678947312L;
    private Long id;
    private Article article;
    private String body;
    private User user;
}
