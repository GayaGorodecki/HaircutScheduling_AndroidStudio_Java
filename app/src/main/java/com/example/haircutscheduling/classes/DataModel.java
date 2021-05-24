package com.example.haircutscheduling.classes;

public class DataModel {

    String hairStyle;
    String price;
    String date;
    String hour;
    int id_;
    int image;

    public DataModel(String hairStyle, String date, String hour, int id)
    {
        this.hairStyle = hairStyle;
        this.date = price;
        this.hour = hour;
        this.id_ = id_;
    }

    public DataModel(String hairStyle, String price, int id_, int image)
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
