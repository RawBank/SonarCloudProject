/**
* Copyright 2022 Rawbank. All Rights Reserved.
* 
 * Change Section:
* Programmer               Date
*
* 
 * 
 * Created By:
* @author jacquesk
* @since 8 nov. 2022
* 
 */

package com.rawbank.admin.oracle.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.rawbank.admin.oracle.entity.RawApprovalsOps;

/**
 * @author jacquesk
 *
 */

@Repository
public interface RawApprovalsOpsRepository extends JpaRepository<RawApprovalsOps, Long> {
	
	// Load Prepaid Individual and Bulk dashboard in Approver profile. It loads req status PENDING or ECHEC_CSC
	Optional<List<RawApprovalsOps>> findByapprouverStatusInAndCommentsStartingWith(List<String> approuverStatusList, String comments);
	

	
	//old query
	//Optional<List<RawApprovalsOps>> findByApprouverStatusContainingIgnoreCase(String approuverStatus);
	//Used to get only bulk cards with approve status PENDING to query P2P transfer API
	Optional<List<RawApprovalsOps>> findByapprouverStatusAndCommentsStartingWith(String approuverStatus, String comments);

	
	//Working fine to filter records not like BLK and not Ind in Approver profile Table with Action button
	Optional<List<RawApprovalsOps>> findByapprouverStatusAndCommentsNotLikeAndCommentsNotLike(String approuverStatus, String comments, String comments1);
	
	
	//Optional<List<RawApprovalsOps>> findByapprouverAndApprouverStatusAndCommentsNotLikeAndCommentsNotLike(String approuver, String approuverStatus, String comments, String comments1);
	
	

	//old but with risk of launching P2P transfer many times while csc card recharge would fail
	//Optional<RawApprovalsOps> findByRetrievalReference (String retrievalReference);
	//New to approve only txn with status PENDING one by one
	Optional<RawApprovalsOps> findByRetrievalReferenceAndApprouverStatus (String retrievalReference, String approuverStatus);
	
	
	
	
	 
	// Report Initiator general MC recharge  -- old req working
	//Optional<List<RawApprovalsOps>> findByInitiatorAndOpsType(String initiator, String opsType);
	// Report Initiator general MC recharge  -- new req working
	 @Query(value = "SELECT * FROM CSC_ADMIN_APPROVAL_OPS " +
             "WHERE initiator = :initiator " +
             "AND opsType = :opsType " +
             "AND comments LIKE :comments%", nativeQuery = true)
	 Optional<List<RawApprovalsOps>>findByInitiatorAndOpsTypeAndCommentsLike(
	      @Param("initiator") String initiator,
	      @Param("opsType") String opsType,
	      @Param("comments") String comments);
	
	// Report Initiator general MC IND and Bulk  -- new req working
	 @Query(value = "SELECT * FROM CSC_ADMIN_APPROVAL_OPS " +
             "WHERE initiator = :initiator " +
             "AND opsType = :opsType " +
             "AND comments NOT LIKE %:comments%", nativeQuery = true)
	 Optional<List<RawApprovalsOps>>findByInitiatorAndOpsTypeAndCommentsLike2(
	      @Param("initiator") String initiator,
	      @Param("opsType") String opsType,
	      @Param("comments") String comments);
	 
	 
	 
	
	
   
	// report in Approver profile for general MC cards  -- old
	//Optional<List<RawApprovalsOps>> findByApprouverAndApprouverStatusIsNot(String approuver, String approuverStatus);
	// report in Approver profile for general MC cards  -- New
	Optional<List<RawApprovalsOps>> findByApprouverAndApprouverStatusIsNotAndCommentsIsNotLike(String approuver, String approuverStatus, String comments);
	

	//report in Approver profile for both IND and BLK
	 @Query(value = "SELECT * FROM CSC_ADMIN_APPROVAL_OPS " +
             "WHERE approuver = :approuver " +
             "AND approuverStatus = :approuverStatus " +
             "AND comments LIKE :comments%", nativeQuery = true)
	 Optional<List<RawApprovalsOps>>findByApprouverAndApprouverStatusIsNotAndCommentsIsLike(
	      @Param("approuver") String approuver,
	      @Param("approuverStatus") String approuverStatus,
	      @Param("comments") String comments);
	
	
	
	 
	 


		
}  
