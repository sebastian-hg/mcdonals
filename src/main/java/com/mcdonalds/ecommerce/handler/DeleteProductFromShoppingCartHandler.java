package com.mcdonalds.ecommerce.handler;

import com.mcdonalds.ecommerce.helper.ResponseHelper;
import com.mcdonalds.ecommerce.mapper.AddClientMapper;
import com.mcdonalds.ecommerce.service.DeleteProductFromShoppingCartService;
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
public class DeleteProductFromShoppingCartHandler extends Handler<Object, Validator> {


    private final DeleteProductFromShoppingCartService service;

    public DeleteProductFromShoppingCartHandler(@NonNull ResponseHelper responseHelper,
                                                AddClientMapper mapper,
                                                @Autowired @Qualifier("defaultValidator") Validator validator,
                                                ResponseHelper responseHelper1, DeleteProductFromShoppingCartService service) {
        super(responseHelper, Object.class, validator);
        this.service = service;

    }


    @Override
    protected Mono<ServerResponse> execute(Mono<Object> validBody, ServerRequest serverRequest) {
        log.info("Body validation with request {} ...", serverRequest);
        var shoppingCartRequestId = serverRequest.pathVariable("shoppingCartId");
        var shoppingCartId = Long.parseLong(shoppingCartRequestId);
        var productRequestId = serverRequest.pathVariable("productId");
        var productId = Long.parseLong(productRequestId);
        return service.execute(shoppingCartId, productId)
                //TODO: add DTO and MAPPER
                .flatMap(shoppingCart -> responseHelper.buildOK(Mono.just(shoppingCart)));
    }
}
