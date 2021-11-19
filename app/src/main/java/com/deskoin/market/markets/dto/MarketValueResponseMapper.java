package com.deskoin.market.markets.dto;

import com.deskoin.market.markets.model.MarketValue;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MarketValueResponseMapper {

    public List<MarketValueResponse> map(List<MarketValue> values) {
        return values == null ? null : values.stream().map(this::map).collect(Collectors.toList());
    }

    public MarketValueResponse map(MarketValue value) {
        if (value == null) {
            return null;
        } else {
            var response = new MarketValueResponse();
            response.setPrice(value.getPrice());
            response.setVolume(value.getVolume());
            return response;
        }
    }

}
