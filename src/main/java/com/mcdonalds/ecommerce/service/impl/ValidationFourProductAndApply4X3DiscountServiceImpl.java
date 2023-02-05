package com.mcdonalds.ecommerce.service.impl;

import com.mcdonalds.ecommerce.model.ShoppingCart;
import com.mcdonalds.ecommerce.model.ShoppingCartDiscount;
import com.mcdonalds.ecommerce.model.ShoppingCartProduct;
import com.mcdonalds.ecommerce.repository.ShoppingCartDiscountRepository;
import com.mcdonalds.ecommerce.repository.ShoppingCartProductRepository;
import com.mcdonalds.ecommerce.repository.ShoppingCartRepository;
import com.mcdonalds.ecommerce.service.ValidationFourProductAndApply4X3DiscountService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Log4j2
@Service
public class ValidationFourProductAndApply4X3DiscountServiceImpl implements ValidationFourProductAndApply4X3DiscountService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartProductRepository shoppingCartProductRepository;
    private final ShoppingCartDiscountRepository shoppingCartDiscountRepository;
    private static final String DESCRIPTION_DISCOUNT = "se aplico descuento 4X3 para producto ";

    @Override
    public Mono<ShoppingCart> execute(Long shoppingCartId) {
        var shoppingCart = shoppingCartRepository.findById(shoppingCartId).orElseThrow();
        var shoppingCartProductList = shoppingCartProductRepository.findAllByShoppingCartId(shoppingCartId);
        return Mono.just(shoppingCartProductList)
                .map(shoppingCartProductList1 -> {
                    for (ShoppingCartProduct shoppingCartProduct : shoppingCartProductList1) {
                        var numberOfProducts = shoppingCartProduct.getNumberOfProducts();
                        if (numberOfProducts > 3) {
                            var shoppingCartDiscount = ShoppingCartDiscount.builder()
                                    .description(DESCRIPTION_DISCOUNT + shoppingCartProduct.getProduct().getDescription())
                                    .totalDiscount(shoppingCartProduct.getProduct().getPrice())
                                    .shoppingCart(shoppingCart)
                                    .build();
                            shoppingCartDiscountRepository.save(shoppingCartDiscount);
                        }
                    }
                    return shoppingCart;
                });
    }
}
