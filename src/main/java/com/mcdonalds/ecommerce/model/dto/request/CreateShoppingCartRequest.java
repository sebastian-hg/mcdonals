package com.mcdonalds.ecommerce.model.dto.request;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Builder
public class CreateShoppingCartRequest {

    @NotNull(message = "clientId can not be null ")
    private Long clientId;
    //@IsVipValidConstraint(message = "the value has to be true or false in isVip")
    private Boolean isVip;
}
