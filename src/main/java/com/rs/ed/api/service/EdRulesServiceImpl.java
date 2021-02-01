package com.rs.ed.api.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.drools.compiler.compiler.DroolsParserException;
import org.drools.compiler.compiler.PackageBuilder;
import org.drools.core.RuleBase;
import org.drools.core.RuleBaseFactory;
import org.drools.core.WorkingMemory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rs.ed.api.model.ApplicantInfo;
import com.rs.ed.api.model.PlanInfo;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class EdRulesServiceImpl implements EdRulesService {
	/*
	 * @Override public EdRulesResponse applicantEligDetails(@NonNull ApplicantInfo
	 * applicantInfo) {
	 * 
	 * String plan = applicantInfo.getPlanName(); PlanValidator planInstance =
	 * PlanUtilsFactory.getPlanInstance(plan); return
	 * planInstance.evaluate(applicantInfo);
	 * 
	 * }
	 */

	
	@Value("${drools.files.path}")
	private String droolFileBaseLoc;
	
	


	@Override
	public PlanInfo applicantEligDetails(@NonNull ApplicantInfo applicantInfo){
		String drlFile = droolFileBaseLoc+applicantInfo.getPlanName()+".drl";
		log.debug("Plan file selected by citizen is {}",drlFile);
		PlanInfo response = new PlanInfo();
		response.setPlanName(applicantInfo.getPlanName());
		response.setCaseNo(applicantInfo.getCaseNo());
		InputStream is = getClass().getResourceAsStream(drlFile);
		
		InputStreamReader reader = new InputStreamReader(is);

		PackageBuilder pkg = new PackageBuilder();
		try {
			pkg.addPackageFromDrl(reader);
		} catch (DroolsParserException | IOException e) {
			e.printStackTrace();
		}
		
		org.drools.core.rule.Package rulesPkg = pkg.getPackage();
		RuleBase ruleBase = RuleBaseFactory.newRuleBase();
		ruleBase.addPackage(rulesPkg);
		
		WorkingMemory workingMemory = ruleBase.newStatefulSession();
		workingMemory.insert(applicantInfo);
		workingMemory.insert(response);
		workingMemory.fireAllRules();
		log.debug("Received benifits {}",response);

		return response;
	}

}