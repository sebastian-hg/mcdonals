package com.mcdonalds.ecommerce.configuration.impl;

import com.mcdonalds.ecommerce.configuration.EnumNamePattern;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.stream.Stream;

public class EnumNamePatternValidator implements ConstraintValidator<EnumNamePattern, CharSequence >{


    private List<String> acceptedValues;
    @Override
    public void initialize(EnumNamePattern constraintAnnotation) {
        acceptedValues = Stream.of(constraintAnnotation.enumClass().getEnumConstants())
                .map(Enum::name)
                .toList();
    }

    @Override
    public boolean isValid(CharSequence value, ConstraintValidatorContext context) {
        return StringUtils.hasText(value) && acceptedValues.contains(value.toString());
    }
}






