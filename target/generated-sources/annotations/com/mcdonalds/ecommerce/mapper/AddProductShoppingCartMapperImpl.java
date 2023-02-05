package com.mcdonalds.ecommerce.mapper;

import com.mcdonalds.ecommerce.model.Product;
import com.mcdonalds.ecommerce.model.ShoppingCart;
import com.mcdonalds.ecommerce.model.dto.request.AddProductRequest;
import com.mcdonalds.ecommerce.model.dto.response.AddProductResponse;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-03T20:23:18-0300",
    comments = "version: 1.5.2.Final, compiler: javac, environment: Java 17 (Oracle Corporation)"
)
@Component
public class AddProductShoppingCartMapperImpl implements AddProductShoppingCartMapper {

    @Override
    public AddProductResponse toResponseDto(ShoppingCart shoppingCart) {
        if ( shoppingCart == null ) {
            return null;
        }

        AddProductResponse addProductResponse = new AddProductResponse();

        return addProductResponse;
    }

    @Override
    public Product toEntity(AddProductRequest addProductRequest) {
        if ( addProductRequest == null ) {
            return null;
        }

        Product.ProductBuilder product = Product.builder();

        product.id( addProductRequest.getProductId() );

        return product.build();
    }
}
