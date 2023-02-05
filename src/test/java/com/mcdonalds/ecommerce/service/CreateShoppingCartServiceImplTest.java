package com.mcdonalds.ecommerce.service;

import com.mcdonalds.ecommerce.model.Client;
import com.mcdonalds.ecommerce.model.ShoppingCart;
import com.mcdonalds.ecommerce.model.dto.request.CreateShoppingCartRequest;
import com.mcdonalds.ecommerce.repository.ClientRepository;
import com.mcdonalds.ecommerce.repository.ShoppingCartRepository;
import com.mcdonalds.ecommerce.service.impl.CreateShoppingCartServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(SpringExtension.class)
public class CreateShoppingCartServiceImplTest {
    @Mock
    private ClientRepository clientRepository;
    @Mock
    private ShoppingCartRepository shoppingCartRepository;

    @InjectMocks
    private CreateShoppingCartServiceImpl service;

    private CreateShoppingCartRequest shoppingCartRequest;
    private ShoppingCart shoppingCartExpected;
    private Mono<ShoppingCart> shoppingCartResponse;

    @Test
    void givenRequestWhenExecuteThenIsOk() {
        givenRequest();
        givenRepository();
        givenResponse();
        whenExecute();
        thenIsOk();
    }

    private void givenRequest() {
        shoppingCartRequest = CreateShoppingCartRequest.builder()
                .clientId(1L)
                .isVip(Boolean.TRUE)
                .build();
    }

    private void givenRepository() {
        var client = Client.builder()
                .id(1L)
                .nameCompleted("Jose Salgado")
                .documentNational(95764679)
                .build();
        ShoppingCart shoppingCart = ShoppingCart.builder()
                .id(1L)
                .numberProducts(8)
                .client(client)
                .isVip(Boolean.TRUE)
                .creationDate(LocalDate.MIN)
                .creationTime(LocalTime.MIN)
                .build();

        Mockito.when(clientRepository.findById(any(Long.class))).thenReturn(Optional.of(client));
        Mockito.when(shoppingCartRepository.save(any(ShoppingCart.class))).thenReturn(shoppingCart);
    }

    private void givenResponse() {
        shoppingCartExpected = ShoppingCart.builder()
                .id(1L)
                .numberProducts(8)
                .client(Client.builder()
                        .id(1L)
                        .nameCompleted("Jose Salgado")
                        .documentNational(95764679)
                        .build())
                .isVip(Boolean.TRUE)
                .creationDate(LocalDate.MIN)
                .creationTime(LocalTime.MIN)
                .build();
    }

    private void whenExecute() {
        shoppingCartResponse = service.execute(shoppingCartRequest);
    }

    private void thenIsOk() {
        StepVerifier.create(shoppingCartResponse)
                .expectNextMatches(shoppingCart1 -> shoppingCart1.equals(shoppingCartExpected))
                .expectComplete()
                .verify();
        Mockito.verify(clientRepository).findById(any(Long.class));
        Mockito.verify(shoppingCartRepository).save(any(ShoppingCart.class));
    }
}
