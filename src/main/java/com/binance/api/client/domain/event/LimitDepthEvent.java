package com.binance.api.client.domain.event;

import com.binance.api.client.constant.BinanceApiConstants;
import com.binance.api.client.domain.market.OrderBookEntry;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

/**
 * Limit Depth event for a symbol
 *
 * @author xiaotian.huang
 * @date 2019-05-18
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class LimitDepthEvent {

    private Long lastUpdatedId;

    private List<OrderBookEntry> asks;

    private List<OrderBookEntry> bids;

    public LimitDepthEvent(@JsonProperty("lastUpdateId") Long lastUpdatedId,
                           @JsonProperty("asks") List<OrderBookEntry> asks,
                           @JsonProperty("bids") List<OrderBookEntry> bids) {
        this.lastUpdatedId = lastUpdatedId;
        this.asks = asks;
        this.bids = bids;
    }

    public Long getLastUpdatedId() {
        return lastUpdatedId;
    }

    public List<OrderBookEntry> getAsks() {
        return asks;
    }

    public List<OrderBookEntry> getBids() {
        return bids;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, BinanceApiConstants.TO_STRING_BUILDER_STYLE)
                .append("lastUpdatedId", lastUpdatedId)
                .append("asks", asks)
                .append("bids", bids)
                .toString();
    }
}
