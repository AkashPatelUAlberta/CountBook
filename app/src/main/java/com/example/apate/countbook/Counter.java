package com.example.apate.countbook;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Akash on 2017-09-25.
 */

public class Counter {

    private String name;
    private Date date;
    private int current_val;
    private int initial_val;
    private String comment;

    public Counter (String name, int current_val, int initial_val, String comment) {
        this.name = name;
        this.date = new Date();
        this.current_val = current_val;
        this.initial_val = initial_val;
        this.comment = comment;
    }

    public int getCurrent_val() {
        return this.current_val;
    }

    public void setCurrent_val(int current_val) {
        this.current_val = current_val;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    public int getInitial_val() {
        return this.initial_val;
    }

    public void setInitial_val(int initial_val) {
        this.initial_val = initial_val;
    }

    public String getComment() {
        return this.comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void increment() {
        this.current_val++;
    }

    public void decrement() {
        this.current_val--;
    }
}
