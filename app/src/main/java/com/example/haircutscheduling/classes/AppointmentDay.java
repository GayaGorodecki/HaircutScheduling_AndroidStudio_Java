package com.example.haircutscheduling.classes;

import com.example.haircutscheduling.classes.DataModels.HairStyleDataModel;

import java.util.LinkedList;
import java.util.List;

public class AppointmentDay {
    private Day day;
    private Boolean DayOff;
    private List<HairStyleDataModel> AppointList;

    public AppointmentDay(Day day, Boolean dayOff, List<HairStyleDataModel> appointList) {
        setDay(day);
        setDayOff(dayOff);
        setAppointList(appointList);
    }

    public AppointmentDay(Day day, Boolean dayOff) {
        setDay(day);
        setDayOff(dayOff);
        setAppointList(new LinkedList<HairStyleDataModel>());
    }

    public Day getDay() { return day; }
    public void setDay(Day day) { this.day = day; }
    public Boolean getDayOff() { return DayOff; }
    public void setDayOff(Boolean dayOff) { DayOff = dayOff; }
    public List<HairStyleDataModel> getAppointList() { return AppointList; }
    public void setAppointList(List<HairStyleDataModel> appointList) { AppointList = appointList; }
}
