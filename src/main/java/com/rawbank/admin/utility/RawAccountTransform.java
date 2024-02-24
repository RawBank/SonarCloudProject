/**
 * © Copyright 2021 Rawbank. All Rights Reserved.
 * 
 * Change Section:
 * Programmer			Date
 * Krishna				7/20/2022
 * Initial Coding.
 * 
 * 
 * Created By:
 * @author krishna
 * @since Jul 20, 2022
 * 
 */
package com.rawbank.admin.utility;

import com.rawbank.admin.bo.RawAccountBo;
import com.rawbank.admin.client.stubs.getAccountList.GetAccountResponse;

/**
 * @author krishna
 *
 */
public class RawAccountTransform {

	
	
	private RawAccountTransform() {
		super();
	}

	public static RawAccountBo getAccountBo(GetAccountResponse getAccountResponse) {
		RawAccountBo rawAccountBo = new RawAccountBo();
		rawAccountBo.setAccountNumber(getAccountResponse.getAccount().getAccountNumber().getInternalFormatAccountOurBranch().getAccount());
		rawAccountBo.setBillingAccountBalance(String.valueOf(getAccountResponse.getIndicativeBalance()));
		rawAccountBo.setCustomerNumber(getAccountResponse.getAccount().getCustomer().getCustomer().getCustomerNumber());
		return rawAccountBo;
	}
}
