package com.deskoin.market.markets;

import com.deskoin.market.markets.exception.MarketIllegalArgumentException;
import com.deskoin.market.markets.model.Market;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarketService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MarketService.class);

    private final MarketRepository marketRepository;

    public MarketService(MarketRepository marketRepository) {
        this.marketRepository = marketRepository;
    }

    public Optional<Market> getMarket(String marketName) {
        if (marketName == null || marketName.isBlank()) {
            throw new MarketIllegalArgumentException("Market name must not be null or blank");
        }
        LOGGER.debug("Retrieve market {}", marketName);
        return marketRepository.findByName(marketName);
    }

    public List<Market> getMarkets() {
        LOGGER.debug("Retrieve all markets");
        return marketRepository.findAll();
    }

}
