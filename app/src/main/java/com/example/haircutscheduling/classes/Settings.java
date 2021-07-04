package com.example.haircutscheduling.classes;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

public class Settings {
    public HashMap<String,String> DayOffList;
    public HashMap<String,Day> OperationTime;

    public Settings(){
        DayOffList = new HashMap();
        OperationTime = new HashMap();
    }

    /*public void AddDayOff(String date){ DayOffList.add(date); }
    public void RemoveDayOff(String date){ if(DayOffList.contains(date)) DayOffList.remove(date); }

    public void AddDay(Day day){ OperationTime.add(day); }
    public void RemoveDay(Day day){ if(OperationTime.contains(day)) OperationTime.remove(day); }*/
}
