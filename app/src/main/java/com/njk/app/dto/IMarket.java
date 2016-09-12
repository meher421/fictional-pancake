package com.njk.app.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by meher on 28/8/16.
 */

public interface IMarket {

    /**
     * start complete product data.ie universal
     */
    Map<String, HashMap<String, Market>> getProductsData();

    void setProductsData(Map<String, HashMap<String, Market>> data);

    /** end */


    /**
     * start  Contains only product names, used as keys to fetch market data of that product
     */
    ArrayList<String> getProductNames();

    void setProductsNames(ArrayList<String> list);
    /** end */


    /**
     * start  get the list of markets ,provided product name
     */
    HashMap<String, ArrayList<String>> getMarketsMap();

    void setMarketsMap(HashMap<String, ArrayList<String>> test);

    /**
     * end
     */

    /**
     * start  dollar value of the day
     */
    Double getUsdValue();


    void setUsdValue(Double usd);
    /** end */
}
