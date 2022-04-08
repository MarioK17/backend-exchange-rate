package com.mario.backendbasicbcp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "COIN")
public class Coin {

    @Id
    @SequenceGenerator(name = "COIN_SEQUENCE", sequenceName = "S_COIN", allocationSize = 1)
    @GeneratedValue(generator = "COIN_SEQUENCE", strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "VALUE", length = 20)
    private String value;


    public Coin(Long id) {
        this.id = id;
    }

    public Coin(String value) {
        this.value = value;
    }

    public Coin() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
