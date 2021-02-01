package com.rs.ed.api.utils;

import com.rs.ed.api.model.ApplicantInfo;
import com.rs.ed.api.model.PlanInfo;

import lombok.NonNull;

/*
 * 1.Applicant must be job less to get the benefit from this plan.
 */


public class SNAPPlanValidator implements PlanValidator {

	@Override
	public PlanInfo evaluate(@NonNull ApplicantInfo applicantInfo) {
		PlanInfo response = new PlanInfo();
		response.setCaseNo(applicantInfo.getCaseNo());
		response.setPlanName("SNAP");
		if(!applicantInfo.getIsEmployeed()) {
			response.setPlanStartDate("2021-01-01");
			response.setPlanEndDate("2031-01-01");
			response.setPlanStatus("AP");
			response.setBenifitAmt(400.00);
		}else {
			response.setPlanStatus("DN");
			response.setDenialRsn("Salaried Employee");
		}
		
		return response;
	}


}
