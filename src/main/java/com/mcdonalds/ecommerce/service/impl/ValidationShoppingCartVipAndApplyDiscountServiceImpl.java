package com.mcdonalds.ecommerce.service.impl;

import com.mcdonalds.ecommerce.model.ShoppingCart;
import com.mcdonalds.ecommerce.model.ShoppingCartDiscount;
import com.mcdonalds.ecommerce.repository.ShoppingCartDiscountRepository;
import com.mcdonalds.ecommerce.repository.ShoppingCartRepository;
import com.mcdonalds.ecommerce.service.ValidationShoppingCartVipAndApplyDiscountService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@AllArgsConstructor
@Log4j2
@Service
public class ValidationShoppingCartVipAndApplyDiscountServiceImpl implements ValidationShoppingCartVipAndApplyDiscountService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final ShoppingCartDiscountRepository shoppingCartDiscountRepository;
    private static final Integer MINIMUM_PRODUCT_FOR_DISCOUNT = 4;
    private static final String DISCOUNT_DESCRIPTION = "descuento por cantidad";
    private static final BigDecimal VIP_DISCOUNT = BigDecimal.valueOf(150);
    private static final BigDecimal NORMAL_DISCOUNT = BigDecimal.valueOf(100);

    @Override
    public Mono<ShoppingCart> execute(Long shoppingCartId) {
        var shoppingCart = shoppingCartRepository.findById(shoppingCartId).orElseThrow();
        return Mono.just(shoppingCart)
                .map(shoppingCart1 -> {
                    var numberProducts = shoppingCart1.getNumberProducts();
                    if (numberProducts >= MINIMUM_PRODUCT_FOR_DISCOUNT) {
                        var discount = ShoppingCartDiscount.builder()
                                .description(DISCOUNT_DESCRIPTION)
                                .shoppingCart(shoppingCart1)
                                .totalDiscount(Boolean.TRUE.equals(shoppingCart1.getIsVip())
                                        ? VIP_DISCOUNT
                                        : NORMAL_DISCOUNT)
                                .build();
                        shoppingCartDiscountRepository.save(discount);
                    }
                    return shoppingCart1;
                })
                .map(shoppingCartRepository::save);
    }
}
