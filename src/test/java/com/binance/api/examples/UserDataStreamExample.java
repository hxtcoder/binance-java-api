package com.binance.api.examples;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.BinanceApiWebSocketClient;
import com.binance.api.client.domain.event.AccountUpdateEvent;
import com.binance.api.client.domain.event.OrderTradeUpdateEvent;
import com.binance.api.client.domain.event.UserDataUpdateEvent.UserDataUpdateEventType;

/**
 * User data stream endpoints examples.
 *
 * It illustrates how to create a stream to obtain updates on a user account,
 * as well as update on trades/orders on a user account.
 */
public class UserDataStreamExample {

  public static void main(String[] args) {
    BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance("7RLFLpmGaWJBZop0pij1YuoAxinKDHJqcfH1aA1lYPjdz9GfT0BwdOrU7NbBe5aO",
            "Z2YFSoPVv7GRuooTYWF7SJAZfzKpR7Ulf5h6BLCiNH2kjLG2Hikq1n6wmPm2l8X9");
    BinanceApiRestClient client = factory.newRestClient();

    // First, we obtain a listenKey which is required to interact with the user data stream
    String listenKey = client.startUserDataStream();

    // Then, we open a new web socket client, and provide a callback that is called on every update
    BinanceApiWebSocketClient webSocketClient = factory.newWebSocketClient();
    // test start
/*    System.out.println("listenKey = " + listenKey);
    client.closeUserDataStream(listenKey);
    String listenkey2 = client.startUserDataStream();
    System.out.println("listenkey2 = " + listenkey2);
    client.closeUserDataStream(listenkey2);
    String listenkey3 = client.startUserDataStream();
    System.out.println("listenkey3 = " + listenkey3);*/


    // test end

    // Listen for changes in the account
    try {
      webSocketClient.onUserDataUpdateEvent(listenKey, System.out::println);
    } catch (Exception e) {
      System.out.println("e = " + e);
    }
    /*webSocketClient.onUserDataUpdateEvent(listenKey, response -> {
      //
      System.out.println("response = " + response);
      //
      if (response.getEventType() == UserDataUpdateEventType.ACCOUNT_UPDATE) {
        AccountUpdateEvent accountUpdateEvent = response.getAccountUpdateEvent();
        // Print new balances of every available asset
        System.out.println(accountUpdateEvent.getBalances());
      } else {
        OrderTradeUpdateEvent orderTradeUpdateEvent = response.getOrderTradeUpdateEvent();
        // Print details about an order/trade
        System.out.println(orderTradeUpdateEvent);

        // Print original quantity
        System.out.println(orderTradeUpdateEvent.getOriginalQuantity());

        // Or price
        System.out.println(orderTradeUpdateEvent.getPrice());
      }
    });
    System.out.println("Waiting for events...");*/

    // We can keep alive the user data stream
    // client.keepAliveUserDataStream(listenKey);

    // Or we can invalidate it, whenever it is no longer needed
    // client.closeUserDataStream(listenKey);
  }
}
