package com.mario.backendbasicbcp.repository;

import com.mario.backendbasicbcp.model.Coin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoinRepository extends JpaRepository<Coin, Long> {


    @Query("select c from Coin c " +
            "where c.id = :id")
    Optional<Coin> findCoinByIdCoin(Long id);



}
