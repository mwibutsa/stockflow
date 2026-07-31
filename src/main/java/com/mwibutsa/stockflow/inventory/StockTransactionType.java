package com.mwibutsa.stockflow.inventory;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum StockTransactionType {
    @JsonProperty("STOCK_IN")
    STOCK_IN,
    @JsonProperty("STOCK_OUT")
    STOCK_OUT,
    @JsonProperty("ADJUSTMENT")
    ADJUSTMENT
}
