package com.github.m5.cloud.config.client;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaoyu
 */

public class StockSearch {
    public static final String INDEX_NAME = "stock-search";
    public static final String INDEX_TYPE = "doc";
    private String stockCode;

    private String stockName;

    private String companyName;
    private String listingDate;


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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getListingDate() {
        return listingDate;
    }

    public void setListingDate(String listingDate) {
        this.listingDate = listingDate;
    }

    public Map<String, String> map() {
        Map<String, String> map = new HashMap<>();
        map.put("stockCode", stockCode);
        map.put("stockName", stockName);
        map.put("companyName", companyName);
        map.put("listingDate", listingDate);
        return map;
    }

}
