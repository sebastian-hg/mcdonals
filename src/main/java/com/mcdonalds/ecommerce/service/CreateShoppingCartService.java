package com.mcdonalds.ecommerce.service;

import com.mcdonalds.ecommerce.model.ShoppingCart;
import com.mcdonalds.ecommerce.model.dto.request.CreateShoppingCartRequest;
import reactor.core.publisher.Mono;

public interface CreateShoppingCartService {
    Mono<ShoppingCart>execute(CreateShoppingCartRequest createShoppingCartRequest);
}
