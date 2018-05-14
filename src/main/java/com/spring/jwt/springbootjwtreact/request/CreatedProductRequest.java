package com.spring.jwt.springbootjwtreact.request;

import com.spring.jwt.springbootjwtreact.entity.Category;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
public class CreatedProductRequest {

    @NotBlank
    private String name;

    @NotNull
    @Max(100)
    @Min(1)
    private int stock;

    @NotNull
    private int price;

    @NotNull
    private Category category;

}
