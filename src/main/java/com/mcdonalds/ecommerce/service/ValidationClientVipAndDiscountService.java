package com.mcdonalds.ecommerce.service;

import com.mcdonalds.ecommerce.model.ShoppingCart;
import reactor.core.publisher.Mono;

public interface ValidationClientVipAndDiscountService {
    Mono<ShoppingCart> execute(ShoppingCart shoppingCart);
}
