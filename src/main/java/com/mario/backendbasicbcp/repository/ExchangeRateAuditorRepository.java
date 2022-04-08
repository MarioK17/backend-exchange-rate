package com.mario.backendbasicbcp.repository;

import com.mario.backendbasicbcp.model.ExchangeRateAuditor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExchangeRateAuditorRepository extends JpaRepository<ExchangeRateAuditor, Long> {

}
