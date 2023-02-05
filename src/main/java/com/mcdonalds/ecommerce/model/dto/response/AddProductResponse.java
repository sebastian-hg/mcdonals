package com.mcdonalds.ecommerce.model.dto.response;

import com.mcdonalds.ecommerce.helper.IResponseSuccess;
import lombok.Data;

@Data
public class AddProductResponse implements IResponseSuccess {
    private String description;
}
