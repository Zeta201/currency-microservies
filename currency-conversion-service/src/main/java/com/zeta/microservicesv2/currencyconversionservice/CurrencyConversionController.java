package com.zeta.microservicesv2.currencyconversionservice;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyConversionController {

	@Autowired
	private CurrencyExchangeProxy proxy;

	@GetMapping("/currency-conversion/from/{from}/to/{to}/quantity/{quantity}")
	public CurrencyConversion calculateCurrencyConversion(@PathVariable String from, @PathVariable String to,
			@PathVariable BigDecimal quantity) {
		CurrencyConversion currencyConversion = proxy.retrieveExchangeValue(from, to);
		return CurrencyConversion.builder()
				.id(currencyConversion.getId())
				.from(from)
				.to(to)
				.conversionMultiple(currencyConversion.getConversionMultiple())
				.totlaCalculatedAmount(quantity.multiply(currencyConversion.getConversionMultiple()))
				.quantity(quantity)
				.environment(currencyConversion.getEnvironment())
				.build();
	}
}
