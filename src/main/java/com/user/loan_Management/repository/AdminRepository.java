package com.user.loan_Management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.user.loan_Management.model.AdminRegister;

public interface AdminRepository extends JpaRepository<AdminRegister, Long> {

	public AdminRegister findByName(String name);

}
