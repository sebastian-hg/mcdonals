package com.mcdonalds.ecommerce.service.impl;

import com.mcdonalds.ecommerce.model.ShoppingCart;
import com.mcdonalds.ecommerce.repository.ShoppingCartRepository;
import com.mcdonalds.ecommerce.service.GetShoppingCartService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Log4j2
@Service
public class GetShoppingCartServiceImpl implements GetShoppingCartService {
    private final ShoppingCartRepository repository;


    @Override
    public Mono<ShoppingCart> execute(Long shoppingCardId) {
        return Mono.just(repository.findById(shoppingCardId).orElseThrow());
    }
}
