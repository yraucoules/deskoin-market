package com.deskoin.market.markets.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Random;

@Configuration
public class MarketConfiguration {

    @Bean
    public Random random() {
        return new Random();
    }
}
