package com.mario.backendbasicbcp.service;

import com.mario.backendbasicbcp.dto.ExchangeRateDto;
import com.mario.backendbasicbcp.model.Coin;
import com.mario.backendbasicbcp.model.ExchangeRate;
import com.mario.backendbasicbcp.model.ExchangeRateAuditor;
import com.mario.backendbasicbcp.model.User;
import com.mario.backendbasicbcp.repository.CoinRepository;
import com.mario.backendbasicbcp.repository.ExchangeRateAuditorRepository;
import com.mario.backendbasicbcp.repository.ExchangeRateRepository;
import com.mario.backendbasicbcp.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExchangeRateServiceImpl implements ExchangeRateService {

    private final ExchangeRateRepository exchangeRateRepository;
    private final ExchangeRateAuditorRepository exchangeRateAuditorRepository;
    private final UserRepository userRepository;
    private final CoinRepository coinRepository;


    public ExchangeRateServiceImpl(ExchangeRateRepository exchangeRateRepository, ExchangeRateAuditorRepository exchangeRateAuditorRepository, UserRepository userRepository, CoinRepository coinRepository) {
        this.exchangeRateRepository = exchangeRateRepository;
        this.exchangeRateAuditorRepository = exchangeRateAuditorRepository;
        this.userRepository = userRepository;
        this.coinRepository = coinRepository;
    }

    @Override
    public Flux<ExchangeRateDto> filterExchangeRates(Long originalCurrency, Long targetCurrency, String exchangeDate) {

        Flux<ExchangeRateDto> tipoCambioDtoFlux = Flux.fromIterable(exchangeRateRepository
                        .findAllExchangeRateBy(originalCurrency,
                                targetCurrency,
                                "".equals(exchangeDate)? LocalDate.of(1800, Month.JANUARY, 1) : LocalDate.parse(exchangeDate, DateTimeFormatter.ISO_DATE)))
                .doOnNext(tipoCambio -> {

                })
                .doOnComplete(() -> {

                })
                .map(ExchangeRateDto::buildDto);


        return tipoCambioDtoFlux;
    }

    @Override
    public List<ExchangeRateDto> filterExchangeRatesNormal(String originalCurrency, String targetCurrency, String exchangeDate) {

       return exchangeRateRepository.findAllExchangeRateBy(!"".equals(originalCurrency)? Long.parseLong(originalCurrency) : 0L, !"".equals(targetCurrency)? Long.parseLong(targetCurrency) : 0L,  "".equals(exchangeDate)? LocalDate.of(1800, Month.JANUARY, 1) : LocalDate.now())
                .stream()
                .map(ExchangeRateDto::buildDto)
               .collect(Collectors.toList());

    }

    @Override
    public Mono<ExchangeRateDto> saveExchangeRate(ExchangeRateDto exchangeRateDto) {
        Coin originalCoin;
        if(exchangeRateDto.getOriginalCurrency().getId() != 0) {
            originalCoin = coinRepository.findCoinByIdCoin(exchangeRateDto.getOriginalCurrency().getId()).get();
        } else {
            originalCoin = new Coin();
            originalCoin.setValue(exchangeRateDto.getOriginalCurrency().getValue());
            originalCoin = coinRepository.save(originalCoin);
        }

        Coin targetCoin;
        if(exchangeRateDto.getTargetCurrency().getId() != 0) {
            targetCoin = coinRepository.findCoinByIdCoin(exchangeRateDto.getTargetCurrency().getId()).get();
        } else {
            targetCoin = new Coin();
            targetCoin.setValue(exchangeRateDto.getTargetCurrency().getValue());
            targetCoin = coinRepository.save(targetCoin);
        }


        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setOriginalCurrency(originalCoin);
        exchangeRate.setTargetCurrency(targetCoin);
        exchangeRate.setConversionFactor(exchangeRateDto.getConversionFactor());
        exchangeRate.setExchangeDate(LocalDate.parse(exchangeRateDto.getExchangeDate(), DateTimeFormatter.ISO_DATE));

        return Mono.just(ExchangeRateDto.buildDto(exchangeRateRepository.save(exchangeRate)));
    }

    @Override
    public Mono<ExchangeRateDto> updateExchangeRate(ExchangeRateDto exchangeRateDto) {

        ExchangeRate exchangeRate = exchangeRateRepository.findExchangeRateById(exchangeRateDto.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        exchangeRate.setOriginalCurrency(new Coin(exchangeRateDto.getOriginalCurrency().getId()));
        exchangeRate.setTargetCurrency(new Coin(exchangeRateDto.getTargetCurrency().getId()));
        exchangeRate.setConversionFactor(exchangeRateDto.getConversionFactor());
        exchangeRate.setExchangeDate(LocalDate.parse(exchangeRateDto.getExchangeDate(), DateTimeFormatter.ISO_DATE));

        return Mono.just(ExchangeRateDto.buildDto(exchangeRateRepository.save(exchangeRate)));
    }

    @Override
    public Mono<Double> calculateExchangeRate(Long originalCurrency, Long targetCurrency, Double amount) {

        ExchangeRate exchangeRate = this.exchangeRateRepository.findExchangeRateByOriginalCurrencyAndTargetCurrency(originalCurrency, targetCurrency, LocalDate.now())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return ReactiveSecurityContextHolder.getContext()
                .map(SecurityContext::getAuthentication)
                .map(s -> {
                    ExchangeRateAuditor exchangeRateAuditor = new ExchangeRateAuditor();
                    exchangeRateAuditor.setDateRegister(LocalDateTime.now());
                    exchangeRateAuditor.setOriginalCurrency(String.valueOf(originalCurrency));
                    exchangeRateAuditor.setTargetCurrency(String.valueOf(targetCurrency));
                    exchangeRateAuditor.setAmount(amount);
                    exchangeRateAuditor.setConversionFactor(exchangeRate.getConversionFactor());

                    User user = userRepository.findByUsername(s.getName()).get();

                    exchangeRateAuditor.setUser(user);
                    exchangeRateAuditorRepository.save(exchangeRateAuditor);

                    return amount * exchangeRate.getConversionFactor();
                });
    }
}
