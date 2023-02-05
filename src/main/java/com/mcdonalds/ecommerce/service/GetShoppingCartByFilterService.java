package com.mcdonalds.ecommerce.service;

import com.mcdonalds.ecommerce.model.ShoppingCart;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;

public interface GetShoppingCartByFilterService {
    Mono<List<ShoppingCart>> execute(Integer documentId, LocalDate dateStar, LocalDate dateEnd, String typeOrder);
}
