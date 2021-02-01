package com.rs.ed.api.utils;

import com.rs.ed.api.model.ApplicantInfo;
import com.rs.ed.api.model.PlanInfo;

import lombok.NonNull;

/*
 * 1.Applicant must be graduate to get the benefit from this plan.
 */


public class RIWValidator  implements PlanValidator{

	@Override
	public PlanInfo evaluate(@NonNull ApplicantInfo applicantInfo) {
		PlanInfo response = new PlanInfo();
		response.setCaseNo(applicantInfo.getCaseNo());
		response.setPlanName("RIW");
		if(applicantInfo.getIsGraduate()) {
			response.setPlanStartDate("2021-01-01");
			response.setPlanEndDate("2031-01-01");
			response.setPlanStatus("AP");
			response.setBenifitAmt(300.99);
		}else {
			response.setPlanStatus("DN");
			response.setDenialRsn("Minimum Garduates are required to avail this plan");
		}
		
		return response;
	}

}
