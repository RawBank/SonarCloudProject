package com.rawbank.admin.service;

import java.util.List;

import com.rawbank.admin.oracle.entity.RawChangeStatusCancel;

public interface RawChangeStatusCancelService {
	
	void saveChangeStatusCancelRecord(RawChangeStatusCancel rawChangeStatusCancel);
	
	List<RawChangeStatusCancel> findAllCancelRecords();
	
	void updateCancelRecord(
			String reqCancelId, 
			String cValidator, 
			String cDateValidation, 
			String cStatusOpCsc, 
			String cReferenceCode, 
			String cStatusAmpl, 
			String cCancelEventStatus, 
			String cCancelComment);
	
	void updateCancelRecordWithProvision(
			String reqCancelId, 
			String cValidator, 
			String cDateValidation, 
			String cStatusOpCsc, 
			String cReferenceCodeA, 
			String cReferenceCodeB, 
			String cStatusAmplA, 
			String cStatusAmplB,
			String cCancelEventStatus, 
			String cCancelComment);
	
	void updateCancelRecordWithProvisionRetry(
			String reqCancelId, 
			String cValidator, 
			String cDateValidation, 
			String cReferenceCodeA, 
			String cReferenceCodeB, 
			String cStatusAmplA, 
			String cStatusAmplB,
			String cCancelEventStatus, 
			String cCancelComment);
	
	void updateCancelRecordAmplitudeEcritureAetBankRefCodeA(
			String reqCancelId, 
			String cReferenceCodeA,
			String cStatusAmplA,
			String cCancelEventStatus);
	
	void updateCancelRecordAmplitudeEcritureBetBankRefCodeB(
			String reqCancelId, 
			String cReferenceCodeB,
			String cStatusAmplB,
			String cCancelEventStatus
			);
	
	
	void updateCancelRecordCscStatus(
			String reqCancelId, 
			String cValidator, 
			String cDateValidation, 
			String cStatusOpCsc,
			String cCancelEventStatus
			);
	
	
	void updateCancelRecordAmplitudeEcritureAetB(
			String reqCancelId,
			String cStatusAmplA,
			String cStatusAmplB,
			String cCancelEventStatus);
	
	
	void updateCancelRecordAmplitudeEcritureA(
			String reqCancelId, 
			String cStatusAmplA,
			String cCancelEventStatus);
	
	void updateCancelRecordAmplitudeEcritureB(
			String reqCancelId, 
			String cStatusAmplB,
			String cCancelEventStatus);
	
	
	void updateCancelRecordAsRejected(
			String reqCancelId, 
			String cValidator, 
			String cDateValidation,
			String cCancelComment,
			String cCancelEventStatus
			);
	
	
}
