package com.mcdonalds.ecommerce.handler;

import com.mcdonalds.ecommerce.helper.ResponseHelper;
import com.mcdonalds.ecommerce.mapper.AddClientMapper;
import com.mcdonalds.ecommerce.model.dto.request.AddClientRequest;
import com.mcdonalds.ecommerce.service.AddClientService;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Validator;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Log4j2
@Component
public class AddClientHandler extends Handler<AddClientRequest, Validator> {

    private final AddClientMapper mapper;
    private final AddClientService service;

    public AddClientHandler(@NonNull ResponseHelper responseHelper,
                            AddClientMapper mapper,
                            @Autowired @Qualifier("defaultValidator") Validator validator,
                            AddClientService service) {
        super(responseHelper, AddClientRequest.class, validator);
        this.service = service;
        this.mapper = mapper;
    }


    @Override
    public @NonNull Mono<ServerResponse> execute(Mono<AddClientRequest> validBody, ServerRequest serverRequest) {
        log.info("Body validation with request {} ...", serverRequest);
        return validBody
                .doOnNext(body -> log.info("Body validation with request {} ...", body))
                .flatMap(service::execute)
                //     .map(mapper::toClientResponse)
                .flatMap(response -> responseHelper.buildOK());
    }
}



