package com.mcdonalds.ecommerce.exception;

import com.mcdonalds.ecommerce.handlerErrors.response.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Log4j2
public class GenericCustomException extends BaseException {
    final Exception exception;

    @Override
    public Mono<ServerResponse> handlerException() {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode("AL_500")
                .errorDetail(getMessage(exception))
                .httpStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .build();
        return makeServerResponse(errorResponse);
    }
}