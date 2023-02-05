package com.mcdonalds.ecommerce.service;

import com.mcdonalds.ecommerce.model.ShoppingCart;
import reactor.core.publisher.Mono;

public interface ValidationFourProductAndApply4X3DiscountService {
    Mono<ShoppingCart>execute(Long shoppingCartId);
}
