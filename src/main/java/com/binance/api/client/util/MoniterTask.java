package com.binance.api.client.util;

import okhttp3.WebSocket;

import java.util.Map;
import java.util.Set;
import java.util.TimerTask;

/**
 * @author xiaotian.huang
 * @date 2019-05-17
 */
public class MoniterTask extends TimerTask {
    private Map<String, WebSocket> webSocketMap = WebSocketMoniterContainer.inst().getWebSocketMap();
    // mark
    private int markCount;
    //
    private final int ALLOWABLE_DIFFERENCE = 2;

    @Override
    public void run() {
        Set<Map.Entry<String, WebSocket>> entrySet = webSocketMap.entrySet();
        for (Map.Entry<String, WebSocket> entry : entrySet) {
            String channel = entry.getKey();
            System.out.println(channel);
            WebSocket webSocket = entry.getValue();
            Integer pongCount = getPongCount(webSocket);
            System.out.println(pongCount);
        }
    }

    private Integer getPongCount(WebSocket webSocket) {
        return ReflectUtil.getFiledValue(webSocket, "receivedPongCount", Integer.class);
    }


    private void initMark() {
        markCount = 0;
    }

    private void updateMark() {
        this.markCount++;
    }
}
