package com.njk.app.firebase;

import com.njk.app.dto.Market;


import java.util.HashMap;

/**
 * Created by meher on 28/8/16.
 */

public class ProductModelFirebaseHelper {

    private Double usd;
    private HashMap<String, HashMap<String, Market>> products;

    public ProductModelFirebaseHelper() {
    }

    public HashMap<String, HashMap<String, Market>> getProducts() {
        return products;
    }

    public void setProducts(HashMap<String, HashMap<String, Market>> products) {
        this.products = products;
    }

    public Double getUsd() {
        return usd;
    }

    public void setUsd(Double usd) {
        this.usd = usd;
    }
}
