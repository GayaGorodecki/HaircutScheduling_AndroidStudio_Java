package com.example.haircutscheduling.classes;

public class DataModel {

    String hairStyle;
    String description;
    int id_;
    int image;

    public DataModel(String hairStyle, String description, int id_, int image)
    {
        this.hairStyle = hairStyle;
        this.description = description;
        this.id_ = id_;
        this.image = image;
    }

    public String getHairStyle() { return hairStyle; }

    public String getDescription() { return description; }

    public int getId() { return id_; }

    public int getImage() { return image; }
}
