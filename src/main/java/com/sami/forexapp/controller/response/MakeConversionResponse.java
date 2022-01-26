package com.sami.forexapp.controller.response;

public class MakeConversionResponse {

	private Long conversionId;
	private Double amountInSymbol;

	public MakeConversionResponse(Long conversionId, Double amountInSymbol) {
		super();
		this.conversionId = conversionId;
		this.amountInSymbol = amountInSymbol;
	}

	public Long getConversionId() {
		return conversionId;
	}

	public void setConversionId(Long conversionId) {
		this.conversionId = conversionId;
	}

	public Double getAmountInSymbol() {
		return amountInSymbol;
	}

	public void setAmountInSymbol(Double amountInSymbol) {
		this.amountInSymbol = amountInSymbol;
	}
}
