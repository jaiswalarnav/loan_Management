package com.user.loan_Management.dto;


//import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDto {

	private long id;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	@NotNull(message = "dob can not be empty")
	private String dob;

}