package com.nayeem.example.demo.slumber.dto;

import com.nayeem.example.demo.slumber.entity.Article;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class ArticlesResponse implements Serializable {
    private static final long serialVersionUID = 5271106380374271408L;
    int page;
    int total;
    int size;
    List<Article> articles;
}