package com.mcdonalds.ecommerce.mapper;

import com.mcdonalds.ecommerce.model.ShoppingCart;
import com.mcdonalds.ecommerce.model.dto.response.GetNumberProductsResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface GetNumberProductsMapper {

    @Mapping(target = "numberProducts", expression = "java(shoppingCart.getNumberProducts())")
    GetNumberProductsResponse toResponseDto (ShoppingCart shoppingCart);
}
