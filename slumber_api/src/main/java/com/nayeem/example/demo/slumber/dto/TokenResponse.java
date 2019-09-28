package com.nayeem.example.demo.slumber.dto;

import com.nayeem.example.demo.slumber.entity.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class TokenResponse implements Serializable {

    private static final long serialVersionUID = 3897128892578188742L;
    private final String access_token;
    //private final String refresh_token;
    private User user;

    public TokenResponse(String access_token, String refresh_token) {
        this.access_token = access_token;
        //this.refresh_token = refresh_token;
    }

    public TokenResponse(String access_token, User user) {
        this.access_token = access_token;
        //this.refresh_token = refresh_token;
        this.user = user;
    }
}
