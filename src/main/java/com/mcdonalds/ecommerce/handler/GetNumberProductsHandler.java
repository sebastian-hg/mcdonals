package com.mcdonalds.ecommerce.handler;

import com.mcdonalds.ecommerce.helper.ResponseHelper;
import com.mcdonalds.ecommerce.mapper.GetNumberProductsMapper;
import com.mcdonalds.ecommerce.service.GetStatusFromShoppingCartService;
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
public class GetNumberProductsHandler extends Handler<Object, Validator> {
    private final GetNumberProductsMapper getNumberProductsMapper;
    private final GetStatusFromShoppingCartService service;

    public GetNumberProductsHandler(@NonNull ResponseHelper responseHelper,
                                    GetNumberProductsMapper mapper,
                                    @Autowired @Qualifier("defaultValidator") Validator validator,
                                    GetNumberProductsMapper getNumberProductsMapper, GetStatusFromShoppingCartService service) {
        super(responseHelper, Object.class, validator);
        this.getNumberProductsMapper = getNumberProductsMapper;
        this.service = service;
    }
    @Override
    protected Mono<ServerResponse> execute(Mono<Object> validBody, ServerRequest serverRequest) {
        log.info("Body validation with request {} ...", serverRequest);
        var personId = (serverRequest.pathVariable("id"));
        var statusId = Long.parseLong(personId);

        return service.execute(statusId)
                .map(getNumberProductsMapper::toResponseDto)
                .flatMap(getNumberProductsResponse -> responseHelper.buildOK(Mono.just(getNumberProductsResponse)));
    }
}
