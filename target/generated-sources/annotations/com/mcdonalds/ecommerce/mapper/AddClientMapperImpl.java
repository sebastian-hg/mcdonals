package com.mcdonalds.ecommerce.mapper;

import com.mcdonalds.ecommerce.model.Client;
import com.mcdonalds.ecommerce.model.dto.request.AddClientRequest;
import com.mcdonalds.ecommerce.model.dto.response.AddClientResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-03T20:23:18-0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
@Component
public class AddClientMapperImpl implements AddClientMapper {

    @Override
    public Client toClient(AddClientRequest addClientRequest) {
        if ( addClientRequest == null ) {
            return null;
        }

        Client.ClientBuilder client = Client.builder();

        client.documentNational( addClientRequest.getDocumentID() );
        client.nameCompleted( addClientRequest.getNameComplete() );
        client.gender( addClientRequest.getGender() );

        return client.build();
    }

    @Override
    public AddClientResponse toClientResponse(Client client) {
        if ( client == null ) {
            return null;
        }

        AddClientResponse addClientResponse = new AddClientResponse();

        addClientResponse.setNameNewClient( client.getNameCompleted() );
        addClientResponse.setDocument( client.getDocumentNational() );

        return addClientResponse;
    }
}
