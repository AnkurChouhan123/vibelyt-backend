package com.ankur.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ankur.model.PasswordResetToken;

public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Integer> {

	PasswordResetToken findByToken(String token);

}
