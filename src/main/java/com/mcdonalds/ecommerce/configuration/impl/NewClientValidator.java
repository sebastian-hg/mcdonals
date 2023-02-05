package com.mcdonalds.ecommerce.configuration.impl;

import com.mcdonalds.ecommerce.annotation.NameValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class NewClientValidator implements ConstraintValidator<NameValidation, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value.length() <= 1 ? Boolean.FALSE : Boolean.TRUE;
    }
}
