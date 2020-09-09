package com.example.abhishekpathak.appointmentreminder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    ArrayList<Appointment>appointmentArrayList=new ArrayList<Appointment>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
         ArrayList<Appointment>appointmentArrayList=new ArrayList<Appointment>();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CreateSomeTestAppointmentsToStartWith();
    }

    private void CreateSomeTestAppointmentsToStartWith() {


        appointmentArrayList.add(new Appointment("Doctors visit","Health","Oct",9,2016,9,00,"AM"));
        appointmentArrayList.add(new Appointment("gf visit","Personal","Nov",9,2016,9,00,"AM"));
        appointmentArrayList.add(new Appointment("teacher visit","School","Jun",9,2016,9,00,"AM"));
        appointmentArrayList.add(new Appointment("Boss visit","Work","Apr",9,2016,9,00,"AM"));
        appointmentArrayList.add(new Appointment("Doctors visit","Health","Oct",9,2016,9,00,"AM"));
        appointmentArrayList.add(new Appointment("Doctors visit","Health","Oct",9,2016,9,00,"AM"));

        for(int i=0;i<appointmentArrayList.size();i++){
            PopulateTable(i);
        }


    }
    private String setToDateAndTime(Appointment appointment){

        long currentDateAndTime=System.currentTimeMillis();//Todays date
        SimpleDateFormat formatDate=new SimpleDateFormat("MMM,d,yyyy");//Date Format
        String todaysDate=formatDate.format(currentDateAndTime);
        String passDate=appointment.monthDate+" "+appointment.daydate+","+appointment.yearDate;
        if(Objects.equals(todaysDate,passDate)){
            return appointment.hourTime+":"+appointment.minuteTime+" "+appointment.AMorPMTime;

            }
            return appointment.monthDate+" "+appointment.daydate+","+appointment.yearDate;
        }

    @Override
    //Returns information passed from AddAppointmentActivity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode==1){
            if(resultCode==RESULT_OK){
                //Creates the new appointment with the information passed
                appointmentArrayList.add(new Appointment(
                        data.getStringExtra("name"),data.getStringExtra("type"),
                data.getStringExtra("monthOfYear"),data.getIntExtra("dayOfMonth",0),data.getIntExtra("year",0),
                        data.getIntExtra("hour",11),data.getIntExtra("minute",11),data.getStringExtra("AMorPM")
                ));

                PopulateTable(appointmentArrayList.size()-1);


            }



        }








    }

    private void PopulateTable(int arrayListCounter) {
        TableLayout appointmentTBL=findViewById(R.id.tblTaskContent);
        TableRow newTableRow=new TableRow(this);
        TableRow.LayoutParams lp=new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT,TableRow.LayoutParams.WRAP_CONTENT);
        newTableRow.setLayoutParams(lp);

        TextView txtvName=new TextView(this);
        txtvName.setLayoutParams(lp);
        txtvName.setGravity(Gravity.CENTER);
        txtvName.setText(appointmentArrayList.get(arrayListCounter).name);
        txtvName.setWidth(140);
        txtvName.setTextSize(12);
        txtvName.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        TextView txtvType=new TextView(this);
        txtvType.setLayoutParams(lp);
        txtvType.setGravity(Gravity.CENTER);
        txtvType.setText(appointmentArrayList.get(arrayListCounter).type);
        txtvType.setWidth(93);
        txtvType.setTextSize(12);
        txtvType.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        TextView txtvDate=new TextView(this);
        txtvDate.setLayoutParams(lp);
        txtvDate.setGravity(Gravity.CENTER);
        txtvDate.setText(setToDateAndTime(appointmentArrayList.get(arrayListCounter)));
        txtvDate.setWidth(93);
        txtvDate.setTextSize(12);
        txtvDate.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);

        newTableRow.addView(txtvName);
        newTableRow.addView(txtvType);
        newTableRow.addView(txtvDate);
        appointmentTBL.addView(newTableRow,arrayListCounter+1);


    }

    public void AddAppointmentbtn(View view) {
        startActivityForResult(new Intent(this,AddAppointmentActivity.class),1);
    }
}
