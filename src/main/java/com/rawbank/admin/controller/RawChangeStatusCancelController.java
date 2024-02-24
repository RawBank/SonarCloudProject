
package com.rawbank.admin.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.rawbank.admin.oracle.entity.RawChangeStatusCancel;
import com.rawbank.admin.service.RawChangeStatusCancelServiceImpl;
import com.rawbank.admin.utility.RawUtility;

@Controller
public class RawChangeStatusCancelController {
	
	@Autowired
	RawChangeStatusCancelServiceImpl rawChangeStatusCancelServiceImpl;
	
	
	@GetMapping("/loadpendingcancelmc")
	public ModelAndView getAllPendingMc(ModelMap model) {
		
		
	List<RawChangeStatusCancel> listChangeStausCancelRec = rawChangeStatusCancelServiceImpl.findAllCancelRecords();
	model.addAttribute("cancelCardsInfo", listChangeStausCancelRec);

	return new ModelAndView("load_cancel_MC");
		
	}
	
	
	@GetMapping("/loadpendingcancelmcInitiator")
	public ModelAndView getAllPendingMcInitiator(ModelMap model) {
		
		
	List<RawChangeStatusCancel> listChangeStausCancelRec = rawChangeStatusCancelServiceImpl.findAllCancelRecords();
	model.addAttribute("cancelCardsInfo", listChangeStausCancelRec);
	
	model.put("name", RawUtility.getLoggedinUserName() + " [" + RawUtility.getLoggedinUserRole() + "]");
	model.put("role", RawUtility.getLoggedinUserRole());

	return new ModelAndView("load_cancel_MC_initiator");
		
	}

}
