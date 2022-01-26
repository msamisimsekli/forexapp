package com.sami.forexapp.service;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.sami.forexapp.controller.request.MakeConversionRequest;
import com.sami.forexapp.controller.response.MakeConversionResponse;
import com.sami.forexapp.entity.Conversion;
import com.sami.forexapp.exception.ForexAppException;
import com.sami.forexapp.repository.ConversionRepository;

@RunWith(MockitoJUnitRunner.class)
public class ConversionServiceTest {

	private static final Double EXCHANGE_RATE = 5.0;

	@InjectMocks
	private ConversionService conversionService;

	@Mock
	private ConversionRepository conversionRepo;

	@Mock
	private ExchangeRateService exchangeRateService;

	@Before
	public void init() {
		Conversion conversion = new Conversion();
		when(conversionRepo.save(Mockito.any())).thenReturn(conversion);
		when(exchangeRateService.getExchangeRates(Mockito.anyString(), Mockito.anyString())).thenReturn(EXCHANGE_RATE);
	}

	@Test
	public void conversionShouldBeMade() {
		Double amountToConvert = 10.0;
		MakeConversionResponse response = conversionService
				.makeConversion(new MakeConversionRequest("TRY", "USD", amountToConvert));

		assertNotNull(response);
		assertEquals(response.getAmountInSymbol(), amountToConvert * EXCHANGE_RATE);
	}

	@Test(expected = ForexAppException.class)
	public void conversionFailDueToInvalidAmount() {
		Double amountToConvert = -1.0;
		conversionService.makeConversion(new MakeConversionRequest("TRY", "USD", amountToConvert));
	}

	@Test(expected = ForexAppException.class)
	public void conversionRetrieveFailDueToInvalidId() {
		when(conversionRepo.findById(Mockito.anyLong())).thenReturn(Optional.empty());
		conversionService.getConversionById(1232134L);
	}
}
