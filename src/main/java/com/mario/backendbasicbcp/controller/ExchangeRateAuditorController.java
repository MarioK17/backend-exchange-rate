package com.mario.backendbasicbcp.controller;

import com.mario.backendbasicbcp.dto.ExchangeAuditorDto;
import com.mario.backendbasicbcp.dto.ExchangeRateDto;
import com.mario.backendbasicbcp.service.ExchangeRateAuditorService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/exchangeAuditor")
public class ExchangeRateAuditorController {

    private final ExchangeRateAuditorService exchangeRateAuditorService;

    public ExchangeRateAuditorController(ExchangeRateAuditorService exchangeRateAuditorService) {
        this.exchangeRateAuditorService = exchangeRateAuditorService;
    }


    @GetMapping(value = "", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ExchangeAuditorDto> filterExchangeRates() {

        return exchangeRateAuditorService.listAuditor();
    }

}
