package com.example.abhishekpathak.appointmentreminder;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class AddAppointmentActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener {

    TextView txtDate;
    TextView txtTime;

    private int year;
    private int month;
    private int day;

    private int hour;
    private int minute;

    static final int DATE_DIALOG_ID=999;
    static final int TIME_DIALOG_ID=998;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appointment);

       setCurrentDateAndTimeOnView();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Calendar c=Calendar.getInstance();
        c.set(Calendar.YEAR,year);
        c.set(Calendar.MONTH,month);
        c.set(Calendar.DAY_OF_MONTH,dayOfMonth);
        txtDate.setText(new StringBuilder()
                //Month is 0 based,so add 1
                .append(month+1).append("-").append(dayOfMonth).append("-").append(year).append(" "));

    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        Calendar c=Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY,hourOfDay);
        c.set(Calendar.MINUTE,minute);
        //set Current time into Textview
        txtTime.setText(new StringBuilder().append(pad(hourOfDay)).append(":").append(pad(minute)));

    }

    public void setCurrentDateAndTimeOnView() {
        txtDate=(TextView) findViewById(R.id.txttvDate);
        txtTime=(TextView)findViewById(R.id.txttvTime);

        final Calendar c= Calendar.getInstance();
        year=c.get(Calendar.YEAR);
        month=c.get(Calendar.MONTH);
        day=c.get(Calendar.DAY_OF_MONTH);

        hour=c.get(Calendar.HOUR_OF_DAY);
        minute=c.get(Calendar.MINUTE);

        //set Current time into Textview
        txtTime.setText(new StringBuilder().append(pad(hour)).append(":").append(pad(minute)));

        //set Current Date into Textview
        txtDate.setText(new StringBuilder()
        //Month is 0 based,so add 1
                .append(month+1).append("-").append(day).append("-").append(year).append(" "));

        }

    public void edittxtDate(View view) {

        DialogFragment datePicker=new DatePickerFragment();
        datePicker.show(getSupportFragmentManager(),"datePicker");
    }

    public void edittxtTime(View view) {

        DialogFragment timePicker=new TimePickerFragment();
        timePicker.show(getSupportFragmentManager(),"timePicker");
    }


        private String pad(int c){
        if(c>=10)
            return String.valueOf(c);
        else
            return "0"+String.valueOf(c);

        }


    public void btnCancel(View view) {
        finish();
    }

    public void btnAddAppointment(View view) {
        EditText editAppointmentName=findViewById(R.id.edittaskName);
        Spinner spinnerAppointmentType=findViewById(R.id.spnTaskType);
        if(!(editAppointmentName.getText().toString()).isEmpty()){
            Intent intent=new Intent();
            intent.putExtra("name",editAppointmentName.getText().toString());
            intent.putExtra("type",spinnerAppointmentType.getSelectedItem().toString());
            intent.putExtra("monthOfYear",DisplayTheMonthInCharacters(month));
            intent.putExtra("dayOfMonth",day);
            intent.putExtra("year",year);

            intent.putExtra("hour",FormatTheHour(hour));
            intent.putExtra("minute",minute);
            intent.putExtra("AMorPM",AMorPM(hour));

            setResult(RESULT_OK,intent);
            finish();


        }
        else{
            Toast toast=Toast.makeText(AddAppointmentActivity.this,"Please Enter an Appointment Name",Toast.LENGTH_SHORT);
            toast.show();

        }

    }

    private String AMorPM(int passedHour) {
        if(passedHour>12){return "PM";}
        else{return "AM";}
    }

    private int FormatTheHour(int passedHour) {
        if(passedHour>12){passedHour-=12;}
        return passedHour;
    }

    private String DisplayTheMonthInCharacters(int passedMonth) {
        switch (passedMonth){
            case 0:
                return"Jan";
            case 1:
                return"Feb";
            case 2:
                return"Mar";
            case 3:
                return"Apr";
            case 4:
                return"May";
            case 5:
                return"Jun";
            case 6:
                return"Jul";
            case 7:
                return"Aug";
            case 8:
                return"Sep";
            case 9:
                return "Oct";
            case 10:
                return "Nov";
            case 11:
                return "Dec";

                }
        return "";
    }

}
