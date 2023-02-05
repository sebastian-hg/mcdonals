package com.mcdonalds.ecommerce.service;

import reactor.core.publisher.Mono;

public interface DeleteShoppingCartService {
    Mono<Boolean>execute(Long id);
}
