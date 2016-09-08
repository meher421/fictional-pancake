package com.njk.app.testdto;

/**
 * Created by meher on 6/8/16.
 */

public class Messages {
    private long lastUpdate;
    private String message;
    private String title;
    public Messages() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
