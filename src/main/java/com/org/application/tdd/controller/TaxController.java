package com.org.application.tdd.controller;

import com.org.application.tdd.services.ProcessTax;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TaxController {

	@Autowired
	ProcessTax processTax;


	@PostMapping(value = "/v1/getTax")
	public ResponseEntity calTax ( @RequestBody Map<String, String> requestParams){
		int income = Integer.parseInt(requestParams.get("income"));
		int months = Integer.parseInt(requestParams.get("months"));
		return ResponseEntity.ok().body(processTax.calculate(income,months));
	}
}
