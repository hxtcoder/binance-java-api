package com.binance.api.examples;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.BinanceApiWebSocketClient;
import com.binance.api.client.domain.TimeInForce;
import com.binance.api.client.domain.account.*;
import com.binance.api.client.domain.account.request.AllOrdersRequest;
import com.binance.api.client.domain.account.request.OrderStatusRequest;
import com.binance.api.client.domain.general.ExchangeInfo;
import junit.framework.TestCase;
import okhttp3.internal.ws.RealWebSocket;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.concurrent.Executors;

import static com.binance.api.client.domain.account.NewOrder.limitBuy;

public class MyTest {


    @Test
    public void testCeres() {
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance("7RLFLpmGaWJBZop0pij1YuoAxinKDHJqcfH1aA1lYPjdz9GfT0BwdOrU7NbBe5aO",
                "Z2YFSoPVv7GRuooTYWF7SJAZfzKpR7Ulf5h6BLCiNH2kjLG2Hikq1n6wmPm2l8X9");
        BinanceApiRestClient client = factory.newRestClient();

/*        ExchangeInfo exchangeInfo = client.getExchangeInfo();
        System.out.println("exchangeInfo = " + exchangeInfo);*/

/*        Order order = client.getOrderStatus(new OrderStatusRequest("ADAUSDT", 40358659L));
        System.out.println("order = " + order);*/
/*        String activeKey = client.startUserDataStream();
        System.out.println("activeKey = " + activeKey);
        client.closeUserDataStream(activeKey);
        String s = client.startUserDataStream();
        System.out.println("s = " + s);*/
//        Account account = client.getAccount(6000000L, System.currentTimeMillis());
//        System.out.println(account.getBalances());

        // Placing a real LIMIT order
//        try {
//            NewOrderResponse newOrderResponse = client.newOrder(limitBuy("ADAUSDT", TimeInForce.GTC, "150", "0.09")
//                    .newClientOrderId("abc123")
//                    .newOrderRespType(NewOrderResponseType.FULL));
//            System.out.println(newOrderResponse);
//        } catch (Exception e){
//            e.printStackTrace();
//        }

//        List<Order> allOrders = client.getAllOrders(new AllOrdersRequest("ADAUSDT").limit(10));
//        System.out.println(allOrders);

        // 测试用户数据流
        String listenkey = client.startUserDataStream();

        BinanceApiWebSocketClient webSocketClient = factory.newWebSocketClient();
        webSocketClient.onUserDataUpdateEvent(listenkey, response -> {
            System.out.println("response = " + response);
        });
    }

    BinanceApiClientFactory factory;
    BinanceApiRestClient client;

    @Before
    public void init() {
        factory = BinanceApiClientFactory.newInstance("ZgZsgFpxRiuoZFGAcCY9S47oTaYtgpD9NWP15wrw3xeHVfN9IP4XneixOtDfkHhS",
                "avMfTZ9MnJ8tbtkqTyzx3gwKprCNVqBked11P3E8Iw4QZU9x3IuiHmFTfHiqm9t3");
        client = factory.newRestClient();
    }

    @Test
    public void testCloseListenkey() {
        client.closeUserDataStream("xp3BWZYM6jqKaDLFGUCeBlGJbT3rmYP1bFsvZczdThjLA7zV2W79C9vfrl88");
    }

    @Test
    public void testUserDataStream() {
        String listenkey = client.startUserDataStream();
        BinanceApiWebSocketClient webSocketClient = factory.newWebSocketClient();
        webSocketClient.onUserDataUpdateEvent(listenkey, System.out::println);
    }

    //qc4a0IbY4NDNkNsSA1ibjO3ogwGnlR7I9mXenjnoINepV3CqMgdElwLdBdHl
    @Test
    public void testGetListenkey() {
        String listenKey = client.startUserDataStream();
        System.out.println("listenKey = " + listenKey);
    }

//    public static void main(String[] args) {
//        Executors.newScheduledThreadPool(1).scheduleAtFixedRate()
//    }


    @Test
    public void testReflex() {
        try {
            Class<RealWebSocket> aClass = RealWebSocket.class;
            Method pongCount = aClass.getDeclaredMethod("receivedPongCount",null);
            RealWebSocket realWebSocket = (RealWebSocket)aClass.newInstance();
            pongCount.setAccessible(true);
            while (true) {
                Thread.sleep(5000);
                int invoke = (int) pongCount.invoke(realWebSocket);
                System.out.println("invoke = " + invoke);
            }
        } catch (NoSuchMethodException  | InstantiationException | IllegalAccessException | InvocationTargetException | InterruptedException e) {
            e.printStackTrace();
            return;
        }
    }
}
