package com.rawbank.admin.oracle.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rawbank.admin.oracle.entity.RawBulkRecharge;

@Repository
public interface RawBulkRechargeRepository extends JpaRepository<RawBulkRecharge, Long>{
	
	List<RawBulkRecharge> findByNomFichier(String nomFichier);

}
