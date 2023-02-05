package com.mcdonalds.ecommerce.model.dto.request;

import com.mcdonalds.ecommerce.annotation.EnumNamePattern;
import com.mcdonalds.ecommerce.model.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AddClientRequest {
    @NotNull(message = "documentID can not be null ")
    private Integer documentID;

    @NotNull
    @EnumNamePattern(enumClass = Gender.class)
    private Gender gender;
    private String nameComplete;
}
