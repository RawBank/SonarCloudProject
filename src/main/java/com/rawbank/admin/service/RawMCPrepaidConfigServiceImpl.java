package com.rawbank.admin.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rawbank.admin.oracle.entity.RawMCPredaidConfig;
import com.rawbank.admin.oracle.repository.RawMCPrepaidConfigRepository;

@Service
public class RawMCPrepaidConfigServiceImpl implements RawMCPrepaidConfigService {
	
	@Autowired
	RawMCPrepaidConfigRepository rawMCPrepaidConfigRepository;
	
	private String trustedMcPrepaidAccount;
	List<RawMCPredaidConfig> trustedPrepaidAccount;

	@Override
	public String getTrustedPrepaidCreditAccount() {
		trustedPrepaidAccount = rawMCPrepaidConfigRepository.findAll();
		trustedMcPrepaidAccount = trustedPrepaidAccount.get(0).getTrustedCreditQccount();
		return trustedMcPrepaidAccount;
	}
	

	

}
