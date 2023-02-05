package com.mcdonalds.ecommerce.annotation.impl;

import com.mcdonalds.ecommerce.annotation.IsVipValidConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ClientVIPValidator implements ConstraintValidator<IsVipValidConstraint, Boolean> {


    @Override
    public boolean isValid(Boolean value, ConstraintValidatorContext context) {
        return value == Boolean.TRUE || value == Boolean.FALSE ;
    }
}
