package com.deskoin.market.markets;

import com.deskoin.market.markets.dto.MarketResponse;
import com.deskoin.market.markets.exception.MarketIllegalArgumentException;
import com.deskoin.market.markets.mapper.MarketResponseMapper;
import com.deskoin.market.markets.model.Market;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("markets")
public class MarketController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MarketController.class);

    private final MarketService marketService;

    private final MarketResponseMapper marketResponseMapper;

    public MarketController(MarketService marketService,
                            MarketResponseMapper marketResponseMapper) {
        this.marketService = marketService;
        this.marketResponseMapper = marketResponseMapper;
    }

    @Operation(summary = "Retrieve a market")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Market retrieved"),
            @ApiResponse(responseCode = "400", description = "Market name is invalid"),
            @ApiResponse(responseCode = "404", description = "Market not found"),
    })
    @GetMapping("{marketName}")
    public ResponseEntity<MarketResponse> getMarket(@PathVariable String marketName) {
        LOGGER.debug("Retrieve market {}", marketName);
        try {
            var response = marketService.getMarket(marketName)
                    .map(marketResponseMapper::map);
            return ResponseEntity.of(response);
        } catch (MarketIllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @Operation(summary = "Retrieve markets")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Markets retrieved"),
    })
    @GetMapping
    public List<MarketResponse> getMarkets() {
        LOGGER.debug("Retrieve markets");
        List<Market> markets = marketService.getMarkets();
        return this.marketResponseMapper.mapAll(markets);
    }

}
