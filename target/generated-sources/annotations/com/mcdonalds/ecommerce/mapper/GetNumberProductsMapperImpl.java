package com.mcdonalds.ecommerce.mapper;

import com.mcdonalds.ecommerce.model.ShoppingCart;
import com.mcdonalds.ecommerce.model.dto.response.GetNumberProductsResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-03T20:23:18-0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
@Component
public class GetNumberProductsMapperImpl implements GetNumberProductsMapper {

    @Override
    public GetNumberProductsResponse toResponseDto(ShoppingCart shoppingCart) {
        if ( shoppingCart == null ) {
            return null;
        }

        GetNumberProductsResponse getNumberProductsResponse = new GetNumberProductsResponse();

        getNumberProductsResponse.setNumberProducts( shoppingCart.getNumberProducts() );

        return getNumberProductsResponse;
    }
}
