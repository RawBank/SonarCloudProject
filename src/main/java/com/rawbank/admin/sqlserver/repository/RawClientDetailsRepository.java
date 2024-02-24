/**
 * 
 */
package com.rawbank.admin.sqlserver.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rawbank.admin.sqlserver.entity.RawClientDetails;

/**
 * @author jacquesk
 *
 */
@Repository
public interface RawClientDetailsRepository  extends JpaRepository<RawClientDetails, String>{

	 Optional<RawClientDetails> findByClientNumber(String clientNumber);
}
