package com.deskoin.market.markets;

import com.deskoin.market.markets.model.Market;

import java.util.List;
import java.util.Optional;

public interface MarketRepository {

    Optional<Market> findByName(String marketName);

    List<Market> findAll();

}
