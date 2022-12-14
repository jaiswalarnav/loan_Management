package com.user.loan_Management.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserUpdateProfileDto {

	@NotBlank(message = "contact no can not be empty")
	@Pattern(regexp = "^[1-9][0-9]*$", message = "enter valid contact number")
	@Size(min = 10, max = 10, message = "contact number should be of 10 digits")
	private String contactNo;

	@Email
	@NotBlank(message = "mail Id can not be empty")
	private String mailId;

	@NotBlank(message = "address can not be empty")
	@Size(max = 100, message = "Address length should not be greater than 100 characters ")
	private String address;

	@NotNull(message = "marital status can not be empty")
	private boolean married;
}
