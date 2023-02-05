package com.mcdonalds.ecommerce.service;

import com.mcdonalds.ecommerce.model.ShoppingCart;
import com.mcdonalds.ecommerce.model.dto.request.AddProductRequest;
import reactor.core.publisher.Mono;

public interface AddProductToShoppingCartService {
    Mono<ShoppingCart> execute(Long shoppingCartId, AddProductRequest addProductRequest);
}
