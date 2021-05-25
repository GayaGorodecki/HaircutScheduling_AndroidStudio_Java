package com.example.haircutscheduling.classes.UpdatesBoard;

public class UpdateDataModel {

    String update;
    String date; // TODO: change to Date?
    int id_;

    public UpdateDataModel(String update, String date, int id)
    {
        this.update = update;
        this.date = date;
        this.id_ = id;
    }

    public String getUpdate() {
        return update;
    }

    public String getDate() {
        return date;
    }

    public int getId_() {
        return id_;
    }
}
