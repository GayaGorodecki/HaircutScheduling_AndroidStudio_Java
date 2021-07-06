package com.example.haircutscheduling.classes.Data;

import com.example.haircutscheduling.classes.AppointmentDay;
import com.example.haircutscheduling.classes.DataModels.UpdateDataModel;

import java.util.ArrayList;
import java.util.HashMap;

public class AppointmentsData {

    public HashMap<String, AppointmentDay> appointmentList;

    public AppointmentsData()
    {
        appointmentList = new HashMap();
    }
}
