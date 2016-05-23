package com.example.colordemo;
/*
* This class is to set the values of x-coordinate and y-coordinate
 * and color into object
 * created on 20 may by nikhil
 * */

public class DataInformation {
    public int x;
    public int y;
    public int color;

    public DataInformation() {}

    public DataInformation(int x, int y , int color) {
        this.x = x;
        this.y = y;
        this.color=color;
    }

    public DataInformation(DataInformation src) {
        this.x = src.x;
        this.y = src.y;
        this.color=src.color;
    }


}