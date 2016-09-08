package com.njk.app.testdto.downlink;

import com.njk.app.testdto.Market;
import com.njk.app.testdto.Messages;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by meher on 21/8/16.
 */

public class DayData {

    private double dollar;
    private long lastUpdated;
    private ArrayList<Messages> messages;
    private HashMap<String,ArrayList<Market>> products;
    public DayData() {

    }

    public double getDollar() {
        return dollar;
    }

    public void setDollar(double dollar) {
        this.dollar = dollar;
    }

    public long getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(long lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public ArrayList<Messages> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Messages> messages) {
        this.messages = messages;
    }

    public HashMap<String,ArrayList<Market>> getProducts() {
        return products;
    }

    public void setProducts(HashMap<String,ArrayList<Market>> products) {
        this.products = products;
    }
}
