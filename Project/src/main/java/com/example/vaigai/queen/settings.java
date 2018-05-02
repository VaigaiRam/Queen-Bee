package com.example.vaigai.queen;

import android.app.AlarmManager;
import android.app.Application;
import android.app.DatePickerDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class settings extends AppCompatActivity {

    EditText eReminderTime,phone;
    Intent alarmIntent;
    PendingIntent pendingIntent;
    AlarmManager alarmManager;
    String phne,msg;
    Calendar mcurrentTime;
    Switch tb;
    int NOTIFICATION_ID=0,hour,minute;
    private int hr;
    private int min;
    Button ph;
    private static final String ACTION_NOTIFY ="com.example.vaigai.queen.ACTION_NOTIFY";
    NotificationManager mNotificationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);





        eReminderTime=(EditText)findViewById(R.id.edittime) ;
        ph=(Button)findViewById(R.id.button4);

        phone=(EditText)findViewById(R.id.editText9);



        ph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Calendar cur_cal = new GregorianCalendar();
                cur_cal.setTimeInMillis(System.currentTimeMillis());//set the current time and date for this calendar
                long curr=cur_cal.getTimeInMillis();
                Calendar cal = new GregorianCalendar();
                cal.add(Calendar.DAY_OF_YEAR, cur_cal.get(Calendar.DAY_OF_YEAR));
                cal.set(Calendar.HOUR_OF_DAY,hour);
                cal.set(Calendar.MINUTE,minute);
                cal.set(Calendar.SECOND, 0);
                cal.set(Calendar.MILLISECOND,0);
                cal.set(Calendar.DATE, cur_cal.get(Calendar.DATE));
                cal.set(Calendar.MONTH, cur_cal.get(Calendar.MONTH));



                if(cur_cal.get(Calendar.HOUR_OF_DAY) > cal.get(Calendar.HOUR_OF_DAY) )
                {
                    cal.add(Calendar.DAY_OF_YEAR,(cur_cal.get(Calendar.DAY_OF_YEAR))+1);
                    Toast.makeText(settings.this, "hour set", Toast.LENGTH_LONG).show();
                }
                else if(cur_cal.get(Calendar.HOUR_OF_DAY) == cal.get(Calendar.HOUR_OF_DAY))
                {
                    if (cur_cal.get(Calendar.MINUTE) > cal.get(Calendar.MINUTE ))
                    {
                        cal.add(Calendar.DAY_OF_YEAR,(cur_cal.get(Calendar.DAY_OF_YEAR))+1);
                        Toast.makeText(settings.this, "min set", Toast.LENGTH_LONG).show();
                    }
                }

                NotificationManager mNotificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                AlarmManager alarmManager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
                Intent notifyIntent = new Intent(settings.this,AlarmReceiver.class);
                PendingIntent notifyPendingIntent = PendingIntent.getBroadcast
                        (getApplicationContext(), NOTIFICATION_ID, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),24*60*60*1000, notifyPendingIntent);








            }
        });
        eReminderTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                mcurrentTime = Calendar.getInstance();
                hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                minute = mcurrentTime.get(Calendar.MINUTE);

             //   Toast.makeText(settings.this, "hour"+hour+"min"+minute, Toast.LENGTH_LONG).show();
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(settings.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {

                        hour=selectedHour;
                        minute=selectedMinute;
                        String m=Integer.toString(minute);
                        String t=m;
                        if (m.length()==1)
                        {
                            m="0"+t;
                            minute=Integer.parseInt(m);

                        }
                        eReminderTime.setText( selectedHour + ":" + m);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();

            }
        });





    }
    private void deliverNotification(Context context) {


        //Set the toast message for the "on" case

    }











}