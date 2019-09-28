package com.nayeem.example.demo.slumber.dto;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserApprovalRequest implements Serializable {
    private static final long serialVersionUID = -7245780030953981278L;
    @NonNull
    private Long id;
    private Integer status;
}
