package com.sami.forexapp.model;

import java.util.Map;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "LatestRates", description = "Latest exchange rates documentation")
public class LatestRates {

	@Schema(description = "Exchage rate request's success state")
	private boolean success;

	@Schema(description = "Timestamp for exchange request time")
	private long timestamp;

	@Schema(description = "Source currency which is always EUR")
	private String base;

	@Schema(description = "Date")
	private String date;

	@Schema(description = "Map for exchange rate for each currency based on EUR")
	private Map<String, Double> rates;

	public LatestRates(boolean success, long timestamp, String base, String date, Map<String, Double> rates) {
		super();
		this.success = success;
		this.timestamp = timestamp;
		this.base = base;
		this.date = date;
		this.rates = rates;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = base;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Map<String, Double> getRates() {
		return rates;
	}

	public void setRates(Map<String, Double> rates) {
		this.rates = rates;
	}

	@Override
	public String toString() {
		return "LatestRates [success=" + success + ", timestamp=" + timestamp + ", base=" + base + ", date=" + date
				+ ", rates=" + rates + "]";
	}
}
