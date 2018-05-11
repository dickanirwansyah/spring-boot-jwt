package com.spring.jwt.springbootjwtreact.request;

import javax.validation.constraints.NotBlank;

public class LoginRequest {

    @NotBlank
    private String usernameOrEmail;

    @NotBlank
    private String password;

    public String getUsernameOrEmail(){
        return usernameOrEmail;
    }

    public String getPassword(){
        return password;
    }
}
