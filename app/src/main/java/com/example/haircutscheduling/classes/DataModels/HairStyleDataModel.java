package com.example.haircutscheduling.classes.DataModels;

public class HairStyleDataModel {

    public void setHairStyle(String hairStyle) {
        this.hairStyle = hairStyle;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setImage(int image) {
        this.image = image;
    }

    String hairStyle;
    String price;
    String date;
    String hour;
    int image;

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    int _id;

    public HairStyleDataModel(String hairStyle, String price, String date, String hour, int image)
    {
        this.hairStyle = hairStyle;
        this.price = price;
        this.date = date;
        this.hour = hour;
        this.image = image;
    }

    public HairStyleDataModel(String hairStyle, String price, String date, String hour, int image, int id)
    {
        this.hairStyle = hairStyle;
        this.price = price;
        this.date = date;
        this.hour = hour;
        this.image = image;
        this._id = id;
    }

    public HairStyleDataModel(String hairStyle, String date, String hour)
    {
        this.hairStyle = hairStyle;
        this.date = date;
        this.hour = hour;
    }

    public HairStyleDataModel(String hairStyle, String price, int image)
    {
        this.hairStyle = hairStyle;
        this.price = price;
        this.image = image;
    }

    public String getDate() { return date; }

    public String getHour() { return hour; }

    public String getHairStyle() { return hairStyle; }

    public String getPrice() { return price; }

    public int getImage() { return image; }
}
