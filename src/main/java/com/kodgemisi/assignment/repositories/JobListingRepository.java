package com.kodgemisi.assignment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.kodgemisi.assignment.domains.JobListing;

public interface JobListingRepository extends JpaRepository<JobListing, Long>{
	
	@Query("SELECT jl FROM JOB_LISTING jl INNER JOIN jl.user u ON u.id = jl.user AND u.email = ?1")
	JobListing getJobListingByEmail(String email);

}
