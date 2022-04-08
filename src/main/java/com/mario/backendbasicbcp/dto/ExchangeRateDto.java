package com.mario.backendbasicbcp.dto;

import com.mario.backendbasicbcp.model.ExchangeRate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExchangeRateDto {

    private Long id;

    private CoinDto originalCurrency;

    private CoinDto targetCurrency;

    private Double conversionFactor;

    private String exchangeDate;

    public ExchangeRateDto(Long id) {
        this.id = id;
    }

    public static ExchangeRateDto buildDto(ExchangeRate exchangeRate) {

        ExchangeRateDto exchangeRateDto = new ExchangeRateDto();
        exchangeRateDto.setId(exchangeRate.getId());
        exchangeRateDto.setOriginalCurrency(new CoinDto(exchangeRate.getOriginalCurrency().getId()));
        exchangeRateDto.setTargetCurrency(new CoinDto(exchangeRate.getTargetCurrency().getId()));
        exchangeRateDto.setConversionFactor(exchangeRate.getConversionFactor());
        exchangeRateDto.setExchangeDate(exchangeRate.getExchangeDate().format(DateTimeFormatter.ISO_DATE));

        return exchangeRateDto;

    }
}
