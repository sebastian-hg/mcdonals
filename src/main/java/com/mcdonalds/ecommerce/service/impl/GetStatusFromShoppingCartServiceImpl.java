package com.mcdonalds.ecommerce.service.impl;

import com.mcdonalds.ecommerce.exception.ShoppingCartNoExistException;
import com.mcdonalds.ecommerce.model.ShoppingCart;
import com.mcdonalds.ecommerce.repository.ShoppingCartRepository;
import com.mcdonalds.ecommerce.service.GetStatusFromShoppingCartService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@AllArgsConstructor
@Log4j2
@Service
public class GetStatusFromShoppingCartServiceImpl implements GetStatusFromShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;

    @Override
    public Mono<ShoppingCart> execute(Long shoppingCartId) {
        var shoppingCartResult= shoppingCartRepository.findById(shoppingCartId);
        return Mono.just(shoppingCartResult)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .switchIfEmpty(Mono.error(ShoppingCartNoExistException::new));
    }
}
