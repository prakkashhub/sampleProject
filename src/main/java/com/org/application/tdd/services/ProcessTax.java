package com.org.application.tdd.services;

import org.springframework.stereotype.Service;

@Service
public class ProcessTax {

	private  double  taxPer = 0.3;
	public double calculate(int income, int months) {

		return income * months * taxPer;
	}
}
