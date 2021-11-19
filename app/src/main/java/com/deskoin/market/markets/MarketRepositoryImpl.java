package com.deskoin.market.markets;

import com.deskoin.market.markets.model.Market;
import com.deskoin.market.markets.model.MarketValue;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.*;

@Repository
public class MarketRepositoryImpl implements MarketRepository {

    private static Map<String, Market> marketStore = initMarketStore();

    private static Map<String, Market> initMarketStore() {
        List<MarketValue> krakenMarketValues = List.of(
                new MarketValue(new BigDecimal("1000.0"), new BigDecimal("10.0")),
                new MarketValue(new BigDecimal("845.0"), new BigDecimal("1.0")),
                new MarketValue(new BigDecimal("951.0"), new BigDecimal("2.0")),
                new MarketValue(new BigDecimal("504.0"), new BigDecimal("10.0")),
                new MarketValue(new BigDecimal("673.0"), new BigDecimal("5.0"))
        );
        List<MarketValue> binanceMarketValues = List.of(
                new MarketValue(new BigDecimal("435.0"), new BigDecimal("4.0")),
                new MarketValue(new BigDecimal("749.0"), new BigDecimal("3.0")),
                new MarketValue(new BigDecimal("195.0"), new BigDecimal("6.0")),
                new MarketValue(new BigDecimal("693.0"), new BigDecimal("10.0")),
                new MarketValue(new BigDecimal("835.0"), new BigDecimal("10.0"))
        );
        return Map.ofEntries(
                new AbstractMap.SimpleEntry<>("kraken", new Market("kraken", krakenMarketValues)),
                new AbstractMap.SimpleEntry<>("binance", new Market("binance", binanceMarketValues))
        );
    }

    @Override
    public Optional<Market> findByName(String marketName) {
        return Optional.ofNullable(marketStore.get(marketName));
    }

    @Override
    public List<Market> findAll() {
        return new ArrayList<>(marketStore.values());
    }

}
