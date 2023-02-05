package com.mcdonalds.ecommerce.model.dto.response;

import com.mcdonalds.ecommerce.helper.IResponseSuccess;
import lombok.Data;

@Data
public class AddClientResponse implements IResponseSuccess {
    String nameNewClient;
    Integer document;
}
