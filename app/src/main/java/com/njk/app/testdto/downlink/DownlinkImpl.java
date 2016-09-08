package com.njk.app.testdto.downlink;

import com.njk.app.testdto.Market;
import com.njk.app.testdto.Messages;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by meher on 23/8/16.
 */

public class DownlinkImpl implements IDownLink {

    private static DownlinkImpl mInstance = new DownlinkImpl();
    private DownLinkData downLinkData = DownLinkData.getInstance();

    private DownlinkImpl() {
    }

    public static DownlinkImpl getInstance() {
        return mInstance;
    }

    @Override
    public DayData getTodaysData() {
        return downLinkData.getTodaysData();
    }

    @Override
    public void setTodaysData(DayData data) {
        downLinkData.setTodaysData(data);
    }

    @Override
    public HashMap<String,ArrayList<Market>> getProducts() {
        return downLinkData.getProducts();
    }

    @Override
    public int getProductsSize() {
        return downLinkData.getProductsSize();
    }

    @Override
    public ArrayList<Messages> getMessages() {
        return downLinkData.getMessages();
    }

    @Override
    public int getMessagesSize() {
        return downLinkData.getMessagesSize();
    }
}
