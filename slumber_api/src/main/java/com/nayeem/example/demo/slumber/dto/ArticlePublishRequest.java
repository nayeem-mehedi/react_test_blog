package com.nayeem.example.demo.slumber.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nayeem.example.demo.slumber.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticlePublishRequest implements Serializable {
    private static final long serialVersionUID = -5216404715278945775L;
    @NotBlank
    private String title;
    @NotBlank
    private String summary;
    @NotBlank
    private String coverImage;
    @NotBlank
    private String body;
    @JsonIgnore
    private User user;
}
