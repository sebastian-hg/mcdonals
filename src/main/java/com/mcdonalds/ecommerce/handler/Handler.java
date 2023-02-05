package com.mcdonalds.ecommerce.handler;

import com.mcdonalds.ecommerce.exception.api.BadRequestException;
import com.mcdonalds.ecommerce.exception.client.RequestNotValidException;
import com.mcdonalds.ecommerce.helper.ResponseHelper;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Log4j2
@AllArgsConstructor
@RequiredArgsConstructor
public abstract class Handler <T, U extends Validator>{
    @NonNull
    protected ResponseHelper responseHelper;
    private Class<T> validationClass;
    private U validator;

    /**
     * Execute when the request has a body
     * @param serverRequest
     * @return
     */
    public @NonNull Mono<ServerResponse> executeWithBodyValidation(ServerRequest serverRequest) {
        log.debug("Body validation with request {} ...", serverRequest);
        return validateBodyAndExecute(serverRequest);
    }

    /**
     * Execute when the request doesn't have a body
     * @param serverRequest
     * @return
     */
    public Mono<ServerResponse> executeWithoutBodyValidation(ServerRequest serverRequest) {
        log.debug("Without body validation with request {} ...", serverRequest);
        return execute(Mono.empty(), serverRequest);
    }

    protected abstract Mono<ServerResponse> execute(Mono<T> validBody, ServerRequest serverRequest);

    private Mono<ServerResponse> validateBodyAndExecute(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(validationClass)
                .flatMap(body -> {
                    Errors errors = new BeanPropertyBindingResult(body, validationClass.getName());
                    validator.validate(body, errors);
                    if (errors.getAllErrors().isEmpty()) {
                        return execute(Mono.just(body), serverRequest);
                    } else {
                        log.debug("Found errors {} ...", errors);
                        return Mono.error(new RequestNotValidException(errors));

                    }
                })
                .switchIfEmpty(Mono.error(new BadRequestException()));
    }
}
