package com.mcdonalds.ecommerce.service;

import com.mcdonalds.ecommerce.model.Client;
import com.mcdonalds.ecommerce.model.dto.request.AddClientRequest;
import reactor.core.publisher.Mono;

public interface AddClientService {

    Mono<Client> execute(AddClientRequest addClientRequest);
}
