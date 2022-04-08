package com.mario.backendbasicbcp.service;

import com.mario.backendbasicbcp.dto.CoinDto;
import com.mario.backendbasicbcp.repository.CoinRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CoinServiceImpl implements CoinService {

    private final CoinRepository coinRepository;

    public CoinServiceImpl(CoinRepository coinRepository) {
        this.coinRepository = coinRepository;
    }

    @Override
    public List<CoinDto> listAllCoins() {

         return this.coinRepository.findAll().stream().map(c -> {
            CoinDto coinDto = new CoinDto();
            coinDto.setId(c.getId());
            coinDto.setValue(c.getValue());

            return coinDto;
        }).collect(Collectors.toList());
    }
}
