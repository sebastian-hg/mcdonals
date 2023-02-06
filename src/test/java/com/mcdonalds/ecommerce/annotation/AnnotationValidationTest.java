package com.mcdonalds.ecommerce.annotation;

import com.mcdonalds.ecommerce.model.dto.request.AddClientRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.Validation;
import javax.validation.Validator;

import static org.junit.jupiter.api.Assertions.assertFalse;
@ExtendWith(SpringExtension.class)
class AnnotationValidationTest {

    private  Validator validator;

    @BeforeEach
    public  void setupValidatorInstance() {
        validator = Validation.buildDefaultValidatorFactory()
                .getValidator();
    }

    @Test
    void whenAllAcceptable_thenShouldNotGiveConstraintViolations() {
        var request = AddClientRequest.builder().documentID(522525).nameComplete("jose").build();

        var violations = validator.validate(request);
        assertFalse(violations.isEmpty());
    }
}

