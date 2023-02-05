package com.mcdonalds.ecommerce.service;

import com.mcdonalds.ecommerce.model.Product;
import com.mcdonalds.ecommerce.model.ShoppingCart;
import com.mcdonalds.ecommerce.model.ShoppingCartProduct;
import com.mcdonalds.ecommerce.repository.ShoppingCartProductRepository;
import com.mcdonalds.ecommerce.repository.ShoppingCartRepository;
import com.mcdonalds.ecommerce.service.impl.DeleteProductFromShoppingCartServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
public class DeleteProductFromShoppingCartServiceImplTest {
    @Mock
    private ShoppingCartRepository shoppingCartRepository;
    @Mock
    private ShoppingCartProductRepository shoppingCartProductRepository;

    @InjectMocks
    private DeleteProductFromShoppingCartServiceImpl service;

    private Long idProduct;
    private Long idShoppingCart;
    private ShoppingCart shoppingCart2;
    private ShoppingCart expected;
    private Mono<ShoppingCart> response;

    @Test
    void givenRequestWhenExecuteThemIsOg() {
        givenRequest();
        givenRepository();
        givenResponse();
        whenExecute();
        thenIsOk();
    }

    private void givenRequest() {
        idProduct = 1L;
        idShoppingCart= 1L;
    }

    private void givenRepository() {
        Boolean aBoolean = Boolean.TRUE;
        ShoppingCart shoppingCart = ShoppingCart.builder()
                .id(1L)
                .isVip(Boolean.TRUE)
                .creationTime(LocalTime.MIN)
                .creationDate(LocalDate.MIN)
                .numberProducts(4)
                .totalPurchase(BigDecimal.valueOf(500))
                .build();
        shoppingCart2 = ShoppingCart.builder()
                .id(1L)
                .isVip(Boolean.TRUE)
                .creationTime(LocalTime.MIN)
                .creationDate(LocalDate.MIN)
                .numberProducts(0)
                .totalPurchase(BigDecimal.valueOf(0))
                .build();
        ShoppingCartProduct shoppingCartProduct = ShoppingCartProduct.builder()
                .product(Product.builder()
                        .price(BigDecimal.valueOf(100))
                        .isActive(Boolean.TRUE)
                        .description("Burger")
                        .build())
                .shoppingCart(shoppingCart)
                .numberOfProducts(4)
                .build();

        Mockito.when(shoppingCartRepository.existsById(any(Long.class))).thenReturn(true);
        Mockito.when(shoppingCartProductRepository.findByShoppingCartIdAndProductId(any(Long.class), any(Long.class))).thenReturn(Optional.of(shoppingCartProduct));
        Mockito.when(shoppingCartRepository.findById(any(Long.class))).thenReturn(Optional.of(shoppingCart));
        Mockito.when(shoppingCartRepository.save(any(ShoppingCart.class))).thenReturn(shoppingCart2);
        Mockito.doNothing().when(shoppingCartProductRepository).deleteAllByShoppingCartId(any(Long.class));

    }

    private void givenResponse() {
        expected = shoppingCart2 = ShoppingCart.builder()
                .id(1L)
                .isVip(Boolean.TRUE)
                .creationTime(LocalTime.MIN)
                .creationDate(LocalDate.MIN)
                .numberProducts(0)
                .totalPurchase(BigDecimal.valueOf(0))
                .build();
    }

    private void whenExecute() {
        response = service.execute(idShoppingCart,idProduct);
    }

    private void thenIsOk() {
        StepVerifier.create(response)
                .expectNextMatches(shoppingCart1 -> shoppingCart1.equals(expected))
                .expectComplete()
                .verify();
        Mockito.verify(shoppingCartRepository).existsById(any(Long.class));
        Mockito.verify(shoppingCartProductRepository).findByShoppingCartIdAndProductId(any(Long.class), any(Long.class));
        Mockito.verify(shoppingCartRepository).findById(any(Long.class));
        Mockito.verify(shoppingCartRepository).save(any(ShoppingCart.class));
        Mockito.verify(shoppingCartProductRepository).deleteAllByShoppingCartId(any(Long.class));

    }

}
