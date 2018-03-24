package com.kodgemisi.assignment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kodgemisi.assignment.domains.ConfirmationToken;

@Repository
public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long>{

	@Query("SELECT ct FROM confirmation_token ct WHERE ct.token = ?1")
	ConfirmationToken findByToken(String token);
}
