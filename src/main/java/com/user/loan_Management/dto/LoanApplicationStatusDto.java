package com.user.loan_Management.dto;

import org.springframework.format.annotation.DateTimeFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoanApplicationStatusDto {

	@NotNull(message = "application number can not be blank")
	private long applicationNo;

	@NotBlank(message = "DOB cannot be blank")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private String dob;

}