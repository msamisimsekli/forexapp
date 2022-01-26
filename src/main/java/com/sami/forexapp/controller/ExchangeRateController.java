package com.sami.forexapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sami.forexapp.service.ExchangeRateService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("exchangeRate")
@Tag(name = "ExchangeRateController", description = "Exchange rate Api documentation")
public class ExchangeRateController {

	private final ExchangeRateService service;

	@Autowired
	public ExchangeRateController(ExchangeRateService service) {
		this.service = service;
	}

	@GetMapping
	@Operation(description = "Getting exchange rate of base/symbol")
	public ResponseEntity<Double> getExchangeRate(@RequestParam String base, @RequestParam String symbol) {
		return ResponseEntity.ok(service.getExchangeRates(base, symbol));
	}
}
