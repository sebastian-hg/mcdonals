package com.mcdonalds.ecommerce.service;

import com.mcdonalds.ecommerce.model.ShoppingCart;
import reactor.core.publisher.Mono;

public interface DeleteProductFromShoppingCartService {
    Mono<ShoppingCart> execute(Long shoppingCartId, Long productId);
}
