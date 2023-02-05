package com.mcdonalds.ecommerce.service.impl;

import com.mcdonalds.ecommerce.exception.ProductAlreadyExistException;
import com.mcdonalds.ecommerce.exception.ProductNoAvailableExistException;
import com.mcdonalds.ecommerce.model.ShoppingCart;
import com.mcdonalds.ecommerce.model.ShoppingCartProduct;
import com.mcdonalds.ecommerce.model.dto.request.AddProductRequest;
import com.mcdonalds.ecommerce.repository.ProductRepository;
import com.mcdonalds.ecommerce.repository.ShoppingCartProductRepository;
import com.mcdonalds.ecommerce.repository.ShoppingCartRepository;
import com.mcdonalds.ecommerce.service.AddProductToShoppingCartService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.Optional;

@AllArgsConstructor
@Log4j2
@Service
public class AddProductToShoppingCartServiceImpl implements AddProductToShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductRepository productRepository;
    private final ShoppingCartProductRepository shoppingCartProductRepository;

    @Override
    public Mono<ShoppingCart> execute(Long shoppingCartId, AddProductRequest addProductRequest) {
        return Mono.just(addProductRequest)
                .map(addProductRequest1 -> shoppingCartProductRepository.existsByShoppingCartIdAndProductId(shoppingCartId,addProductRequest1.getProductId()))
                .filter(Boolean.FALSE::equals)
                .switchIfEmpty(Mono.error(ProductAlreadyExistException::new))
                .map(addProductRequest1 -> shoppingCartRepository.findById(shoppingCartId))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(shoppingCart -> {
                    var product = productRepository.findById(addProductRequest.getProductId())
                            .orElseThrow();
                    var detail = ShoppingCartProduct.builder()
                            .shoppingCart(shoppingCart)
                            .product(product)
                            .numberOfProducts(addProductRequest.getNumberOfProduct())
                            .build();
                    shoppingCartProductRepository.save(detail);
                    var newProducts = detail.getNumberOfProducts() + shoppingCart.getNumberProducts();
                    var purchaseProduct = product.getPrice();
                    BigDecimal itemsBigDecimal = BigDecimal.valueOf(addProductRequest.getNumberOfProduct());
                    var totalLine = purchaseProduct.multiply(itemsBigDecimal);
                    shoppingCart.setNumberProducts(newProducts);
                    var totalPurchase = shoppingCart.getTotalPurchase().add(totalLine);
                    shoppingCart.setTotalPurchase(totalPurchase);
                    return shoppingCart;
                })
                .map(shoppingCartRepository::save)
                .switchIfEmpty(Mono.error(ProductNoAvailableExistException::new));
    }
}
