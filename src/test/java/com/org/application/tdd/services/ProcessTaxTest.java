package com.org.application.tdd.services;

import com.org.application.tdd.services.ProcessTax;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;


public class ProcessTaxTest {

	@InjectMocks
	ProcessTax processTax;

	@BeforeEach
	void init()
	{
		MockitoAnnotations.openMocks(this);

	}

	@Test
	void testTaxCal()
	{
		int income =10;
		int months = 5;
		double totalTax = processTax.calculate(income,months);

		Assertions.assertNotEquals(10.0, totalTax);
		Assertions.assertEquals(15.0, totalTax);


	}

}
