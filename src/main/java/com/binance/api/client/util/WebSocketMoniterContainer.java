package com.binance.api.client.util;

import okhttp3.WebSocket;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * websocket 容器
 *
 * @author xiaotian.huang
 * @date 2019-05-17
 */
public class WebSocketMoniterContainer {

    private Map<String,WebSocket> webSocketMap = new HashMap<>();

    private static WebSocketMoniterContainer container;

    private WebSocketMoniterContainer() {
    }

    public static WebSocketMoniterContainer inst() {
        if (Objects.isNull(container)) {
            container = new WebSocketMoniterContainer();
        }
        return container;
    }

    public void add(String channel, WebSocket webSocket) {
        webSocketMap.put(channel, webSocket);
    }

    public Map<String,WebSocket> getWebSocketMap() {
        return webSocketMap;
    }
}
