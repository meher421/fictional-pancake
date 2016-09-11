package com.njk.app.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by meher on 28/8/16.
 */

public interface IMarket {

    Map<String, HashMap<String, Market>> getProductsData();

    void setProductsData(Map<String, HashMap<String, Market>> data);

    ArrayList<String> getProductNames();

    void setProductsNames(ArrayList<String> list);


    void setMarketsMap(HashMap<String,ArrayList<String>> test);

    HashMap<String,ArrayList<String>> getMarketsMap();


}
