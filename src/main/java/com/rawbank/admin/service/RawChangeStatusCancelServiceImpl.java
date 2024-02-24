package com.rawbank.admin.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rawbank.admin.oracle.entity.RawChangeStatusCancel;
import com.rawbank.admin.oracle.repository.RawChangeStatusCancelRepository;

@Service
public class RawChangeStatusCancelServiceImpl implements RawChangeStatusCancelService{
	
	@Autowired
	RawChangeStatusCancelRepository rawChangeStatusCancelRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(RawChangeStatusCancelService.class);

	@Override
	public void saveChangeStatusCancelRecord(RawChangeStatusCancel rawChangeStatusCancel) {
		rawChangeStatusCancelRepository.save(rawChangeStatusCancel);
	}

	@Override
	public List<RawChangeStatusCancel> findAllCancelRecords() {
		//return rawChangeStatusCancelRepository.findAll();
		//logger.info(" ============ Get all cancel records  " + rawChangeStatusCancelRepository.findAllByOrderByIdDesc());
		
		return rawChangeStatusCancelRepository.findAllByOrderByIdDesc();
		
		
	}

	@Override
	public void updateCancelRecord(String reqCancelId, String cValidator, String cDateValidation, String cStatusOpCsc, String cReferenceCodeA, String cStatusAmplA, String cCancelEventStatus, String cCancelComment) {
		RawChangeStatusCancel rawChangeStatusCancel = rawChangeStatusCancelRepository.findByreqCancelId(reqCancelId);
		if(rawChangeStatusCancel.equals(null)) {
			logger.info(" =============================== rawChangeStatusCancel object is EMPTY" );
		}else {
			rawChangeStatusCancel.setValidator(cValidator);
			rawChangeStatusCancel.setDateValidation(cDateValidation);
			rawChangeStatusCancel.setStatusOpCsc(cStatusOpCsc);
			rawChangeStatusCancel.setTxnReferenceCodeBankA(cReferenceCodeA);
			rawChangeStatusCancel.setStatusOpAmplitudeA(cStatusAmplA);
			rawChangeStatusCancel.setCancelEventStatus(cCancelEventStatus);
			rawChangeStatusCancel.setCancelComment(cCancelComment);
			
			rawChangeStatusCancelRepository.save(rawChangeStatusCancel);
			
			logger.info(" ========= Complete Card without Provision DB save object====== " + rawChangeStatusCancel);

		}
		
		
	}

	@Override
	public void updateCancelRecordWithProvision(String reqCancelId, String cValidator, String cDateValidation,
			String cStatusOpCsc, String cReferenceCodeA, String cReferenceCodeB, String cStatusAmplA,
			String cStatusAmplB, String cCancelEventStatus, String cCancelComment) {
		RawChangeStatusCancel rawChangeStatusCancel = rawChangeStatusCancelRepository.findByreqCancelId(reqCancelId);
		if(rawChangeStatusCancel.equals(null)) {
			logger.info(" =============================== rawChangeStatusCancel object is EMPTY" );
		}else {
			rawChangeStatusCancel.setValidator(cValidator);
			rawChangeStatusCancel.setDateValidation(cDateValidation);
			rawChangeStatusCancel.setStatusOpCsc(cStatusOpCsc);
			rawChangeStatusCancel.setTxnReferenceCodeBankA(cReferenceCodeA);
			rawChangeStatusCancel.setTxnReferenceCodeBankB(cReferenceCodeB);
			rawChangeStatusCancel.setStatusOpAmplitudeA(cStatusAmplA);
			rawChangeStatusCancel.setStatusOpAmplitudeB(cStatusAmplB);
			rawChangeStatusCancel.setCancelEventStatus(cCancelEventStatus);
			rawChangeStatusCancel.setCancelComment(cCancelComment);
			
			rawChangeStatusCancelRepository.save(rawChangeStatusCancel);
			
			logger.info(" ========= Complete Card with Provision DB save object====== " + rawChangeStatusCancel);

		}
		
	}
	
	
	@Override
	public void updateCancelRecordWithProvisionRetry(String reqCancelId, String cValidator, String cDateValidation,
			 String cReferenceCodeA, String cReferenceCodeB, String cStatusAmplA,
			String cStatusAmplB, String cCancelEventStatus, String cCancelComment) {
		RawChangeStatusCancel rawChangeStatusCancel = rawChangeStatusCancelRepository.findByreqCancelId(reqCancelId);
		if(rawChangeStatusCancel.equals(null)) {
			logger.info(" =============================== rawChangeStatusCancel object is EMPTY" );
		}else {
			rawChangeStatusCancel.setValidator(cValidator);
			rawChangeStatusCancel.setDateValidation(cDateValidation);
			rawChangeStatusCancel.setTxnReferenceCodeBankA(cReferenceCodeA);
			rawChangeStatusCancel.setTxnReferenceCodeBankB(cReferenceCodeB);
			rawChangeStatusCancel.setStatusOpAmplitudeA(cStatusAmplA);
			rawChangeStatusCancel.setStatusOpAmplitudeB(cStatusAmplB);
			rawChangeStatusCancel.setCancelEventStatus(cCancelEventStatus);
			rawChangeStatusCancel.setCancelComment(cCancelComment);
			
			rawChangeStatusCancelRepository.save(rawChangeStatusCancel);
			
			logger.info(" ========= Complete Card with Provision Retry DB save object====== " + rawChangeStatusCancel);

		}
		
	}

	@Override
	public void updateCancelRecordAmplitudeEcritureAetBankRefCodeA(String reqCancelId, String cReferenceCodeA, String cStatusAmplA, String cCancelEventStatus) {
		RawChangeStatusCancel rawChangeStatusCancel = rawChangeStatusCancelRepository.findByreqCancelId(reqCancelId);
		if(rawChangeStatusCancel.equals(null)) {
			logger.info(" =============================== rawChangeStatusCancel object is EMPTY" );
		}else {
			rawChangeStatusCancel.setTxnReferenceCodeBankA(cReferenceCodeA);
			rawChangeStatusCancel.setStatusOpAmplitudeA(cStatusAmplA);
			rawChangeStatusCancel.setCancelEventStatus(cCancelEventStatus);
			
			rawChangeStatusCancelRepository.save(rawChangeStatusCancel);
			
			logger.info(" ========= Amplitude Premiere Ecriture et Bank Ref CodeA DB save object====== " + rawChangeStatusCancel);

		}
	}

	@Override
	public void updateCancelRecordAmplitudeEcritureBetBankRefCodeB(String reqCancelId, String cReferenceCodeB, String cStatusAmplB, String cCancelEventStatus) {
		RawChangeStatusCancel rawChangeStatusCancel = rawChangeStatusCancelRepository.findByreqCancelId(reqCancelId);
		if(rawChangeStatusCancel.equals(null)) {
			logger.info(" =============================== rawChangeStatusCancel object is EMPTY" );
		}else {
			rawChangeStatusCancel.setTxnReferenceCodeBankB(cReferenceCodeB);
			rawChangeStatusCancel.setStatusOpAmplitudeB(cStatusAmplB);
			rawChangeStatusCancel.setCancelEventStatus(cCancelEventStatus);
			
			rawChangeStatusCancelRepository.save(rawChangeStatusCancel);
			
			logger.info(" ========= Amplitude Deuxieme Ecriture et Bank Ref CodeB DB save object====== " + rawChangeStatusCancel);

		}
		
	}

	@Override
	public void updateCancelRecordCscStatus(String reqCancelId, String cValidator, String cDateValidation,
			String cStatusOpCsc, String cCancelEventStatus) {
		RawChangeStatusCancel rawChangeStatusCancel = rawChangeStatusCancelRepository.findByreqCancelId(reqCancelId);
		if(rawChangeStatusCancel.equals(null)) {
			logger.info(" =============================== rawChangeStatusCancel object is EMPTY" );
		}else {
			rawChangeStatusCancel.setValidator(cValidator);
			rawChangeStatusCancel.setDateValidation(cDateValidation);
			rawChangeStatusCancel.setStatusOpCsc(cStatusOpCsc);
			rawChangeStatusCancel.setCancelEventStatus(cCancelEventStatus);
			
			rawChangeStatusCancelRepository.save(rawChangeStatusCancel);
			
			logger.info(" ========= CSC DB save  object====== " + rawChangeStatusCancel);

		}
		
	}

	@Override
	public void updateCancelRecordAmplitudeEcritureAetB(String reqCancelId, String cStatusAmplA, String cStatusAmplB, String cCancelEventStatus) {
		RawChangeStatusCancel rawChangeStatusCancel = rawChangeStatusCancelRepository.findByreqCancelId(reqCancelId);
		if(rawChangeStatusCancel.equals(null)) {
			logger.info(" =============================== rawChangeStatusCancel object is EMPTY" );
		}else {
			rawChangeStatusCancel.setStatusOpAmplitudeA(cStatusAmplA);
			rawChangeStatusCancel.setStatusOpAmplitudeB(cStatusAmplB);
			rawChangeStatusCancel.setCancelEventStatus(cCancelEventStatus);
			
			rawChangeStatusCancelRepository.save(rawChangeStatusCancel);
			
			logger.info(" ========= Amplitude Premiere et Deuxieme Ecriture DB save object====== " + rawChangeStatusCancel);

		}
		
	}

	@Override
	public void updateCancelRecordAmplitudeEcritureA(String reqCancelId, String cStatusAmplA, String cCancelEventStatus) {
		RawChangeStatusCancel rawChangeStatusCancel = rawChangeStatusCancelRepository.findByreqCancelId(reqCancelId);
		if(rawChangeStatusCancel.equals(null)) {
			logger.info(" =============================== rawChangeStatusCancel object is EMPTY" );
		}else {
			rawChangeStatusCancel.setStatusOpAmplitudeA(cStatusAmplA);
			rawChangeStatusCancel.setCancelEventStatus(cCancelEventStatus);
			
			rawChangeStatusCancelRepository.save(rawChangeStatusCancel);
			
			logger.info(" ========= Amplitude Premiere Ecriture DB save object====== " + rawChangeStatusCancel);

		}
		
	}

	@Override
	public void updateCancelRecordAmplitudeEcritureB(String reqCancelId, String cStatusAmplB, String cCancelEventStatus) {
		RawChangeStatusCancel rawChangeStatusCancel = rawChangeStatusCancelRepository.findByreqCancelId(reqCancelId);
		if(rawChangeStatusCancel.equals(null)) {
			logger.info(" =============================== rawChangeStatusCancel object is EMPTY" );
		}else {
		
			rawChangeStatusCancel.setStatusOpAmplitudeB(cStatusAmplB);
			rawChangeStatusCancel.setCancelEventStatus(cCancelEventStatus);
			
			rawChangeStatusCancelRepository.save(rawChangeStatusCancel);
			
			logger.info(" ========= Amplitude Deuxieme Ecriture DB save object====== " + rawChangeStatusCancel);

		}
		
	}
	
	
	@Override
	public void updateCancelRecordAsRejected(String reqCancelId, String cValidator, String cDateValidation,String cCancelComment,String cCancelEventStatus) {
		RawChangeStatusCancel rawChangeStatusCancel = rawChangeStatusCancelRepository.findByreqCancelId(reqCancelId);
		if(rawChangeStatusCancel.equals(null)) {
			logger.info(" =============================== rawChangeStatusCancel object is EMPTY" );
		}else {
			rawChangeStatusCancel.setValidator(cValidator);
			rawChangeStatusCancel.setDateValidation(cDateValidation);
			rawChangeStatusCancel.setCancelComment(cCancelComment);
			rawChangeStatusCancel.setCancelEventStatus(cCancelEventStatus);
			
			rawChangeStatusCancelRepository.save(rawChangeStatusCancel);
			
			logger.info(" ========= Cancel card Reject object save  object====== " + rawChangeStatusCancel);

		}
		
	}

	
	
	

}
