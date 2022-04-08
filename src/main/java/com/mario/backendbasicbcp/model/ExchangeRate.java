package com.mario.backendbasicbcp.model;


import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "EXCHANGE_RATE")
public class ExchangeRate {

    @Id
    @SequenceGenerator(name = "EXCHANGE_RATE_SEQUENCE", sequenceName = "S_EXCHANGE_RATE", allocationSize = 1)
    @GeneratedValue(generator = "EXCHANGE_RATE_SEQUENCE", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "original_currency_id")
    private Coin originalCurrency;


    @ManyToOne
    @JoinColumn(name = "target_currency_id")
    private Coin targetCurrency;

    @Column(name = "CONVERSION_FACTOR", precision = 10, scale = 3)
    private Double conversionFactor;

    @Column(name = "EXCHANGE_DATE")
    private LocalDate exchangeDate;

    public ExchangeRate() {

    }


    public ExchangeRate(Long id, Coin originalCurrency, Coin targetCurrency, Double conversionFactor, LocalDate exchangeDate) {
        this.id = id;
        this.originalCurrency = originalCurrency;
        this.targetCurrency = targetCurrency;
        this.conversionFactor = conversionFactor;
        this.exchangeDate = exchangeDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Coin getOriginalCurrency() {
        return originalCurrency;
    }

    public void setOriginalCurrency(Coin originalCurrency) {
        this.originalCurrency = originalCurrency;
    }

    public Coin getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(Coin targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public Double getConversionFactor() {
        return conversionFactor;
    }

    public void setConversionFactor(Double valor) {
        this.conversionFactor = valor;
    }

    public LocalDate getExchangeDate() {
        return exchangeDate;
    }

    public void setExchangeDate(LocalDate fecTipoCambio) {
        this.exchangeDate = fecTipoCambio;
    }
}
