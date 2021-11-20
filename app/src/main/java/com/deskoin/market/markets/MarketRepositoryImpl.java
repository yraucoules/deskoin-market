package com.deskoin.market.markets;

import com.deskoin.market.markets.model.Market;
import com.deskoin.market.markets.model.MarketValue;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

@Repository
public class MarketRepositoryImpl implements MarketRepository {

    private static final int MARKET_SIZE = 5;
    private static final int MAX_BOUND = 10000;
    private static final int SCALE = 2;
    private static final String KRAKEN_EXCHANGE_NAME = "kraken";
    private static final String BINANCE_EXCHANGE_NAME = "binance";

    private final Random random;

    public MarketRepositoryImpl(Random random) {
        this.random = random;
    }

    @Override
    public Optional<Market> findByName(String marketName) {
        return Optional.ofNullable(getMarketStore().get(marketName));
    }

    @Override
    public List<Market> findAll() {
        return new ArrayList<>(getMarketStore().values());
    }

    private Map<String, Market> getMarketStore() {
        return Map.ofEntries(
                new AbstractMap.SimpleEntry<>(KRAKEN_EXCHANGE_NAME, new Market(KRAKEN_EXCHANGE_NAME, generateValues())),
                new AbstractMap.SimpleEntry<>(BINANCE_EXCHANGE_NAME, new Market(BINANCE_EXCHANGE_NAME, generateValues()))
        );
    }

    private List<MarketValue> generateValues() {
        ArrayList<MarketValue> marketValues = new ArrayList<>();
        for (int i = 0; i < MARKET_SIZE; i++) {
            marketValues.add(new MarketValue(generateValue(), generateValue()));
        }
        return marketValues;
    }

    private BigDecimal generateValue() {
        return new BigDecimal(BigInteger.valueOf(random.nextInt(MAX_BOUND) + 1), SCALE);
    }

}
