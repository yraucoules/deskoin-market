package com.deskoin.market.markets.model;

import java.math.BigDecimal;

public class MarketValue {

    private final BigDecimal price;
    private final BigDecimal volume;

    public MarketValue(BigDecimal price, BigDecimal volume) {
        this.price = price;
        this.volume = volume;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public BigDecimal getVolume() {
        return volume;
    }
}
