package com.njk.app.testdto;

import java.util.ArrayList;

/**
 * Created by meher on 26/7/16.
 */

public class Product {

    private int id = -1;
    private String name;
    private ArrayList<Market> markets;

    public Product() {
    }

    public Product(int id, String name, ArrayList<Market> markets) {
        this.id = id;
        this.name = name;
        if (markets == null)
            this.markets = new ArrayList<>(1);
        else
            this.markets = markets;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Market> getMarkets() {
        return markets;
    }

    public void setMarkets(ArrayList<Market> markets) {
        this.markets = markets;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || object.getClass() != getClass()) {
            return false;
        } else {
            Product product = (Product) object;
            return (this.id == product.getId());
        }

    }

    @Override
    public int hashCode() {

        return id;
    }
}
