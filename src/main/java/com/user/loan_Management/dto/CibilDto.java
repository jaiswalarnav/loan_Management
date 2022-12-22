package com.user.loan_Management.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
//import javax.validation.constraints.NotBlank;
//import javax.validation.constraints.Pattern;
//import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class CibilDto {

	private int id;

	@NotBlank(message = "Pan number field can not be blank")
	@Size(min = 10, max = 10, message = "Pan number must be 10 characters long")
	@Pattern(regexp = "^([a-zA-Z]){5}([0-9]){4}([a-zA-Z]){1}?$")
	private String panNo;

	@NotBlank(message = "Cibil score field can not be blank")
	private Integer cibilScore;

}