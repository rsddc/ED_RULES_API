package com.rs.ed.api.controller;

import java.time.LocalDate;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rs.ed.api.model.ApplicantInfo;
import com.rs.ed.api.model.CitizenData;
import com.rs.ed.api.model.PlanInfo;
import com.rs.ed.api.service.EdRulesService;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class EdRulesController {

	private EdRulesService edRulesService;

	public EdRulesController(EdRulesService edRulesService) {
		super();
		this.edRulesService = edRulesService;
	}

	@PostMapping("/show_details")
	public ResponseEntity<PlanInfo> planDetails(@RequestBody @NonNull CitizenData citizenData) {
		log.info("citizen eligiblity determination request received by EdRulesController");
		ApplicantInfo info= new ApplicantInfo(citizenData.getCaseNo(), citizenData.getFirstName(),
				citizenData.getLastName(), citizenData.getGender(), LocalDate.parse(citizenData.getDob()),
				citizenData.getPlanName(), citizenData.getIsEmployeed(), 
				citizenData.getIsGraduate(), citizenData.getIncome(), citizenData.getKids());
		PlanInfo edResponse = edRulesService.applicantEligDetails(info);
		log.info("citizen eligiblity determination request completed by EdRulesController");
		return new ResponseEntity<PlanInfo>(edResponse, HttpStatus.OK);
	}

}
