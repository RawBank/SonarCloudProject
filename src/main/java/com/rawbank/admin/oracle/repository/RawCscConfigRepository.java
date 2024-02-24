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
* @since 9 nov. 2022
* 
 */

package com.rawbank.admin.oracle.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rawbank.admin.oracle.entity.RawCscConfig;

/**
 * @author jacquesk
 *
 */
public interface RawCscConfigRepository extends JpaRepository<RawCscConfig, Long> {

	Optional<RawCscConfig> findByConfkey(String confkey);
}
