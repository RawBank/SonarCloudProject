/**
 * Copyright 2022 Rawbank. All Rights Reserved.
 * 
 * Change Section: 
 * Programmer 			Date 
 * Krishna 				Aug 8, 2022
 * CSC admin portal Project Initial Coding.
 * 
 * Created By:
 * 
 * @author krishna
 * @since Aug 8, 2022
 * 
 */
package com.rawbank.admin.oracle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rawbank.admin.oracle.entity.RawCardLimitChange;

/**
 * @author krishna
 *
 */
@Repository
public interface RawCardLimitChangeRepository extends JpaRepository<RawCardLimitChange, Long> {
	RawCardLimitChange findBycscNumber(String cscNumber);
	
	@Query("SELECT card FROM RawCardLimitChange card WHERE LOWER(card.approverStatus) LIKE LOWER(CONCAT('%', ?1,'%')) order by card.dateCreated desc")
	List<RawCardLimitChange> findCardsByApproverStatusLikeCaseInsensitive(String approverStatus);
}
