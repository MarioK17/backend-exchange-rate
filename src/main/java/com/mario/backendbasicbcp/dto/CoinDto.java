package com.mario.backendbasicbcp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CoinDto {

    private Long id;
    private String value;

    public CoinDto(Long id) {
        this.id = id;
    }
}
