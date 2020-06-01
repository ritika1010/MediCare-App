package com.example.medicareapp;

import java.io.Serializable;

public class Log_data implements Serializable {
    private int id;
    private String log_name;
    private String image_name;

    public Log_data(int id, String ln, String in) {
        this.id = id;
        this.log_name = ln;
        this.image_name = in;

    }

    public String ifClicked()
    {

        return log_name;
    }

    public int getId() {
        return id;
    }

    public String getLog_name() {
        return log_name;
    }

    public String getImage_name() {
        return image_name;
    }


}