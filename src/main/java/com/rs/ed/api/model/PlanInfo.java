package com.rs.ed.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanInfo {
	
	private Integer caseNo;
	private String planName;
	private String planStatus;
	private String planStartDate;
	private String planEndDate;
	private String denialRsn;
	private Double benifitAmt;


}
