package com.example.haircutscheduling.classes.BookedAppointments;

public class BookedDataModel
{
    String hairStyle;
    String price;
    String date;
    String hour;
    int id_;
    int image;

    public BookedDataModel(String hairStyle, String price, String date, String hour, int id,int image)
    {
        this.hairStyle = hairStyle;
        this.price = price;
        this.date = date;
        this.hour = hour;
        this.id_ = id;
        this.image = image;
    }

    public int getId_() {
        return id_;
    }

    public String getDate() {
        return date;
    }

    public int getImage() {
        return image;
    }

    public String getHairStyle() {
        return hairStyle;
    }

    public String getHour() {
        return hour;
    }

    public String getPrice() {
        return price;
    }
}
