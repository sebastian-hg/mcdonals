package com.mcdonalds.ecommerce.handler;

import com.mcdonalds.ecommerce.helper.ResponseHelper;
import com.mcdonalds.ecommerce.mapper.AddClientMapper;
import com.mcdonalds.ecommerce.model.dto.request.AddClientRequest;
import com.mcdonalds.ecommerce.service.AddClientService;
import com.mcdonalds.ecommerce.service.DeleteShoppingCartService;
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

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;


@Component
@Log4j2
public class DeleteShoppingCartHandler extends Handler<Object, Validator>{


    private final DeleteShoppingCartService service;

    public DeleteShoppingCartHandler(@NonNull ResponseHelper responseHelper,
                            @Autowired @Qualifier("defaultValidator") Validator validator,
                                     DeleteShoppingCartService service) {
        super(responseHelper, Object.class, validator);
        this.service = service;

    }
    @Override
    protected Mono<ServerResponse> execute(Mono<Object> validBody, ServerRequest serverRequest) {
        log.info("Body validation with request {} ...", serverRequest);
        var requestId = serverRequest.pathVariable("id");
        var deleteId = Long.parseLong(requestId);
        return service.execute(deleteId)
                .flatMap(aBoolean -> responseHelper.buildOK());
    }
}