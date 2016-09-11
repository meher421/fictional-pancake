package com.njk.app.dto;

/**
 * Created by meher on 28/8/16.
 */

public class Market {

    String name;
    int bags;
    String date;
    double status;
    int state;
    long timestamp;

    public Market() {
    }

    public Market(String name, double status, int state, int bags, String date, long timestamp) {
        this.name = name;
        this.status = status;
        this.state = state;
        this.date = date;
        this.bags = bags;

        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBags() {
        return bags;
    }

    public void setBags(int bags) {
        this.bags = bags;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getStatus() {
        return status;
    }

    public void setStatus(double status) {
        this.status = status;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public long getTimeStamp() {
        return timestamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timestamp = timeStamp;
    }
}
