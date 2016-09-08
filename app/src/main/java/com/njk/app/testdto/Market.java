package com.njk.app.testdto;

/**
 * Created by meher on 26/7/16.
 */

public class Market {

    private int id = -1;
    private String name = "";
    private long bags;
    private double price;
    private int state;
    private int status;

    public Market() {
    }

    public Market(int id,String name, long bags, double price, int status) {
        this.id = id;
        this.name =name;
        this.bags = bags;
        this.price = price;
        this.status = status;

    }

    public Market(int id) {
        this.id = id;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public void setMarketName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getBags() {
        return bags;
    }

    public void setBags(long bags) {
        this.bags = bags;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
