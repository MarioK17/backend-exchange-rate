package com.mario.backendbasicbcp.service;


import com.mario.backendbasicbcp.dto.ExchangeAuditorDto;
import reactor.core.publisher.Flux;

public interface ExchangeRateAuditorService {

    Flux<ExchangeAuditorDto> listAuditor();
}
