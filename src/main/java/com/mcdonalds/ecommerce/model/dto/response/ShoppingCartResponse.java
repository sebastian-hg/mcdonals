package com.mcdonalds.ecommerce.model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.mcdonalds.ecommerce.helper.IResponseSuccess;
import com.mcdonalds.ecommerce.model.ShoppingCartProduct;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ShoppingCartResponse  implements IResponseSuccess {
    private Long id;
    private Integer documentNational;
    private String nameCompleted;
    private Boolean isVip;
    private Integer numberProducts;
    private BigDecimal totalPurchase;
    private List<ShoppingCartDiscountResponse> discounts;
    private List<ShoppingCartProductResponse> products;
}
