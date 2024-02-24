package com.rawbank.admin.oracle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rawbank.admin.oracle.entity.RawChangeStatusCancel;

@Repository
public interface RawChangeStatusCancelRepository extends JpaRepository<RawChangeStatusCancel, Long> {
	
	RawChangeStatusCancel findByreqCancelId(String reqCancelId);
	
	List<RawChangeStatusCancel> findAllByOrderByIdDesc();
	


}
