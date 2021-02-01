package com.rs.ed.api.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rs.ed.api.model.ApplicantInfo;
import com.rs.ed.api.model.PlanInfo;
import com.rs.ed.api.service.EdRulesService;

@WebMvcTest(EdRulesController.class)
class EdRulesControllerTest {

	private ApplicantInfo proxyApplicantPass, proxyApplicantFail;
	private PlanInfo proxyResponsePass, proxyResponseFail;

	@Autowired
	MockMvc mockMvc;
	@MockBean
	EdRulesService service;

	@BeforeEach
	void setUp() throws Exception {

//		proxyApplicantPass = new ApplicantInfo(100, "Donlad", "Trumph", "male",LocalDate.now(),
//				"SNAP", false, false, 100.00, 3);
////		proxyApplicantFail = new ApplicantInfo(200, "Joe", "Biden", "male",null/* LocalDate.of(1930,4,01)*/, "CCAP",
////				false, false, 100.00, 20);
//		proxyResponsePass = new EdRulesResponse(100,"SNAP","AP",LocalDate.now(),
//				LocalDate.now(), null,100.00);
////		proxyResponseFail = new EdRulesResponse(200, "CCAP", "DN", null, null, "can't support",0.0);
	}

	@Test
	void testPlanDetails() throws Exception {

		when(service.applicantEligDetails(proxyApplicantPass)).thenReturn(proxyResponsePass);
		ObjectMapper mapper = new ObjectMapper();
		String writeValueAsString = mapper.writeValueAsString(proxyApplicantPass);
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/show_details")
				.contentType(MediaType.APPLICATION_JSON).content(writeValueAsString);

		mockMvc.perform(requestBuilder).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON));

	}

}
