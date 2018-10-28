package com.ieg.ders3;

import java.io.Serializable;

public class SMSModel implements Serializable {
    String title;
    String body;

    public SMSModel(String title, String body) {
        this.title = title;
        this.body = body;
    }
}
