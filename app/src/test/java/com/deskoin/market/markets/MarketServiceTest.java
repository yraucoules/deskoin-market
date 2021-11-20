package com.deskoin.market.markets;

import com.deskoin.market.markets.exception.MarketIllegalArgumentException;
import com.deskoin.market.markets.model.Market;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MarketServiceTest {

    @Mock
    private MarketRepository marketRepository;

    @InjectMocks
    private MarketService marketService;

    @Test
    void getMarket_bad_null_input() {
        Assertions.assertThrows(MarketIllegalArgumentException.class, () -> marketService.getMarket(null));
    }

    @Test
    void getMarket_bad_blank_input() {
        Assertions.assertThrows(MarketIllegalArgumentException.class, () -> marketService.getMarket(""));
    }

    @Test
    void getMarket_empty() {
        String marketName = "market";
        when(marketRepository.findByName(eq(marketName))).thenReturn(Optional.empty());
        Optional<Market> result = marketService.getMarket(marketName);
        Assertions.assertTrue(result.isEmpty());
    }

    @Test
    void getMarket_success() {
        String marketName = "market";
        Market marketData = mock(Market.class);
        when(marketRepository.findByName(eq(marketName))).thenReturn(Optional.of(marketData));
        Optional<Market> result = marketService.getMarket(marketName);
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals(marketData, result.get());
    }

}