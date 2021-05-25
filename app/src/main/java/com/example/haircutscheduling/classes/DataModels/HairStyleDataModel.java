package com.example.haircutscheduling.classes.DataModels;

public class HairStyleDataModel {

    String hairStyle;
    String price;
    String date;
    String hour;
    int id_;
    int image;

    public HairStyleDataModel(String hairStyle, String price, String date, String hour, int id,int image)
    {
        this.hairStyle = hairStyle;
        this.price = price;
        this.date = date;
        this.hour = hour;
        this.id_ = id;
        this.image = image;
    }

    public HairStyleDataModel(String hairStyle, String date, String hour, int id)
    {
        this.hairStyle = hairStyle;
        this.date = date;
        this.hour = hour;
        this.id_ = id;
    }

    public HairStyleDataModel(String hairStyle, String price, int id_, int image)
    {
        this.hairStyle = hairStyle;
        this.price = price;
        this.id_ = id_;
        this.image = image;
    }

    public String getDate() { return date; }

    public String getHour() { return hour; }

    public String getHairStyle() { return hairStyle; }

    public String getPrice() { return price; }

    public int getId() { return id_; }

    public int getImage() { return image; }
}
