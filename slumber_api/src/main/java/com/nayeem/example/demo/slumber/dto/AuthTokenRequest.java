package com.nayeem.example.demo.slumber.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthTokenRequest implements Serializable {
    private static final long serialVersionUID = -1927498729215526216L;
    private String username;
    private String password;
}
