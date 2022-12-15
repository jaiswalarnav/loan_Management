package com.user.loan_Management.service;

import com.user.loan_Management.dto.AdminDto;
import com.user.loan_Management.dto.AdminRegisterDto;

public interface AdminService {

	//void loginAdmin(AdminDto adminDto)throws Exception;
	
	long registerAdmin(AdminRegisterDto adminRegisterDto)throws Exception;
}
