package com.njk.app.testdto.uplink;

import com.njk.app.testdto.Market;
import com.njk.app.testdto.Messages;
import com.njk.app.testdto.Product;

import java.util.ArrayList;

/**
 * Created by meher on 26/7/16.
 */

public class UpLinkImpl implements UpLinkInterface {

    private UplinkData mInstance = UplinkData.getInstance();

    @Override
    public String getDate() {
        return mInstance.getTodayDate();
    }

    @Override
    public double getDollarValue() {
        return mInstance.getDollarValue();

    }

    @Override
    public void setDollarValue(double value) {
        mInstance.setDollarValue(value);
    }

    @Override
    public long getUpdatedTime() {
        return mInstance.getUpdatedTime();
    }

    @Override
    public void addMessage(Messages message) {
        mInstance.addMessage(message);
    }

    @Override
    public ArrayList<Messages> getMessages() {
        return mInstance.getMessages();
    }

    @Override
    public void createProduct(int id, String productName, ArrayList<Market> markets) {
        mInstance.createProduct(id, productName, markets);
    }

    @Override
    public void editProduct(int id, String productName, ArrayList<Market> market) {

        mInstance.editProduct(id, productName, market);

    }

    @Override
    public void deleteProduct(int id) {
        mInstance.deleteProduct(id);

    }

    @Override
    public void addMarket(int productId, Market market) {
        mInstance.addMarket(productId, market);
    }

    @Override
    public ArrayList<Product> getAllProducts() {

        return mInstance.getAllProducts();
    }

    @Override
    public Product getProduct(int id) {
        return mInstance.getProduct(id);
    }

    @Override
    public void addProduct(Product product) {
        mInstance.addProduct(product.getId(), product);
    }
}
