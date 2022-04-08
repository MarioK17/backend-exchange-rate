package com.mario.backendbasicbcp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "EXCHANGE_RATE_AUDITOR")
public class ExchangeRateAuditor {

    @Id
    @SequenceGenerator(name = "EXCHANGE_RATE_SEQUENCE", sequenceName = "S_EXCHANGE_RATE", allocationSize = 1)
    @GeneratedValue(generator = "EXCHANGE_RATE_SEQUENCE", strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "DATE_REGISTER")
    private LocalDateTime dateRegister;

    @Column(name = "ORIGINAL_CURRENCY", length = 10)
    private String originalCurrency;

    @Column(name = "TARGET_CURRENCY", length = 10)
    private String targetCurrency;

    @Column(name = "AMOUNT", precision = 17, scale = 3)
    private Double amount;

    @Column(name = "CONVERSION_FACTOR", precision = 10, scale = 3)
    private Double conversionFactor;

    public ExchangeRateAuditor() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDateTime getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(LocalDateTime dateRegister) {
        this.dateRegister = dateRegister;
    }

    public String getOriginalCurrency() {
        return originalCurrency;
    }

    public void setOriginalCurrency(String originalCurrency) {
        this.originalCurrency = originalCurrency;
    }

    public String getTargetCurrency() {
        return targetCurrency;
    }

    public void setTargetCurrency(String targetCurrency) {
        this.targetCurrency = targetCurrency;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getConversionFactor() {
        return conversionFactor;
    }

    public void setConversionFactor(Double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }
}
