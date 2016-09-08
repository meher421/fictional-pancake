package com.njk.app.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by meher on 28/8/16.
 */

public class MarketHelper implements IMarket {

    private static MarketHelper mInstance = new MarketHelper();
    private MarketDataHolder linkData = new MarketDataHolder();


    private MarketHelper() {
    }

    public static MarketHelper getInstance() {
        return mInstance;
    }


    @Override
    public Map<String, HashMap<String, Market>> getProductsData() {
        return linkData.getProductsData();
    }

    @Override
    public void setProductsData(Map<String, HashMap<String, Market>> data) {
        linkData.setProductsData(data);
    }

    @Override
    public ArrayList<String> getProducts() {
      return linkData.getProducts();
    }

    @Override
    public void setProducts(ArrayList<String> list) {
        linkData.setProducts(list);

    }

    @Override
    public void setMarketsMap(HashMap<String,ArrayList<String>> test) {
        linkData.setMarketsMap(test);

    }

    @Override
    public HashMap<String,ArrayList<String>> getMarketsMap() {
        return linkData.getMarketsMap();
    }


    public void getMarket(String productId,String market) {
        linkData.getMarket(productId,market);
    }


    public ArrayList<String> getMarket(String productId) {
        return linkData.getMarket(productId);
    }
}
