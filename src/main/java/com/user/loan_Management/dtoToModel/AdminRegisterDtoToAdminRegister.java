package com.user.loan_Management.dtoToModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


import com.user.loan_Management.dto.AdminRegisterDto;
import com.user.loan_Management.model.AdminRegister;

@Component
public class AdminRegisterDtoToAdminRegister {

	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;

	//BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	public AdminRegister dtoToAdminRegister(AdminRegisterDto adminRegisterDto) {
		AdminRegister adminRegister = new AdminRegister();
		adminRegister.setName(adminRegisterDto.getName());
		// String aPass=adminRegisterDto.getPassword();
		System.out.println(bCryptPasswordEncoder);
		String password = bCryptPasswordEncoder.encode(adminRegisterDto.getPassword());
		adminRegister.setPassword(password);

		return adminRegister;
	}

}
