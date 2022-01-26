package com.sami.forexapp.controller.request;

public class MakeConversionRequest {

	private String base;
	private String symbol;
	private Double amount;

	public MakeConversionRequest(String base, String symbol, Double amount) {
		super();
		this.base = base;
		this.symbol = symbol;
		this.amount = amount;
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

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}
}
