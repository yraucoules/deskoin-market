package com.deskoin.market.markets;

import com.deskoin.market.markets.exception.MarketIllegalArgumentException;
import com.deskoin.market.markets.mapper.MarketResponseMapper;
import com.deskoin.market.markets.model.Market;
import com.deskoin.market.markets.model.MarketValue;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MarketController.class)
@Import(MarketControllerIT.MarketMapperConfiguration.class)
class MarketControllerIT {

    public static final String BASE_PATH = "/markets";

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MarketService marketService;

    @Test
    void getMarket_success() throws Exception {

        String marketName = "kraken";

        Market market = createMarket(marketName);

        when(marketService.getMarket(eq(marketName))).thenReturn(Optional.of(market));

        mockMvc.perform(get(BASE_PATH + "/" + marketName)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", Matchers.is(marketName)))
                .andExpect(jsonPath("$.values[0].price", Matchers.is(10.12)))
                .andExpect(jsonPath("$.values[0].volume", Matchers.is(5.12)))
                .andExpect(jsonPath("$.values[1].price", Matchers.is(11.12)))
                .andExpect(jsonPath("$.values[1].volume", Matchers.is(6.12)))
        ;
    }

    @Test
    void getMarket_wrong_input() throws Exception {

        when(marketService.getMarket(Mockito.any())).thenThrow(new MarketIllegalArgumentException("test"));

        mockMvc.perform(get(BASE_PATH + "/error")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isBadRequest())
        ;
    }

    @Test
    void getMarket_not_found() throws Exception {

        String marketName = "other";

        when(marketService.getMarket(eq(marketName))).thenReturn(Optional.empty());

        mockMvc.perform(get(BASE_PATH + "/" + marketName)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isNotFound());
    }

    @Test
    void getMarkets_success() throws Exception {

        List<Market> markets = List.of(createMarket("kraken"), createMarket("binance"));

        when(marketService.getMarkets()).thenReturn(markets);

        mockMvc.perform(get(BASE_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name", Matchers.is("kraken")))
                .andExpect(jsonPath("$[1].name", Matchers.is("binance")))
                .andExpect(jsonPath("$[0].values[0].price", Matchers.is(10.12)))
                .andExpect(jsonPath("$[0].values[0].volume", Matchers.is(5.12)))
                .andExpect(jsonPath("$[0].values[1].price", Matchers.is(11.12)))
                .andExpect(jsonPath("$[0].values[1].volume", Matchers.is(6.12)))
                .andExpect(jsonPath("$[1].values[0].price", Matchers.is(10.12)))
                .andExpect(jsonPath("$[1].values[0].volume", Matchers.is(5.12)))
                .andExpect(jsonPath("$[1].values[1].price", Matchers.is(11.12)))
                .andExpect(jsonPath("$[1].values[1].volume", Matchers.is(6.12)))
        ;

        mockMvc.perform(get(BASE_PATH)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }

    private Market createMarket(String marketName) {
        return new Market(
                marketName,
                List.of(
                        new MarketValue(BigDecimal.valueOf(10.12), BigDecimal.valueOf(5.12)),
                        new MarketValue(BigDecimal.valueOf(11.12), BigDecimal.valueOf(6.12))
                )
        );
    }

    @TestConfiguration
    @ComponentScan(basePackages = "com.deskoin.market.markets.mapper")
    static class MarketMapperConfiguration {

    }

}