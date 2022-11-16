package com.org.application.tdd.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.org.application.tdd.services.ProcessTax;
import org.junit.Before;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;


@WebMvcTest(TaxController.class)
public class TextControllerTest {

	@Autowired
	MockMvc mockMvc;
    @Autowired
	TaxController taxController;

    @MockBean
	ProcessTax processTax;

	ObjectMapper objectMapper = new ObjectMapper();

	@DisplayName("Verify TaxCal OK")
    @Test
	void testTaxCalStatus_ok() throws Exception {

		Map<String,String> input = new HashMap<>();
		input.put("income","10");
		input.put("months","5");

		String jsonString = objectMapper.writeValueAsString(input);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/v1/getTax").content(jsonString).contentType(
				MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		assertEquals("0.0",result.getResponse().getContentAsString());

	}
}
