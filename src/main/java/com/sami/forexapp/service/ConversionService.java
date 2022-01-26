package com.sami.forexapp.service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.sami.forexapp.controller.request.MakeConversionRequest;
import com.sami.forexapp.controller.response.MakeConversionResponse;
import com.sami.forexapp.entity.Conversion;
import com.sami.forexapp.exception.ForexAppError;
import com.sami.forexapp.exception.ForexAppException;
import com.sami.forexapp.repository.ConversionRepository;

@Service
public class ConversionService {

	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(ConversionService.class);

	private final ConversionRepository conversionRepo;
	private final ExchangeRateService exchangeRateService;

	@Autowired
	public ConversionService(ConversionRepository conversionRepo, ExchangeRateService exchangeRateService) {
		this.conversionRepo = conversionRepo;
		this.exchangeRateService = exchangeRateService;
	}

	public MakeConversionResponse makeConversion(MakeConversionRequest request) {
		String base = request.getBase();
		String symbol = request.getSymbol();
		Double amount = request.getAmount();

		logger.info(
				String.format("makeConversion request arrived with base=%s symbol=%s amount=%s", base, symbol, amount));

		Double exchageRate = exchangeRateService.getExchangeRates(base, symbol);

		if (amount <= 0)
			throw new ForexAppException(ForexAppError.UNACCEPTED_AMOUNT_TO_CONVERT);

		Double amountInSymbol = exchageRate * amount;

		Conversion conversion = new Conversion(base, symbol, exchageRate, amount, amountInSymbol);
		Conversion savedConversion = conversionRepo.save(conversion);

		logger.info(String.format("A conversion has been saved to repo with id=%s", savedConversion.getId()));

		MakeConversionResponse response = new MakeConversionResponse(savedConversion.getId(), amountInSymbol);
		return response;
	}

	public Page<Conversion> getAllConversions(int pageIndex, int pageSize) {
		logger.info(
				String.format("getAllConversions request arrived with pageIndex=%s pageSize=%s", pageIndex, pageSize));

		Page<Conversion> conversionList = conversionRepo.findAll(PageRequest.of(pageIndex, pageSize));

		logger.info(String.format("Page with %s element returned", conversionList.getNumberOfElements()));
		return conversionList;
	}

	public Page<Conversion> getAllByConversionDateBetween(LocalDateTime beginDate, LocalDateTime endDate, int pageIndex,
			int pageSize) {
		logger.info(String.format(
				"getAllByConversionDateBetween request arrived withbeginDate=%s endDate=%s pageIndex=%s pageSize=%s",
				beginDate, endDate, pageIndex, pageSize));

		Page<Conversion> conversionList = conversionRepo.findAllByConversionDateBetween(beginDate, endDate,
				PageRequest.of(pageIndex, pageSize));

		logger.info(String.format("Page with %s element returned", conversionList.getNumberOfElements()));
		return conversionList;
	}

	public Conversion getConversionById(Long id) {
		logger.info(String.format("getConversionById request arrived with id=%s ", id));

		Optional<Conversion> conversionOptional = conversionRepo.findById(id);

		if (!conversionOptional.isPresent())
			throw new ForexAppException(ForexAppError.CONVERSION_ID_NOT_FOUND);

		Conversion conversion = conversionOptional.get();

		logger.info(String.format("Conversion returned"));
		return conversion;
	}

}
