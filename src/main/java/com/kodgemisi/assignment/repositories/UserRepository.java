package com.kodgemisi.assignment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.kodgemisi.assignment.domains.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{

	@Query("SELECT u FROM USER u WHERE u.email = ?1 AND u.password = ?2")
	User getUserByEmailAndPassword(String email, String password);

	User findByEmail(String email);	
	
	@Query("SELECT u FROM USER u WHERE u.email = ?1 AND u.authenticated = 1")
	User findAuthenticatedUserByEmail(String email);	
	
	@Query("SELECT u FROM USER u, password_reset_token prt WHERE u.id = prt.user.id AND prt.token = ?1")
	User findByPasswordResetToken(String token);
	
	/*This an alternative method to findByPasswordResetToken, both finds the same result, Check SQL logging for queries*/
	@Query("SELECT u FROM USER u JOIN u.passwordResetToken prt ON prt.token = ?1")
	User findByJOIN(String token);
	
	/*This an alternative method to findByPasswordResetToken, both finds the same result, Check SQL logging for queries*/
	@Query("SELECT u FROM USER u WHERE u.id IN (SELECT prt.user.id FROM password_reset_token prt WHERE prt.token = ?1)")
	User findBySubQuery(String token);
}
