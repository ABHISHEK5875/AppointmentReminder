package com.example.abhishekpathak.appointmentreminder;

public class Appointment {
    public String name;
    public  String type;
    public String monthDate;
    public  int daydate;
    public  int yearDate;
    public int hourTime;
    public  int minuteTime;
    public String AMorPMTime;

    public Appointment(String passedAppointmentName,String passedAppointmentType,
                       String passedAppointmentDateMonth,int passedAppointmentDateDay,
                       int passedAppointmentDateYear,int passedAppointmentTimeHour,
                       int passedAppointmentTimeMinute,String passedAppointmentTimeAMorPM
                       ){
        name=passedAppointmentName;
        type=passedAppointmentType;
        monthDate=passedAppointmentDateMonth;
        daydate=passedAppointmentDateDay;
        yearDate=passedAppointmentDateYear;
        hourTime=passedAppointmentTimeHour;
        minuteTime=passedAppointmentTimeMinute;
        AMorPMTime=passedAppointmentTimeAMorPM;


    }



}
