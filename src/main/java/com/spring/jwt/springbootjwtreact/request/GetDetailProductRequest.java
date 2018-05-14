package com.spring.jwt.springbootjwtreact.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class GetDetailProductRequest {

    @NotBlank
    private String productId;
}
