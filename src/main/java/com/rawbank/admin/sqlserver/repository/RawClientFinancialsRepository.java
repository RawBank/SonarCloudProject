/**
 * © Copyright 2021 Rawbank. All Rights Reserved.
 * 
 * Change Section:
 * Programmer			Date
 * Krishna				7/16/2022
 * Initial Coding.
 * 
 * 
 * Created By:
 * @author krishna
 * @since Jul 16, 2022
 * 
 */
package com.rawbank.admin.sqlserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rawbank.admin.sqlserver.entity.RawClientFinancials;

/**
 * @author krishna
 *
 */
@Repository
public interface RawClientFinancialsRepository extends JpaRepository<RawClientFinancials, String>{
	
	//This is actuall customerNumber or client number combined with some bank account details. 
	//See in the controller RawAdminChangeCardStatusController.java
	List<RawClientFinancials> findByBankAccountNumber(String bankAccountNumber);
	
	
	//This client number is CSC client number
	List<RawClientFinancials> findByClientNumber(String clientNumber);

}
