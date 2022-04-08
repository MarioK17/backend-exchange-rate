package com.mario.backendbasicbcp.service;

import com.mario.backendbasicbcp.dto.ExchangeRateDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;


public interface ExchangeRateService {


    Flux<ExchangeRateDto> filterExchangeRates(Long originalCurrency, Long targetCurrency, String exchangeDate);

    List<ExchangeRateDto> filterExchangeRatesNormal(String originalCurrency, String targetCurrency, String exchangeDate);

    Mono<ExchangeRateDto> saveExchangeRate(ExchangeRateDto exchangeRateDto);

    Mono<ExchangeRateDto> updateExchangeRate(ExchangeRateDto exchangeRateDto);

    Mono<Double> calculateExchangeRate(Long originalCurrency, Long targetCurrency, Double amount);
}
