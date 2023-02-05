package com.mcdonalds.ecommerce.service;

import com.mcdonalds.ecommerce.model.Client;
import com.mcdonalds.ecommerce.model.ShoppingCart;
import com.mcdonalds.ecommerce.repository.ShoppingCartDiscountRepository;
import com.mcdonalds.ecommerce.repository.ShoppingCartProductRepository;
import com.mcdonalds.ecommerce.repository.ShoppingCartRepository;
import com.mcdonalds.ecommerce.service.impl.DeleteShoppingCartServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;


@ExtendWith(SpringExtension.class)
public class DeleteShoppingCartServiceImplTest {

    @Mock
    private ShoppingCartRepository shoppingCartRepository;
    @Mock
    private ShoppingCartProductRepository shoppingCartProductRepository;
    @Mock
    private ShoppingCartDiscountRepository shoppingCartDiscountRepository;

    @InjectMocks
    private DeleteShoppingCartServiceImpl service;

    private Long idDelete;
    private ShoppingCart shoppingCart;
    private Boolean expected;
    private Mono<Boolean> response;


    @Test
    void givenRequestWhenExecuteThenIsOk() {
        givenRequest();
        givenRepository();
        givenResponse();
        whenExecute();
        thenIsOK();
    }

    private void givenRequest() {
        idDelete = 1L;

    }

    private void givenRepository() {
        shoppingCart = ShoppingCart.builder()
                .id(1L)
                .isVip(Boolean.TRUE)
                .client(Client.builder()
                        .id(1L)
                        .nameCompleted("Jose")
                        .build())
                .numberProducts(9)
                .build();
        Mockito.when(shoppingCartRepository.findById(any(Long.class))).thenReturn(Optional.of(shoppingCart));
        Mockito.doNothing().when(shoppingCartProductRepository).deleteAllByShoppingCartId(any(Long.class));
        Mockito.doNothing().when(shoppingCartDiscountRepository).deleteAllByShoppingCartId(any(Long.class));
        Mockito.doNothing().when(shoppingCartRepository).delete(any(ShoppingCart.class));
    }

    private void givenResponse() {
        expected = Boolean.TRUE;
    }

    private void whenExecute() {
        response = service.execute(any(Long.class));
    }

    private void thenIsOK() {
        StepVerifier.create(response)
                .expectNextMatches(response1 -> response1.equals(expected))
                .expectComplete()
                .verify();
        Mockito.verify(shoppingCartRepository).findById(any(Long.class));
        Mockito.verify(shoppingCartProductRepository).deleteAllByShoppingCartId(any(Long.class));
        Mockito.verify(shoppingCartDiscountRepository).deleteAllByShoppingCartId(any(Long.class));
        Mockito.verify(shoppingCartRepository).delete(any(ShoppingCart.class));
        }


}
