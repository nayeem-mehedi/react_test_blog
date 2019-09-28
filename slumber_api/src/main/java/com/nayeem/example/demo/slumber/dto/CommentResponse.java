package com.nayeem.example.demo.slumber.dto;

import com.nayeem.example.demo.slumber.entity.Comment;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class CommentResponse implements Serializable {
    private static final long serialVersionUID = -6809819923149032864L;
    Comment comment;
}
