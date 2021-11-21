package com.deskoin.market.markets.exception;

public class MarketIllegalArgumentException extends RuntimeException {

    public MarketIllegalArgumentException(String message) {
        super(message);
    }

    public MarketIllegalArgumentException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
