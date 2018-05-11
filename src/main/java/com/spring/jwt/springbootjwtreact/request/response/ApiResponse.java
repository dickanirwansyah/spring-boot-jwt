package com.spring.jwt.springbootjwtreact.request.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class ApiResponse {

    private Boolean success;
    private String message;

    public ApiResponse(Boolean success, String message){
        this.success = success;
        this.message = message;
    }
}
