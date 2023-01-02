package com.user.loan_Management.validator;

import java.util.Arrays;
import java.util.Set;

//import javax.validation.ConstraintViolation;
//import javax.validation.Validation;
//import javax.validation.Validator;
//import javax.validation.ValidatorFactory;

import com.user.loan_Management.http.response.DataResponse;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;


public class Validations {
	
	public DataResponse validate(Object requestDto, String[] array) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		Set<ConstraintViolation<Object>> violations = null;

		violations = validator.validate(requestDto);

		for (ConstraintViolation<Object> violation : violations) {

			if (Arrays.asList(array).indexOf(violation.getPropertyPath().toString()) != -1) {

				if (violation.getMessage().toString() != null) {

					return new DataResponse(400, violation.getMessage(), null);
				}

			}

		}

		return null;
	}


}
