package com.deskoin.market.markets.mapper;

import com.deskoin.market.markets.dto.MarketValueResponse;
import com.deskoin.market.markets.model.MarketValue;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MarketValueResponseMapper {

    public List<MarketValueResponse> mapAll(List<MarketValue> values) {
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
