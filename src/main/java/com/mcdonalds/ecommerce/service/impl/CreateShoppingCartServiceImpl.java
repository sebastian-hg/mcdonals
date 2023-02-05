package com.mcdonalds.ecommerce.service.impl;

import com.mcdonalds.ecommerce.exception.ClientNoExistException;
import com.mcdonalds.ecommerce.model.ShoppingCart;
import com.mcdonalds.ecommerce.model.dto.request.CreateShoppingCartRequest;
import com.mcdonalds.ecommerce.repository.ClientRepository;
import com.mcdonalds.ecommerce.repository.ShoppingCartRepository;
import com.mcdonalds.ecommerce.service.CreateShoppingCartService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;

@AllArgsConstructor
@Log4j2
@Service
public class CreateShoppingCartServiceImpl implements CreateShoppingCartService {
    private final ClientRepository clientRepository;
    private final ShoppingCartRepository shoppingCartRepository;

    @Override
    public Mono<ShoppingCart> execute(CreateShoppingCartRequest createShoppingCartRequest) {
        return Mono.just(createShoppingCartRequest)
                .map(createShoppingCartRequest1 ->
                        clientRepository.findById(createShoppingCartRequest.getClientId()))
                .filter(Optional::isPresent)
                .map(client -> ShoppingCart.builder()
                        .client(client.get())
                        .isVip(createShoppingCartRequest.getIsVip())
                        .build())
                .map(shoppingCartRepository::save)
                .switchIfEmpty(Mono.error(ClientNoExistException::new));
    }
}
