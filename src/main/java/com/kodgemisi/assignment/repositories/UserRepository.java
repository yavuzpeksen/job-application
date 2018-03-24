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
	
	
}
