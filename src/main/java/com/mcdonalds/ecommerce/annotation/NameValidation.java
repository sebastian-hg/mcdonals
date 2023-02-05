package com.mcdonalds.ecommerce.annotation;

import com.mcdonalds.ecommerce.configuration.impl.NewClientValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;

@Documented
@Constraint(validatedBy = NewClientValidator.class)
@Target({METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NameValidation {

    Class<? extends Enum<?>> enumClass();

    String message() default "Invalid value for type of client";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
