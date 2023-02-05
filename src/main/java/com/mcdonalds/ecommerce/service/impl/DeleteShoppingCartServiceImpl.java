package com.mcdonalds.ecommerce.service.impl;

import com.mcdonalds.ecommerce.exception.ShoppingCartNoExistException;
import com.mcdonalds.ecommerce.repository.ShoppingCartDiscountRepository;
import com.mcdonalds.ecommerce.repository.ShoppingCartProductRepository;
import com.mcdonalds.ecommerce.repository.ShoppingCartRepository;
import com.mcdonalds.ecommerce.service.DeleteShoppingCartService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;


@AllArgsConstructor
@Log4j2
@Service
public class DeleteShoppingCartServiceImpl implements DeleteShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartProductRepository shoppingCartProductRepository;
    private final ShoppingCartDiscountRepository shoppingCartDiscountRepository;


    @Override
    public Mono<Boolean> execute(Long id) {
        return Mono.just(shoppingCartRepository.findById(id))
                .filter(Optional::isPresent)
                .switchIfEmpty(Mono.error(ShoppingCartNoExistException::new))
                .map(Optional::get)
                .flatMap(shoppingCart -> {
                    shoppingCartProductRepository.deleteAllByShoppingCartId(shoppingCart.getId());
                    shoppingCartDiscountRepository.deleteAllByShoppingCartId(shoppingCart.getId());
                    shoppingCartRepository.delete(shoppingCart);
                    return Mono.just(Boolean.TRUE);
                });
    }
}
