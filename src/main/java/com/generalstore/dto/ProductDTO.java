package com.generalstore.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ProductDTO {

    public Integer productId;

    @NotBlank(message = "productName cannot be blank")
    private String productName;

    @NotNull(message = "cost cannot be null")
    private Double cost;
}
