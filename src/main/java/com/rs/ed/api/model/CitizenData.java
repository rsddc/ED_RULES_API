package com.rs.ed.api.model;

import lombok.Data;

@Data
public class CitizenData {
	
	private Integer caseNo;
	private String firstName;
	private String lastName;
	private String gender;
	private String dob;
	private String planName;
	private Boolean isEmployeed;
	private Boolean isGraduate;
	private Double income;
	private int kids;


}
