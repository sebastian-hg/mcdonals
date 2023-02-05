package com.mcdonalds.ecommerce.service.impl;

import com.mcdonalds.ecommerce.model.ShoppingCart;
import com.mcdonalds.ecommerce.repository.ShoppingCartProductRepository;
import com.mcdonalds.ecommerce.repository.ShoppingCartRepository;
import com.mcdonalds.ecommerce.service.DeleteProductFromShoppingCartService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Optional;

@AllArgsConstructor
@Log4j2
@Service
public class DeleteProductFromShoppingCartServiceImpl implements DeleteProductFromShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartProductRepository shoppingCartProductRepository;

    @Override
    public Mono<ShoppingCart> execute(Long shoppingCartId, Long productId) {

        return Mono.just(shoppingCartRepository.existsById(shoppingCartId))
                .filter(Boolean.TRUE::equals)
                .map(aBoolean -> shoppingCartProductRepository.findByShoppingCartIdAndProductId(shoppingCartId, productId))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(shoppingCartProduct -> {
                    var shoppingCart = shoppingCartRepository.findById(shoppingCartId).orElseThrow();
                    var items = shoppingCartProduct.getNumberOfProducts();
                    var numberProduct = shoppingCart.getNumberProducts() - items;
                    shoppingCart.setNumberProducts(numberProduct);
                    var priceProduct = shoppingCartProduct.getProduct().getPrice();
                    var totalAmount = BigDecimal.valueOf(items).setScale(2).multiply(priceProduct.setScale(2));
                    var shoppingCartTotalAmount= shoppingCart.getTotalPurchase();
                    shoppingCart.setTotalPurchase(shoppingCartTotalAmount.subtract(totalAmount));
                    return shoppingCart;
                })
                .map(shoppingCartRepository::save)
                .map(shoppingCart -> {
                    shoppingCartProductRepository.deleteByShoppingCartIdAndProductId(shoppingCart.getId(),productId);
                    return shoppingCart;
                });
    }

}
