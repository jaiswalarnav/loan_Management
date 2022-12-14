package com.user.loan_Management.service;



import com.user.loan_Management.dto.UserLoginReturnDto;
import com.user.loan_Management.dto.UserUpdateProfileDto;
import com.user.loan_Management.dto.UserViewProfileDto;


public interface UserService {

	UserLoginReturnDto userLogin(long applicationNo,String dob)throws Exception;
	
	UserViewProfileDto viewUserProfile(long applicationNo)throws Exception;
	
	void updateUserProfile(long applicationNo,UserUpdateProfileDto userUpdateProfileDto)throws Exception;
	
}
