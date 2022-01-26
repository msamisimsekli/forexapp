package com.sami.forexapp.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Schema(name = "Conversion", description = "Conversion model documentation")
public class Conversion {

	@Id
	@GeneratedValue
	@Schema(description = "Unique id field of conversion object")
	private Long id;

	@Schema(description = "Source currency for conversion")
	private String base;

	@Schema(description = "Target currency for conversion")
	private String symbol;

	@Schema(description = "Exchange rate for conversion")
	private Double rate;

	@Schema(description = "To be converted amount in source currency")
	private Double amountInBase;

	@Schema(description = "Converted amount in target currency conversion")
	private Double amountInSymbol;

	@CreationTimestamp
	@Schema(description = "Conversion date")
	private LocalDateTime conversionDate;

	public Conversion() {

	}

	public Conversion(String base, String symbol, Double rate, Double amountInBase, Double amountInSymbol) {
		super();
		this.base = base;
		this.symbol = symbol;
		this.rate = rate;
		this.amountInBase = amountInBase;
		this.amountInSymbol = amountInSymbol;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public Double getRate() {
		return rate;
	}

	public void setRate(Double rate) {
		this.rate = rate;
	}

	public Double getAmountInBase() {
		return amountInBase;
	}

	public void setAmountInBase(Double amountInBase) {
		this.amountInBase = amountInBase;
	}

	public Double getAmountInSymbol() {
		return amountInSymbol;
	}

	public void setAmountInSymbol(Double amountInSymbol) {
		this.amountInSymbol = amountInSymbol;
	}

	public LocalDateTime getConversionDate() {
		return conversionDate;
	}

	public void setConversionDate(LocalDateTime conversionDate) {
		this.conversionDate = conversionDate;
	}
}
