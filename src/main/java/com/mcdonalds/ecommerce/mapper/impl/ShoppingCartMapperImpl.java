package com.mcdonalds.ecommerce.mapper.impl;

import com.mcdonalds.ecommerce.mapper.ShoppingCartMapper;
import com.mcdonalds.ecommerce.model.ShoppingCart;
import com.mcdonalds.ecommerce.model.ShoppingCartDiscount;
import com.mcdonalds.ecommerce.model.ShoppingCartProduct;
import com.mcdonalds.ecommerce.model.dto.response.ShoppingCartDiscountResponse;
import com.mcdonalds.ecommerce.model.dto.response.ShoppingCartProductResponse;
import com.mcdonalds.ecommerce.model.dto.response.ShoppingCartResponse;
import com.mcdonalds.ecommerce.repository.ShoppingCartDiscountRepository;
import com.mcdonalds.ecommerce.repository.ShoppingCartProductRepository;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Builder
@Service
@AllArgsConstructor
public class ShoppingCartMapperImpl implements ShoppingCartMapper {

    private final ShoppingCartDiscountRepository shoppingCartDiscountRepository;
    private final ShoppingCartProductRepository shoppingCartProductRepository;

    @Override
    public Mono<ShoppingCartResponse> execute(ShoppingCart shoppingCart) {

        return Mono.just(shoppingCart)
                .map(shoppingCart1 -> {
                    List<ShoppingCartDiscountResponse> shoppingCartDiscountList = new ArrayList<>();
                    for (ShoppingCartDiscount discount : shoppingCartDiscountRepository.findAllByShoppingCartId(shoppingCart1.getId())) {
                        var shoppingCartDiscountResponse = ShoppingCartDiscountResponse.builder()
                                .description(discount.getDescription())
                                .totalDiscount(discount.getTotalDiscount())
                                .build();
                        shoppingCartDiscountList.add(shoppingCartDiscountResponse);
                    }
                    List<ShoppingCartProductResponse> shoppingCartProductList= new ArrayList<>();
                    for (ShoppingCartProduct product: shoppingCartProductRepository.findAllByShoppingCartId(shoppingCart1.getId())){
                        var shoppingCartDiscountResponse= ShoppingCartProductResponse.builder()
                                .description(product.getProduct().getDescription())
                                .numberOfProducts(product.getNumberOfProducts())
                                .price(product.getProduct().getPrice())
                                .build();
                        shoppingCartProductList.add(shoppingCartDiscountResponse);
                    }

                    return ShoppingCartResponse.builder()
                            .id(shoppingCart1.getId())
                            .nameCompleted(shoppingCart1.getClient().getNameCompleted())
                            .documentNational(shoppingCart1.getClient().getDocumentNational())
                            .isVip(shoppingCart1.getIsVip())
                            .numberProducts(shoppingCart1.getNumberProducts())
                            .discounts(shoppingCartDiscountList)
                            .products(shoppingCartProductList)
                            .totalPurchase(shoppingCart1.getTotalPurchase())
                            .build();
                });
    }
}
