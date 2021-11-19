package com.deskoin.market.markets.dto;

import com.deskoin.market.markets.model.Market;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MarketResponseMapper {

    private final MarketValueResponseMapper marketValueResponseMapper;

    public MarketResponseMapper(MarketValueResponseMapper marketValueResponseMapper) {
        this.marketValueResponseMapper = marketValueResponseMapper;
    }

    public List<MarketResponse> map(List<Market> markets) {
        return markets == null ? null : markets.stream().map(this::map).collect(Collectors.toList());
    }

    public MarketResponse map(Market market) {
        if (market == null) {
            return null;
        } else {
            var response = new MarketResponse();
            response.setName(market.getName());
            response.setValues(marketValueResponseMapper.map(market.getValues()));
            return response;
        }
    }

}
