package com.mcdonalds.ecommerce.exception;

import com.fasterxml.jackson.core.io.JsonStringEncoder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.mcdonalds.ecommerce.handlerErrors.response.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.io.IOException;

import static org.springframework.util.StringUtils.hasText;

@Log4j2
@AllArgsConstructor
public class WebClientCustomException extends BaseException {
    private final WebClientResponseException webClientResponseException;

    @Override
    public Mono<ServerResponse> handlerException() {
        int status = webClientResponseException.getRawStatusCode();
        JsonNode json = decodeCause(webClientResponseException.getResponseBodyAsString());
        String reason = json != null ? json.toString() : null;

        ErrorResponse errorResponse = ErrorResponse.builder()
                .errorCode("WC_" + status)
                .errorDetail(getMessage(webClientResponseException))
                .httpStatusCode(status)
                .exceptionReason(reason)
                .build();
        return makeServerResponse(errorResponse);
    }

    private JsonNode decodeCause(String responseBody) {

        if (!hasText(responseBody)) {
            return null;
        }

        ObjectMapper mapper = new ObjectMapper();
        JsonNode json;
        try {
            json = mapper.readTree(responseBody);
        } catch (IOException e1) {
            log.error("Error decoding service response: {}", e1.getMessage());
            json = tryToConvertToJsonFormat(responseBody, mapper);
        }

        return json;
    }

    private JsonNode tryToConvertToJsonFormat(String responseBody, ObjectMapper mapper) {
        JsonNode json = null;
        try {
            JsonStringEncoder encoder = JsonStringEncoder.getInstance();
            StringBuilder builder = new StringBuilder();
            builder.append('"');
            encoder.quoteAsString(responseBody, builder);
            builder.append('"');
            json = mapper.readTree(builder.toString());
        } catch (IOException e2) {
            log.error("Error decoding service response: {}", e2.getMessage());
        }
        return json;
    }
}