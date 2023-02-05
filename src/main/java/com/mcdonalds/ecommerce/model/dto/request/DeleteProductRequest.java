package com.mcdonalds.ecommerce.model.dto.request;

import lombok.Data;

@Data
public class DeleteProductRequest {
    private Long shoppingCartId;
    private Long productId;
    private Integer numberOfProduct;

}
