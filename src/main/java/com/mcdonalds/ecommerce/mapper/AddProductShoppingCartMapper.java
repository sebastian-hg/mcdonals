package com.mcdonalds.ecommerce.mapper;

import com.mcdonalds.ecommerce.model.Product;
import com.mcdonalds.ecommerce.model.ShoppingCart;
import com.mcdonalds.ecommerce.model.dto.request.AddProductRequest;
import com.mcdonalds.ecommerce.model.dto.response.AddProductResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AddProductShoppingCartMapper {

    AddProductResponse toResponseDto(ShoppingCart shoppingCart);

    @Mapping(target = "id", source = "productId")
    Product toEntity(AddProductRequest addProductRequest);

}
