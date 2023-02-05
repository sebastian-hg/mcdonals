package com.mcdonalds.ecommerce.handler;

import com.mcdonalds.ecommerce.helper.ResponseHelper;
import com.mcdonalds.ecommerce.mapper.ShoppingCartMapper;
import com.mcdonalds.ecommerce.model.dto.request.CreateShoppingCartRequest;
import com.mcdonalds.ecommerce.service.CreateShoppingCartService;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;


@Component
@Log4j2
public class CreateShoppingCartHandler extends Handler<CreateShoppingCartRequest, Validator> {

    private final CreateShoppingCartService service;
    private final ShoppingCartMapper mapper;

    public CreateShoppingCartHandler(@NonNull ResponseHelper responseHelper,
                                     ShoppingCartMapper mapper,
                                     @Autowired @Qualifier("defaultValidator") Validator validator,
                                     CreateShoppingCartService service) {
        super(responseHelper, CreateShoppingCartRequest.class, validator);
        this.service = service;
        this.mapper = mapper;
    }

    @Override
    protected @NonNull Mono<ServerResponse> execute(Mono<CreateShoppingCartRequest> validBody, ServerRequest serverRequest) {

        return validBody
                .doOnNext(r -> log.info("Body validation with request {} ...", r))
                .flatMap(service::execute)
                .doOnNext(createShoppingCartResponse -> log.info("response, {}", createShoppingCartResponse))
                .flatMap(mapper::execute)
                .flatMap(response -> responseHelper.buildOK(Mono.just(response)));
    }
}


