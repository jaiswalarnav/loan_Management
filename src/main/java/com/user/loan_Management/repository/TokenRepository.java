package com.user.loan_Management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.user.loan_Management.model.Token;

public interface TokenRepository extends JpaRepository<Token, Long>{

}
