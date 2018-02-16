package com.kodgemisi.assignment.repositories;

import java.util.Set;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.kodgemisi.assignment.domains.Job;

public interface JobRepository extends JpaRepository<Job, Long>{
	
	@Query("SELECT j FROM JOB j WHERE j.jobListing.id = ?1")
	Set<Job> getJobByJobListingId(Long id);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM JOB j WHERE j.jobListing.id = ?1")
	void deleteJobsByJobListingId(Long id);
	
}
