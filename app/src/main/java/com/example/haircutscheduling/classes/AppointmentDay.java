package com.example.haircutscheduling.classes;

import com.example.haircutscheduling.classes.Data.AppointmentsData;
import com.example.haircutscheduling.classes.DataModels.HairStyleDataModel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class AppointmentDay {
    private Day day;
    private ArrayList<HairStyleDataModel> AppointList;

    public AppointmentDay()
    {
        AppointList = new ArrayList<>();
    }

    public AppointmentDay(Day day)
    {
        setDay(day);
        AppointList = new ArrayList<>();
    }

    public AppointmentDay(Day day, ArrayList<HairStyleDataModel> appointList) {
        setDay(day);
        setAppointList(appointList);
    }

    public Day getDay() { return day; }
    public void setDay(Day day) { this.day = day; }
    public ArrayList<HairStyleDataModel> getAppointList() { return AppointList; }
    public void setAppointList(ArrayList<HairStyleDataModel> appointList) { AppointList = appointList; }
}
