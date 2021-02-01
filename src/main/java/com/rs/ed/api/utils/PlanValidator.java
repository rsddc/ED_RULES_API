package com.rs.ed.api.utils;

import com.rs.ed.api.model.ApplicantInfo;
import com.rs.ed.api.model.PlanInfo;

import lombok.NonNull;

public interface PlanValidator {

	PlanInfo evaluate(@NonNull ApplicantInfo applicantInfo);

}
