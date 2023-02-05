package com.mcdonalds.ecommerce.model.dto.response;

import com.mcdonalds.ecommerce.helper.IResponseSuccess;
import lombok.Data;

@Data
public class CreateShoppingCartResponse implements IResponseSuccess {
    private String client;
    private Boolean isVip;

}
