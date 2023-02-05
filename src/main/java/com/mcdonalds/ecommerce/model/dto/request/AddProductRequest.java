package com.mcdonalds.ecommerce.model.dto.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class AddProductRequest {
    @NotNull
    private Long productId;
    @Positive(message = "number can not be negative")
    private Integer numberOfProduct;
}
