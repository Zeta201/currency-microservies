package com.zeta.microservicesv2.currencyexchangeservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {
	@Autowired
	private Environment environment;
	
	@Autowired
	private CurrencyExchangeRepository repository;
	
	
	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyExchange retrieveExchangeValue(@PathVariable String from,@PathVariable String to) {
		
		CurrencyExchange currencyExchange = repository.findByFromAndTo(from, to).orElseThrow(()->new RuntimeException("Invalid input"));
		
		currencyExchange.setEnvironment(environment.getProperty("local.server.port"));
		
		return currencyExchange;
	}
	
	@GetMapping("/currency-exchange/")
	public List<CurrencyExchange> retrieveAll(){
		return repository.findAll();
	}
}
