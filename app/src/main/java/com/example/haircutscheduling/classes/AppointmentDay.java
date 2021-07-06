package com.example.haircutscheduling.classes;

import com.example.haircutscheduling.classes.Data.AppointmentsData;
import com.example.haircutscheduling.classes.DataModels.HairStyleDataModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class AppointmentDay {
    private Day day;
    private HashMap<String, HairStyleDataModel> AppointList;

    public AppointmentDay()
    {
        AppointList = new HashMap<>();
    }

    public AppointmentDay(Day day)
    {
        setDay(day);
        AppointList = new HashMap<>();
    }

    public AppointmentDay(Day day, HashMap<String, HairStyleDataModel> appointList) {
        setDay(day);
        setAppointList(appointList);
    }

    public Day getDay() { return day; }
    public void setDay(Day day) { this.day = day; }
    public HashMap<String, HairStyleDataModel> getAppointList() { return AppointList; }
    public void setAppointList(HashMap<String, HairStyleDataModel> appointList) { AppointList = appointList; }
}
