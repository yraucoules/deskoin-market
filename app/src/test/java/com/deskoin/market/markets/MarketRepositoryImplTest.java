package com.deskoin.market.markets;

import com.deskoin.market.markets.model.Market;
import com.deskoin.market.markets.model.MarketValue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@ExtendWith(MockitoExtension.class)
class MarketRepositoryImplTest {

    @InjectMocks
    private MarketRepositoryImpl marketRepositoryImpl;

    @Test
    void findAll() {
        List<Market> markets = marketRepositoryImpl.findAll();

        Assertions.assertEquals(2, markets.size());

        var krakenMarket = markets.get(0);
        Assertions.assertNotNull(krakenMarket);
        Assertions.assertEquals("kraken", krakenMarket.getName());
        var krakenMarketValues = krakenMarket.getValues();
        Assertions.assertEquals(5, krakenMarketValues.size());
        Assertions.assertEquals(new BigDecimal(10), krakenMarketValues.get(0).getPrice());
        Assertions.assertEquals(new BigDecimal(1), krakenMarketValues.get(0).getVolume());
        Assertions.assertEquals(new BigDecimal(42), krakenMarketValues.get(1).getPrice());
        Assertions.assertEquals(new BigDecimal(2), krakenMarketValues.get(1).getVolume());
        Assertions.assertEquals(new BigDecimal(50), krakenMarketValues.get(2).getPrice());
        Assertions.assertEquals(new BigDecimal(10), krakenMarketValues.get(2).getVolume());
        Assertions.assertEquals(new BigDecimal(30), krakenMarketValues.get(3).getPrice());
        Assertions.assertEquals(new BigDecimal(4), krakenMarketValues.get(3).getVolume());
        Assertions.assertEquals(new BigDecimal(90), krakenMarketValues.get(4).getPrice());
        Assertions.assertEquals(new BigDecimal(1), krakenMarketValues.get(4).getVolume());

        var binanceMarket = markets.get(1);
        Assertions.assertNotNull(binanceMarket);
        Assertions.assertEquals("binance", binanceMarket.getName());
        var binanceMarketValues = binanceMarket.getValues();
        Assertions.assertEquals(5, binanceMarketValues.size());
        Assertions.assertEquals(new BigDecimal(90), binanceMarketValues.get(0).getPrice());
        Assertions.assertEquals(new BigDecimal(8), binanceMarketValues.get(0).getVolume());
        Assertions.assertEquals(new BigDecimal(40), binanceMarketValues.get(1).getPrice());
        Assertions.assertEquals(new BigDecimal(8), binanceMarketValues.get(1).getVolume());
        Assertions.assertEquals(new BigDecimal(100), binanceMarketValues.get(2).getPrice());
        Assertions.assertEquals(new BigDecimal(10), binanceMarketValues.get(2).getVolume());
        Assertions.assertEquals(new BigDecimal(20), binanceMarketValues.get(3).getPrice());
        Assertions.assertEquals(new BigDecimal(10), binanceMarketValues.get(3).getVolume());
        Assertions.assertEquals(new BigDecimal(55), binanceMarketValues.get(4).getPrice());
        Assertions.assertEquals(new BigDecimal(1), binanceMarketValues.get(4).getVolume());
    }

    @ParameterizedTest
    @CsvSource({
            "kraken",
            "binance"
    })
    void findAll(String marketName) {
        Optional<Market> market = marketRepositoryImpl.findByName(marketName);

        Assertions.assertTrue(market.isPresent());
        Assertions.assertEquals(marketName, market.get().getName());
        Assertions.assertEquals(5, market.get().getValues().size());
    }

    @Test
    void findAll_empty() {
        Optional<Market> market = marketRepositoryImpl.findByName("other");

        Assertions.assertTrue(market.isEmpty());
    }

}