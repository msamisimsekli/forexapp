package com.sami.forexapp.service;

import java.util.Map;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestTemplate;

import com.sami.forexapp.exception.ForexAppError;
import com.sami.forexapp.exception.ForexAppException;
import com.sami.forexapp.model.LatestRates;

@Service
public class ExchangeRateService {

	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(ExchangeRateService.class);

	@Autowired
	private Environment env;

	@Autowired
	private RestTemplate restTemplate;

	public Double getExchangeRates(String base, String symbol) {

		logger.info(String.format("A request arrived with base=%s symbol=%s", base, symbol));

		// I only have free plan apiKey, I use free API endpoint to get all symbols
		// based EUR
		String apiKey = env.getProperty("apikey");
		String requestUrl = String.format("http://api.exchangeratesapi.io/v1/latest?access_key=%s", apiKey);

		try {
			LatestRates latestRates = this.restTemplate.getForObject(requestUrl, LatestRates.class);
			Map<String, Double> rates = latestRates.getRates();

			if (!rates.containsKey(base))
				throw new ForexAppException(ForexAppError.UNACCEPTED_BASE_VALUE);

			if (!rates.containsKey(symbol))
				throw new ForexAppException(ForexAppError.UNACCEPTED_SYMBOL_VALUE);

			Double baseBasedEur = rates.get(base);
			Double symbolBasedEur = rates.get(symbol);

			logger.info(String.format("Exchange rate calculated"));

			return symbolBasedEur / baseBasedEur;

		} catch (HttpStatusCodeException | ArithmeticException e) {
			throw new ForexAppException(ForexAppError.EXCHAGE_RATES_API_ERROR);
		}
	}

}
