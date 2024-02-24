package com.rawbank.admin.oracle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rawbank.admin.oracle.entity.RawCardStatusCode;

@Repository
public interface RawCardStatusCodeRepository extends JpaRepository<RawCardStatusCode, Long> {

	RawCardStatusCode findByNumCode(String numCode);

	List<RawCardStatusCode> findByStatusIgnoreCase(String status);
	    
	RawCardStatusCode findByDescription(String description);
	
}
