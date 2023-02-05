package com.mcdonalds.ecommerce.helper;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@Component
public class ResponseHelper {

    public Mono<ServerResponse> build(final HttpStatus httpStatus, Mono<? extends IResponseSuccess> successResponse) {
        Class<Mono<IResponseSuccess>> targetClass = (Class<Mono<IResponseSuccess>>) successResponse.getClass();
        return ServerResponse.status(httpStatus).contentType(MediaType.APPLICATION_JSON).body(successResponse, targetClass)
                .doOnNext(e -> log.debug("Generating Response with httpStatus={} and successResponse", httpStatus));
    }

    public Mono<ServerResponse> build(final HttpStatus httpStatus,
                                      Flux<? extends IResponseSuccess> successResponse,
                                      Class<? extends IResponseSuccess> targetClass) {
        return ServerResponse.status(httpStatus).contentType(MediaType.APPLICATION_JSON).body(successResponse, targetClass)
                .doOnNext(e -> log.debug("Generating Response with httpStatus={}, successResponse and targetClass", httpStatus));
    }

    public Mono<ServerResponse> build(final HttpStatus httpStatus) {
        return ServerResponse.status(httpStatus).build()
                .doOnNext(e -> log.debug("Generating Response with httpStatus={}", httpStatus));
    }

    public Mono<ServerResponse> buildOK(Mono<? extends IResponseSuccess> successResponse) {
        return build(HttpStatus.OK, successResponse);
    }

    public Mono<ServerResponse> buildOK(Flux<? extends IResponseSuccess> successResponse,
                                        Class<? extends IResponseSuccess> targetClass) {
        return build(HttpStatus.OK, successResponse, targetClass);
    }

    public Mono<ServerResponse> buildOK() {
        return build(HttpStatus.OK);
    }

}
