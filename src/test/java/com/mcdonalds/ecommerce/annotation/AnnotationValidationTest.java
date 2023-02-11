package com.mcdonalds.ecommerce.annotation;

import com.mcdonalds.ecommerce.model.dto.request.AddClientRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.validation.Validation;
import javax.validation.Validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
@ExtendWith(SpringExtension.class)
class AnnotationValidationTest {

    private  Validator validator;

    @BeforeEach
    public  void setupValidatorInstance() {
        validator = Validation.buildDefaultValidatorFactory()
                .getValidator();
    }

    @Test
    void givenNoGender_whenValidated_thenShouldBeTwoViolation() {
        var request = AddClientRequest.builder().documentID(522525).nameComplete("jose").build();

        var violations = validator.validate(request);
        assertEquals(2, violations.size());
    }
    @Test
    void givenBadGender_whenValidated_thenShouldBeOneViolation() {
        var request = AddClientRequest.builder().documentID(522525).nameComplete("jose").gender("OTHER").build();
        var violations = validator.validate(request);
        assertEquals(1, violations.size());
    }
}

