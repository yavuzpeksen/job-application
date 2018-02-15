package com.kodgemisi.assignment.repositories;

import java.util.Date;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.kodgemisi.assignment.domains.Job;

public interface JobRepository extends JpaRepository<Job, Long>{
	
	@Query("SELECT j FROM JOB j WHERE j.jobListing.id = ?1")
	Set<Job> getJobByJobListingId(Long id);
		
	@Query(value="INSERT INTO JOB (job_listing_id, title, description, hiring_person_number, last_application_date) VALUES(?1,?2,?3,?4,?5)", nativeQuery = true)
	void addJob(long id, String title, String description, int numOfPerson, Date lastDate );

}
