package com.my.common;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by tianzy on 6/5/14.
 */

@Entity
@Table(name = "stock_new", catalog = "hibernate_test", uniqueConstraints = {
        @UniqueConstraint(columnNames = "STOCK_NAME"),
        @UniqueConstraint(columnNames = "STOCK_CODE")
})
public class StockNew implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "STOCK_ID", unique = true, nullable = false)
    public Integer getStockId() {
        return stockId;
    }

    public void setStockId(Integer stockId) {
        this.stockId = stockId;
    }

    @Column(name = "STOCK_CODE", unique = true, nullable = false, length = 10)
    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    @Column(name = "STOCK_NAME", unique = true, nullable = false, length = 20   )
    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    private Integer stockId;
    private String stockCode;
    private String stockName;

    public StockNew() {}

    public StockNew(String stockCode, String stockName) {
        this.stockCode = stockCode;
        this.stockName = stockName;
    }

}
