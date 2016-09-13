package com.njk.app.testdto.uplink;

import com.njk.app.testdto.Market;
import com.njk.app.testdto.Messages;
import com.njk.app.testdto.Product;

import java.util.ArrayList;

/**
 * Created by meher on 26/7/16.
 */

public interface UpLinkInterface {

    String getDate();

    double getDollarValue();

    void setDollarValue(double value);

    long getUpdatedTime();

    void addMessage(Messages message);

    ArrayList<Messages> getMessages();

    void createProduct(int id, String productName, ArrayList<Market> markets);

    void editProduct(int id, String productName, ArrayList<Market> market);

    void deleteProduct(int id);

    void addMarket(int productId, Market market);

    ArrayList<Product> getAllProducts();

    Product getProduct(int id);

    void addProduct(Product product);
}
