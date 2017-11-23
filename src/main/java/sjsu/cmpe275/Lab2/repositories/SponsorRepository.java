/**
 * 
 */
package sjsu.cmpe275.Lab2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import sjsu.cmpe275.Lab2.model.Sponsor;

/*
 * Project: CMPE275Lab2
 * @author: Kemy Halani, Parth Pandya, Rahil Modi
 * Purpose: Assignment 2 submission at San Jose State University
 * Do not use for any purpose without prior consent from Author or institution
 * 
 */

/*
 * Sponsor repository to operate on Sponsor Table
 */
public interface SponsorRepository extends JpaRepository<Sponsor, Long> {
}
