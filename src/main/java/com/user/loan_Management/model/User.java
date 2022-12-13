package com.user.loan_Management.model;



import com.user.loan_Management.model.support.Support;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class User extends Support {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String dob;
}
