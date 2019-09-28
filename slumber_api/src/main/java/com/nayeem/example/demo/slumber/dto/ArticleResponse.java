package com.nayeem.example.demo.slumber.dto;

import com.nayeem.example.demo.slumber.entity.Article;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class ArticleResponse implements Serializable {
    private static final long serialVersionUID = 4592692690226355997L;
    Article article;
}
