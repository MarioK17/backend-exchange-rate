package com.mario.backendbasicbcp.dto;

import com.mario.backendbasicbcp.model.ExchangeRateAuditor;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.format.DateTimeFormatter;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExchangeAuditorDto {

    private String user;
    private String dateRegister;
    private String originalCurrency;
    private String targetCurrency;
    private Double amount;
    private Double conversionFactor;


    public static ExchangeAuditorDto buildDto(ExchangeRateAuditor exchangeRateAuditor) {
        ExchangeAuditorDto exchangeAuditorDto = new ExchangeAuditorDto();

        exchangeAuditorDto.setUser(exchangeRateAuditor.getUser().getUsername());
        exchangeAuditorDto.setDateRegister(exchangeRateAuditor.getDateRegister().format(DateTimeFormatter.ISO_DATE));
        exchangeAuditorDto.setOriginalCurrency(exchangeRateAuditor.getOriginalCurrency());
        exchangeAuditorDto.setTargetCurrency(exchangeRateAuditor.getTargetCurrency());
        exchangeAuditorDto.setAmount(exchangeRateAuditor.getAmount());
        exchangeAuditorDto.setConversionFactor(exchangeRateAuditor.getConversionFactor());


        return exchangeAuditorDto;
    }

}
