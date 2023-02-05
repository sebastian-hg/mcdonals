package com.mcdonalds.ecommerce.mapper;

import com.mcdonalds.ecommerce.model.Client;
import com.mcdonalds.ecommerce.model.dto.request.AddClientRequest;
import com.mcdonalds.ecommerce.model.dto.response.AddClientResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddClientMapper {
    @Mapping(target="documentNational", source="documentID")
    @Mapping(target="nameCompleted", source="nameComplete")
    Client toClient (AddClientRequest addClientRequest);

    @Mapping(target="nameNewClient", source="nameCompleted")
    @Mapping(target = "document", source = "documentNational" )
    AddClientResponse toClientResponse (Client client);
}
