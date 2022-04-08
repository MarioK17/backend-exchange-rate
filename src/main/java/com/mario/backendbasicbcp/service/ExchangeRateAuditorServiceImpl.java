package com.mario.backendbasicbcp.service;

import com.mario.backendbasicbcp.dto.ExchangeAuditorDto;
import com.mario.backendbasicbcp.repository.ExchangeRateAuditorRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ExchangeRateAuditorServiceImpl implements ExchangeRateAuditorService {

    private final ExchangeRateAuditorRepository exchangeRateAuditorRepository;

    public ExchangeRateAuditorServiceImpl(ExchangeRateAuditorRepository exchangeRateAuditorRepository) {
        this.exchangeRateAuditorRepository = exchangeRateAuditorRepository;
    }

    @Override
    public Flux<ExchangeAuditorDto> listAuditor() {

        return Flux.fromIterable(this.exchangeRateAuditorRepository.findAll())
                .doOnNext(tipoCambio -> {
                })
                .doOnComplete(() -> {

                })
                .map(ExchangeAuditorDto::buildDto);

    }

}
