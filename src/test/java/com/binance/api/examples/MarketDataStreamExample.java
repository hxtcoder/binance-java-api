package com.binance.api.examples;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiWebSocketClient;
import com.binance.api.client.domain.market.CandlestickInterval;
import com.binance.api.client.util.MoniterTask;

import java.io.IOException;
import java.sql.SQLOutput;
import java.sql.Time;
import java.util.Collections;
import java.util.Timer;

/**
 * Market data stream endpoints examples.
 *
 * It illustrates how to create a stream to obtain updates on market data such as executed trades.
 */
public class MarketDataStreamExample {

  public static void main(String[] args) throws InterruptedException, IOException {
    BinanceApiWebSocketClient client = BinanceApiClientFactory.newInstance().newWebSocketClient();

    // Listen for aggregated trade events for ETH/BTC
//    client.onAggTradeEvent("ethbtc,ltcbtc", response -> {});


    // Listen for changes in the order book in ETH/BTC
    client.onDepthEvent("ethbtc,ltcbtc", response -> {});

    client.onLimitDepthEvent("ethbtc,ltcbtc", 10, response -> System.out.println(response));

    // Obtain 1m c
    // esticks in real-time for ETH/BTC
//    client.onCandlestickEvent("ethbtc,ltcbtc", CandlestickInterval.ONE_MINUTE, response -> {});

    new Timer().schedule(new MoniterTask(), 1000 *10, 5000);
  }
}
