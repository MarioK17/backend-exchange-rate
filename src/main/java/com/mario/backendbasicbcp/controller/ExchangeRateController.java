package com.mario.backendbasicbcp.controller;

import com.mario.backendbasicbcp.dto.ExchangeRateDto;
import com.mario.backendbasicbcp.service.ExchangeRateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/exchangeRate")
public class ExchangeRateController {

    private final ExchangeRateService exchangeRateService;

    public ExchangeRateController(ExchangeRateService exchangeRateService) {
        this.exchangeRateService = exchangeRateService;
    }

    @GetMapping(value = "", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ExchangeRateDto> filterExchangeRates(@RequestParam("originalCurrency") Long originalCurrency,
                                                       @RequestParam("targetCurrency") Long targetCurrency,
                                                       @RequestParam("exchangeDate") String exchangeDate) {

        return exchangeRateService.filterExchangeRates(originalCurrency, targetCurrency, exchangeDate);
    }

    @GetMapping(value = "/calculate")
    public Mono<Double> calculateExchangeRate(@RequestParam("originalCurrency") Long originalCurrency,
                                                  @RequestParam("targetCurrency") Long targetCurrency,
                                                  @RequestParam("amount") Double amount) {

        return exchangeRateService.calculateExchangeRate(originalCurrency, targetCurrency, amount);
    }

    @PostMapping(value = "")
    public ResponseEntity<Void> saveExchangeRate(@RequestBody ExchangeRateDto exchangeRateDto) {

        exchangeRateService.saveExchangeRate(exchangeRateDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping(value = "")
    public ResponseEntity<Void> updateExchangeRate(@RequestBody ExchangeRateDto exchangeRateDto) {

        this.exchangeRateService.updateExchangeRate(exchangeRateDto);

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
