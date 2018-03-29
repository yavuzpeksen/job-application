package com.kodgemisi.assignment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kodgemisi.assignment.domains.PasswordResetToken;
import com.kodgemisi.assignment.domains.User;

@Repository
public interface PasswordResetTokenRepository extends JpaRepository<PasswordResetToken, Long>{

	@Query("SELECT prt FROM password_reset_token prt WHERE prt.token = ?1")
	PasswordResetToken findByToken(String token);
	
}
