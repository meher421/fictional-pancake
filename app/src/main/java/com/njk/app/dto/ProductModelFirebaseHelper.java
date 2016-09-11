package com.njk.app.dto;

import java.util.HashMap;

/**
 * Created by meher on 28/8/16.
 */

public class ProductModelFirebaseHelper {

    public ProductModelFirebaseHelper(){}


    private HashMap<String,HashMap<String,Market>> products;

    public HashMap<String, HashMap<String, Market>> getProducts() {
        return products;
    }

    public void setProducts(HashMap<String, HashMap<String, Market>> products) {
        this.products = products;
    }




}
