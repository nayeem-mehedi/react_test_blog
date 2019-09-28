package com.nayeem.example.demo.slumber.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ArticleApprovalChangeRequest implements Serializable {
    private static final long serialVersionUID = -3189119934923466637L;
    @NotNull
    private Long id;
    private Integer approval_state;
}
