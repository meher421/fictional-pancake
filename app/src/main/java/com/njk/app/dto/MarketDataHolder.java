package com.njk.app.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by meher on 28/8/16.
 */

public class MarketDataHolder {

    private Map<String, HashMap<String, Market>> mProducts;
    private ArrayList<String> mProductsList;
    private HashMap<String, ArrayList<String>> mMarkets;


    public Map<String, HashMap<String, Market>> getProductsData() {
        return mProducts;
    }

    public void setProductsData(Map<String, HashMap<String, Market>> mProducts) {
        this.mProducts = mProducts;
    }

    public ArrayList<String> getProducts() {
        return mProductsList;
    }

    public void setProducts(ArrayList list) {
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

}
