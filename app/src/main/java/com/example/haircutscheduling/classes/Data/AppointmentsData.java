package com.example.haircutscheduling.classes.Data;

import com.example.haircutscheduling.classes.AppointmentDay;
import com.example.haircutscheduling.classes.DataModels.UpdateDataModel;

import java.util.ArrayList;
import java.util.HashMap;

public class AppointmentsData {

    public void setAppointmentList(HashMap<String, AppointmentDay> appointmentList) {
        this.appointmentList = appointmentList;
    }

    public HashMap<String, AppointmentDay> getAppointmentList() {
        return appointmentList;
    }

    public HashMap<String, AppointmentDay> appointmentList;

    public AppointmentsData()
    {
        appointmentList = new HashMap<String, AppointmentDay>();
    }
}
