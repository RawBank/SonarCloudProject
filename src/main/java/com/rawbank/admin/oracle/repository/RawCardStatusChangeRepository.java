/**
 * Copyright 2022 Rawbank. All Rights Reserved.
 * 
 * Change Section: 
 * Programmer 			Date 
 * Krishna 				Aug 13, 2022
 * CSC admin portal Project Initial Coding.
 * 
 * Created By:
 * 
 * @author krishna
 * @since Aug 13, 2022
 * 
 */

package com.rawbank.admin.oracle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rawbank.admin.oracle.entity.RawCardStatusChange;

/**
 * @author krishna
 *
 */
@Repository
public interface RawCardStatusChangeRepository extends JpaRepository<RawCardStatusChange, Long> {
	
	public List<RawCardStatusChange> findAllByOrderByCardStatusChangeDateDesc();
	
	public List<RawCardStatusChange> findAllByUserNameOrderByCardStatusChangeDateDesc(String userName);
	
	@Query("SELECT card FROM RawCardStatusChange card WHERE LOWER(card.cardCurrenttStatus) LIKE LOWER(CONCAT('%', ?1,'%'))")
	List<RawCardStatusChange> findBycardCurrenttStatusLikeCaseInsensitive(String cardCurrenttStatus);
	  //ardStatusChangeDate
	  //findFirstByIdOrderByPublicationDateDesc  ardNumber
		RawCardStatusChange findFirstByCardNumberOrderByCardStatusChangeDateDesc(String cardNumber);
	
}


