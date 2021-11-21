package com.deskoin.market.markets.dto;

import java.util.List;

public class MarketResponse {

    private String name;
    private List<MarketValueResponse> values;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MarketValueResponse> getValues() {
        return values;
    }

    public void setValues(List<MarketValueResponse> values) {
        this.values = values;
    }
}
