package com.mario.backendbasicbcp.repository;

import com.mario.backendbasicbcp.model.ExchangeRate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ExchangeRateRepository  extends JpaRepository<ExchangeRate, Long> {

    @Query("select e from ExchangeRate e " +
            "left join e.originalCurrency o " +
            "left join e.targetCurrency t " +
            "where (:originalCurrency = 0L or o.id = :originalCurrency) " +
            "and (:targetCurrency = 0L or t.id = :targetCurrency) " +
            "and (:exchangeDate = date('1800-01-01') or :exchangeDate = e.exchangeDate)")
    List<ExchangeRate> findAllExchangeRateBy(Long originalCurrency, Long targetCurrency, LocalDate exchangeDate);

    @Query("select e from ExchangeRate e " +
            "left join e.originalCurrency o " +
            "left join e.targetCurrency t " +
            "where o.id = :originalCurrency " +
            "and t.id = :targetCurrency " +
            "and e.exchangeDate = :exchangeDate")
    Optional<ExchangeRate> findExchangeRateByOriginalCurrencyAndTargetCurrency(Long originalCurrency, Long targetCurrency, LocalDate exchangeDate);

    @Query("select e from ExchangeRate e " +
            "where e.id = :id")
    Optional<ExchangeRate> findExchangeRateById(Long id);

}
