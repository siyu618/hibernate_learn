package com.my.common;

import java.io.Serializable;

/**
 * Created by tianzy on 6/5/14.
 */
public class Stock implements Serializable {

    public Integer getStockId() {
        return stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    private Integer stockId;
    private String stockCode;
    private String stockName;

    public Stock() {
        ;
    }

    public Stock(String stockCode, String stockName) {
        this.stockCode = stockCode;
        this.stockName = stockName;
    }


}
