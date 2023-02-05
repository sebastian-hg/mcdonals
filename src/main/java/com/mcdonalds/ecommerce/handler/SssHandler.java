package com.mcdonalds.ecommerce.handler;

import com.mcdonalds.ecommerce.helper.ResponseHelper;
import lombok.NonNull;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public class SssHandler extends Handler<Object, Validator>{
    public SssHandler(@NonNull ResponseHelper responseHelper, Class<Object> validationClass, Validator validator) {
        super(responseHelper, validationClass, validator);
    }

    @Override
    protected Mono<ServerResponse> execute(Mono<Object> validBody, ServerRequest serverRequest) {
        return null;
    }
}
