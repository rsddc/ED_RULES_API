package com.rs.ed.api.utils;

import com.rs.ed.api.model.ApplicantInfo;
import com.rs.ed.api.model.PlanInfo;

import lombok.NonNull;

/*
 * 1.Applicant must be having kids to get the benefit from this plan.
 */


public class CCAPValidator  implements PlanValidator{

	@Override
	public PlanInfo evaluate(@NonNull ApplicantInfo applicantInfo) {
		PlanInfo response = new PlanInfo();
		response.setCaseNo(applicantInfo.getCaseNo());
		response.setPlanName("CCAP");
		if(applicantInfo.getKids()>0) {
			response.setPlanStartDate("2021-01-01");
			response.setPlanEndDate("2031-12-01");
			response.setPlanStatus("AP");
			response.setBenifitAmt(200.50);
		}else {
			response.setPlanStatus("DN");
			response.setDenialRsn("Not eligible due to not having kids");
		}
		
		return response;
	}

}
