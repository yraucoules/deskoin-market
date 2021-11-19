package com.deskoin.market.markets.model;

import java.util.List;

public class Market {

    private final String name;
    private final List<MarketValue> values;

    public Market(String name, List<MarketValue> values) {
        this.name = name;
        this.values = values;
    }

    public String getName() {
        return name;
    }

    public List<MarketValue> getValues() {
        return values;
    }
}
