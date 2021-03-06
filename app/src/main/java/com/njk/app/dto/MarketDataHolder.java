package com.njk.app.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by meher on 28/8/16.
 */

public class MarketDataHolder {

    private Map<String, HashMap<String, Market>> mProducts = new HashMap<>();
    private ArrayList<String> mProductsList;
    private HashMap<String, ArrayList<String>> mMarkets;
    private Double usdValue;
    private ArrayList<Message> messageArrayList = new ArrayList<>();


    public Map<String, HashMap<String, Market>> getProductsData() {
        return mProducts;
    }

    public void setProductsData(Map<String, HashMap<String, Market>> mProducts) {
        this.mProducts = mProducts;
    }

    public ArrayList<String> getProductNames() {
        return mProductsList;
    }

    public void setProductsNames(ArrayList list) {
        mProductsList = list;

    }

    public Market getMarket(String productName, String marketname) {
        return mProducts.get(productName).get(marketname);
    }


    public ArrayList<String> getMarket(String productName) {
        return mMarkets.get(productName);
    }

    public HashMap<String, ArrayList<String>> getMarketsMap() {
        return mMarkets;
    }

    public void setMarketsMap(HashMap<String, ArrayList<String>> list) {
        mMarkets = list;
    }

    public HashMap<String, Market> getProduct(String productName) {
        return mProducts.get(productName);
    }

    public Double getUsd() {
        return usdValue;
    }

    public void setUsd(Double usd) {
        usdValue = usd;
    }

    public ArrayList<Message> getMessageList() {
        return messageArrayList;
    }

    public void setMessageList(ArrayList<Message> list) {
        if (list != null)
            messageArrayList = list;
    }
}
