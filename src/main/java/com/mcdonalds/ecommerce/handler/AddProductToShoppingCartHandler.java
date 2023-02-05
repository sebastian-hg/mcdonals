package com.mcdonalds.ecommerce.handler;

import com.mcdonalds.ecommerce.helper.ResponseHelper;
import com.mcdonalds.ecommerce.mapper.ShoppingCartMapper;
import com.mcdonalds.ecommerce.model.dto.request.AddProductRequest;
import com.mcdonalds.ecommerce.service.AddProductToShoppingCartService;
import lombok.AllArgsConstructor;
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
public class AddProductToShoppingCartHandler extends Handler<AddProductRequest, Validator> {


    private final AddProductToShoppingCartService service;
    private final ShoppingCartMapper mapper;

    public AddProductToShoppingCartHandler(@NonNull ResponseHelper responseHelper,
                                           ShoppingCartMapper mapper,
                                           @Autowired @Qualifier("defaultValidator") Validator validator,
                                           AddProductToShoppingCartService service) {
        super(responseHelper, AddProductRequest.class, validator);
        this.service = service;
        this.mapper = mapper;
    }


    /**
     * public @NonNull Mono<ServerResponse> execute(ServerRequest serverRequest) {
     * log.info("Body validation with request {} ...", serverRequest);
     * <p>
     * var requestId = serverRequest.pathVariable("id");
     * var id = Long.parseLong(requestId);
     * return serverRequest.bodyToMono(AddProductRequest.class)
     * //TODO: add validation for close shopping cart
     * .flatMap(addProductRequest -> service.execute(id, addProductRequest))
     * .flatMap(mapper::execute)
     * .flatMap(response -> responseHelper.buildOK(Mono.just((response))));
     * }
     */

    @Override
    protected Mono<ServerResponse> execute(Mono validBody, ServerRequest serverRequest) {
        log.info("Body validation with request {} ...", serverRequest);

        var requestId = serverRequest.pathVariable("id");
        var id = Long.parseLong(requestId);
        return serverRequest.bodyToMono(AddProductRequest.class)
                //TODO: add validation for close shopping cart
                .flatMap(addProductRequest -> service.execute(id, addProductRequest))
                .flatMap(mapper::execute)
                .flatMap(response -> responseHelper.buildOK(Mono.just((response))));
    }
}
