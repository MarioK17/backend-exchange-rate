package com.mario.backendbasicbcp.controller;

import com.mario.backendbasicbcp.dto.CoinDto;
import com.mario.backendbasicbcp.service.CoinService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/coin")
public class CoinController {

    private final CoinService coinService;

    public CoinController(CoinService coinService) {
        this.coinService = coinService;
    }

    @GetMapping
    public Flux<CoinDto> listAllCoins() {
        return Flux.fromIterable(coinService.listAllCoins());
    }

}
