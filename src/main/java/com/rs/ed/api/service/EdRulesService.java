package com.rs.ed.api.service;

import com.rs.ed.api.model.ApplicantInfo;
import com.rs.ed.api.model.PlanInfo;

import lombok.NonNull;

public interface EdRulesService {
	PlanInfo applicantEligDetails(@NonNull ApplicantInfo applicantInfo);
}
