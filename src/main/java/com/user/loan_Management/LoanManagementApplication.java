package com.user.loan_Management;

import org.modelmapper.ModelMapper;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@EnableBatchProcessing
public class LoanManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoanManagementApplication.class,args);
	}

	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

}
