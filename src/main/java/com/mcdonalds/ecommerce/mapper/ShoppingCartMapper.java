package com.mcdonalds.ecommerce.mapper;

import com.mcdonalds.ecommerce.model.ShoppingCart;
import com.mcdonalds.ecommerce.model.dto.response.ShoppingCartResponse;
import reactor.core.publisher.Mono;

public interface ShoppingCartMapper {
    Mono<ShoppingCartResponse> execute(ShoppingCart shoppingCart);
}
