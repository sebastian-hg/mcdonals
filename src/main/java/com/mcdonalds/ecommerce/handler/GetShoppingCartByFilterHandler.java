package com.mcdonalds.ecommerce.handler;

import com.mcdonalds.ecommerce.helper.ResponseHelper;
import com.mcdonalds.ecommerce.model.ShoppingCart;
import com.mcdonalds.ecommerce.service.GetShoppingCartByFilterService;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@AllArgsConstructor
@Component
@Log4j2
public class GetShoppingCartByFilterHandler {

    private final ResponseHelper responseHelper;
    private final GetShoppingCartByFilterService service;

    public @NonNull Mono<ServerResponse> execute(ServerRequest serverRequest) {

        var documentIdRequest = serverRequest.pathVariable("documentId");
        var documentId = Integer.parseInt(documentIdRequest);
        var dateStarRequest = serverRequest.pathVariable("init");
        var dateStar = LocalDate.parse(dateStarRequest);
        var dateEndRequest = serverRequest.pathVariable("end");
        var dateEnd = LocalDate.parse(dateEndRequest);
        var typeOrder = serverRequest.pathVariable("typeOrder");

        return service.execute(documentId, dateStar, dateEnd, typeOrder)
                .flatMap(shoppingCarts -> {
                    var fluxResponse = Flux.fromIterable(shoppingCarts);
                    return responseHelper.buildOK(fluxResponse, ShoppingCart.class);
                });
    }
}
