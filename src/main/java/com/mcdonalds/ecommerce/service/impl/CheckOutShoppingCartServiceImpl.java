package com.mcdonalds.ecommerce.service.impl;

import com.mcdonalds.ecommerce.model.ShoppingCart;
import com.mcdonalds.ecommerce.repository.ShoppingCartRepository;
import com.mcdonalds.ecommerce.service.CheckOutShoppingCartService;
import com.mcdonalds.ecommerce.service.ValidationFourProductAndApply4X3DiscountService;
import com.mcdonalds.ecommerce.service.ValidationShoppingCartVipAndApplyDiscountService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Log4j2
@Service
public class CheckOutShoppingCartServiceImpl implements CheckOutShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final ValidationShoppingCartVipAndApplyDiscountService validationShoppingCartVipAndApplyDiscountService;
    private final ValidationFourProductAndApply4X3DiscountService validationFourProductAndApply4X3DiscountService;

    @Override
    public Mono<ShoppingCart> execute(Long idShoppingCart) {
        return Mono.just(idShoppingCart)
                .flatMap(validationShoppingCartVipAndApplyDiscountService::execute)
                .flatMap(shoppingCart ->
                        validationFourProductAndApply4X3DiscountService.execute(shoppingCart.getId())
                )
                .map(shoppingCartRepository::save);
    }
}
