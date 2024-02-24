/**
 * © Copyright 2021 Rawbank. All Rights Reserved.
 * 
 * Change Section:
 * Programmer			Date
 * Krishna				7/13/2022
 * Initial Coding.
 * 
 * 
 * Created By:
 * @author krishna
 * @since Jul 13, 2022
 * 
 */
package com.rawbank.admin.sqlserver.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rawbank.admin.sqlserver.entity.RawClientCards;

/**
 * @author krishna
 * @param <RawClientCards>
 *
 */
@Repository
public interface RawClientRepository extends JpaRepository<RawClientCards, String> {
	RawClientCards findByClientNumber(String clientNumber);
	
	 // clientNumber  = parentClientNumber
	/*
	 * 	@Query(value = "SELECT *  FROM utilisateur_roles ur WHERE ur.role_id = ?1 AND ur.user_name=?2 ", nativeQuery = true)
	List<Object> relationutlisateurrole(Long roleId, String userName);
	 * */
	Optional<List<RawClientCards>> findByClientNumberOrParentClientNumber(String clientNumber, String parentClientNumber);
	// cardNumber
	
	RawClientCards findByCardNumber(String cardNumber);
}



/*
 * select   expiry_date, card_number  from cscdb.dbo.TBL_CLIENT_CARDS where client_number
 *  in ('00010122','00010133','00012889') or parent_client_number in ('00010122','00010133','00012889')
 * */
 