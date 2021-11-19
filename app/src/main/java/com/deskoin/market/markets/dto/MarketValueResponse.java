package com.deskoin.market.markets.dto;

import java.math.BigDecimal;

public class MarketValueResponse {

    private BigDecimal price;
    private BigDecimal volume;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }
}
