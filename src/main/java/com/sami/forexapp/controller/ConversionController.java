package com.sami.forexapp.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sami.forexapp.controller.request.MakeConversionRequest;
import com.sami.forexapp.controller.response.MakeConversionResponse;
import com.sami.forexapp.entity.Conversion;
import com.sami.forexapp.service.ConversionService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("conversion")
@Tag(name = "ConversionController", description = "Conversion Api documentation")
public class ConversionController {

	private final ConversionService conversionService;

	@Autowired
	public ConversionController(ConversionService conversionService) {
		this.conversionService = conversionService;
	}

	@PostMapping
	@Operation(description = "Creating and saving new conversion")
	public ResponseEntity<MakeConversionResponse> makeConversion(@RequestBody MakeConversionRequest request) {
		return ResponseEntity.ok(conversionService.makeConversion(request));
	}

	@GetMapping("getAll")
	@Operation(description = "Getting all conversion with paging")
	public ResponseEntity<Page<Conversion>> getAllConversions(@RequestParam int pageIndex, @RequestParam int pageSize) {
		return ResponseEntity.ok(conversionService.getAllConversions(pageIndex, pageSize));
	}

	@GetMapping("getById")
	@Operation(description = "Getting conversion by its id")
	public ResponseEntity<Conversion> getConversionById(@RequestParam Long id) {
		return ResponseEntity.ok(conversionService.getConversionById(id));
	}

	@GetMapping("getBetween")
	@Operation(description = "Getting all conversion between a date interval with paging")
	public ResponseEntity<Page<Conversion>> getConversionsBetweenDates(
			@RequestParam("beginDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime beginDate,
			@RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate,
			@RequestParam int pageIndex, @RequestParam int pageSize) {
		return ResponseEntity
				.ok(conversionService.getAllByConversionDateBetween(beginDate, endDate, pageIndex, pageSize));
	}
}
