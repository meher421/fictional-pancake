package com.njk.app.testdto.uplink;

import com.njk.app.utils.Logger;
import com.njk.app.utils.Util;
import com.njk.app.testdto.Market;
import com.njk.app.testdto.Messages;
import com.njk.app.testdto.Product;

import java.util.ArrayList;

/**
 * Created by meher on 26/7/16.
 */

public class UplinkData {

    private static UplinkData uplinkData = new UplinkData();
    private final String TAG = "Uplink-12345";
    private ArrayList<Product> mProducts = new ArrayList<Product>();
    private ArrayList<Messages> mMessages = new ArrayList<>();
    private String messageTitle;
    private String message;
    private double dollarValue;

    public static UplinkData getInstance() {
        return uplinkData;
    }

    public void createProduct(int id, String productName, ArrayList<Market> markets) {
        Product product = new Product(id, productName, markets);
        mProducts.add(product);
        Logger.i(TAG, " products size ;  " + mProducts.size());

    }

    public void addProduct(int id, Product product) {
        int index = mProducts.indexOf(id);
        if (index > 0) {
            mProducts.remove(id);
            mProducts.add(index, product);

        } else {
            mProducts.add(product);

        }

    }

    public void editProduct(int id, String productName, ArrayList<Market> market) {

        int index = mProducts.indexOf(id);
        if (index > 0) {
            Product product = mProducts.get(index);
            product.setName(productName);
            product.setMarkets(market);
        }

    }

    public void deleteProduct(int id) {
        int index = mProducts.indexOf(id);

        if (index > 0) {
            mProducts.remove(index);
        }
    }

    public void addMarket(int productId, Market market) {
        int index = mProducts.indexOf(productId);

        if (index > 0) {
            mProducts.get(index).getMarkets().add(market);
        }

    }

    public ArrayList<Product> getAllProducts() {
        return mProducts;
    }

    public Product getProduct(int productId) {
        int index = mProducts.indexOf(productId);
        if (index > 0) {
            return mProducts.get(index);
        }
        return null;
    }

    public String getTodayDate() {

        return Util.getTodayDate();

    }

    public double getDollarValue() {
        return dollarValue;

    }

    public void setDollarValue(double dollarValue) {
        this.dollarValue = dollarValue;
    }

    public long getUpdatedTime() {
        return System.currentTimeMillis();
    }

    public String getMessageTitle() {
        return messageTitle;
    }

    public void setMessageTitle(String messageTitle) {
        this.messageTitle = messageTitle;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<Messages> getMessages() {
        return mMessages;
    }

    public void addMessage(Messages messages) {
        mMessages.add(messages);
    }
}
