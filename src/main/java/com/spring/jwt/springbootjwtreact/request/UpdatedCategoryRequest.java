package com.spring.jwt.springbootjwtreact.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Builder
public class UpdatedCategoryRequest {

    @NotBlank
    private String categoryId;

    @NotBlank
    private String name;

    @NotBlank
    private String description;
}
