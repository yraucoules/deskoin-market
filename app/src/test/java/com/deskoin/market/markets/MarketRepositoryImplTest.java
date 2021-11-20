package com.deskoin.market.markets;

import com.deskoin.market.markets.model.Market;
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

    @Mock
    private Random random;

    @InjectMocks
    private MarketRepositoryImpl marketRepositoryImpl;

    @Test
    void findAll() {
        Mockito.when(random.nextInt(Mockito.eq(10000))).thenReturn(100);
        List<Market> markets = marketRepositoryImpl.findAll();
        Assertions.assertEquals(2, markets.size());
        Assertions.assertNotNull(markets.get(0).getName());
        Assertions.assertNotNull(markets.get(1).getName());
        Assertions.assertEquals(5, markets.get(0).getValues().size());
        Assertions.assertEquals(BigDecimal.valueOf(1.01), markets.get(0).getValues().get(0).getPrice());
        Assertions.assertEquals(BigDecimal.valueOf(1.01), markets.get(0).getValues().get(0).getVolume());
        Assertions.assertEquals(5, markets.get(1).getValues().size());
        Assertions.assertEquals(BigDecimal.valueOf(1.01), markets.get(1).getValues().get(0).getPrice());
        Assertions.assertEquals(BigDecimal.valueOf(1.01), markets.get(1).getValues().get(0).getVolume());
    }

    @ParameterizedTest
    @CsvSource({
            "kraken",
            "binance"
    })
    void findAll(String marketName) {
        Mockito.when(random.nextInt(Mockito.eq(10000))).thenReturn(100);
        Optional<Market> market = marketRepositoryImpl.findByName(marketName);
        Assertions.assertTrue(market.isPresent());
        Assertions.assertEquals(marketName, market.get().getName());
        Assertions.assertEquals(5, market.get().getValues().size());
    }

    @Test
    void findAll_empty() {
        Mockito.when(random.nextInt(Mockito.eq(10000))).thenReturn(100);
        Optional<Market> market = marketRepositoryImpl.findByName("other");
        Assertions.assertTrue(market.isEmpty());
    }

}