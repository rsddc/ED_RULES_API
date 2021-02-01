package com.rs.ed.api.utils;

public class PlanUtilsFactory {
	
	public static PlanValidator getPlanInstance(String planType) {
		
		switch(planType) {
		
		case "SNAP":return new SNAPPlanValidator();
		case "CCAP":return new CCAPValidator();
		case "RIW": return new RIWValidator();
		}
		return null;
	}
	
	
	
}
