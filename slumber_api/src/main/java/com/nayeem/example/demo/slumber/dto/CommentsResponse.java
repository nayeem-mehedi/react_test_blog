package com.nayeem.example.demo.slumber.dto;

import com.nayeem.example.demo.slumber.entity.Comment;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class CommentsResponse implements Serializable {
    private static final long serialVersionUID = 7904252994013614022L;
    int page;
    int total;
    int size;
    List<Comment> comments;
}