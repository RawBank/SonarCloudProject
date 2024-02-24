package com.rawbank.admin.oracle.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rawbank.admin.oracle.entity.RawMCPredaidConfig;

@Repository
public interface RawMCPrepaidConfigRepository extends JpaRepository<RawMCPredaidConfig, Integer>{

}
