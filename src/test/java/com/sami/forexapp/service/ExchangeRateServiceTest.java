package com.sami.forexapp.service;

import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;

import com.sami.forexapp.exception.ForexAppException;
import com.sami.forexapp.model.LatestRates;

@RunWith(MockitoJUnitRunner.class)
public class ExchangeRateServiceTest {

	private static final String BASE = "TRY";
	private static final String SYMBOL = "USD";

	@InjectMocks
	private ExchangeRateService exchangeRateService;

	@Mock
	private RestTemplate restTemplate;

	@Mock
	private Environment env;

	@Before
	public void init() {
		LatestRates latestRates = createLatestRates();

		when(env.getProperty(Mockito.anyString())).thenReturn("someapikey");
		when(restTemplate.getForObject(Mockito.anyString(), Mockito.any())).thenReturn(latestRates);
	}

	@Test
	public void exchangeRateShouldBeReturned() {

		Double rate = exchangeRateService.getExchangeRates(BASE, SYMBOL);

		Assert.assertTrue(rate == 2.0);
	}

	@Test(expected = ForexAppException.class)
	public void exchangeRateWithInvalidBaseShouldNotBeReturned() {

		exchangeRateService.getExchangeRates("ABC", SYMBOL);
	}

	@Test(expected = ForexAppException.class)
	public void exchangeRateWithInvalidSymbolShouldNotBeReturned() {

		exchangeRateService.getExchangeRates(BASE, "ABC");
	}

	private static LatestRates createLatestRates() {
		Map<String, Double> ratesMap = new HashMap<String, Double>();
		ratesMap.put(BASE, 5.0);
		ratesMap.put(SYMBOL, 10.0);
		LatestRates latestRates = new LatestRates(true, 0, "", "", ratesMap);
		return latestRates;
	}
}
