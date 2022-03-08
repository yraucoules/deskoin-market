package com.deskoin.market.markets;

import com.deskoin.market.markets.model.Market;
import com.deskoin.market.markets.model.MarketValue;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public class MarketRepositoryImpl implements MarketRepository {

    private static final String KRAKEN_EXCHANGE_NAME = "kraken";
    private static final String BINANCE_EXCHANGE_NAME = "binance";

    @Override
    public Optional<Market> findByName(String marketName) {
        return findAll().stream()
                .filter(market -> market.getName().equalsIgnoreCase(marketName))
                .findFirst();
    }

    @Override
    public List<Market> findAll() {
        return List.of(
                new Market(KRAKEN_EXCHANGE_NAME, krakenValues()),
                new Market(BINANCE_EXCHANGE_NAME, binanceValues())
        );
    }

    private List<MarketValue> krakenValues() {
        return List.of(
                new MarketValue(new BigDecimal(10), new BigDecimal(1)),
                new MarketValue(new BigDecimal(42), new BigDecimal(2)),
                new MarketValue(new BigDecimal(50), new BigDecimal(10)),
                new MarketValue(new BigDecimal(30), new BigDecimal(4)),
                new MarketValue(new BigDecimal(90), new BigDecimal(1))
        );
    }

    private List<MarketValue> binanceValues() {
        return List.of(
                new MarketValue(new BigDecimal(90), new BigDecimal(8)),
                new MarketValue(new BigDecimal(40), new BigDecimal(8)),
                new MarketValue(new BigDecimal(100), new BigDecimal(10)),
                new MarketValue(new BigDecimal(20), new BigDecimal(10)),
                new MarketValue(new BigDecimal(55), new BigDecimal(1))
        );
    }

}
