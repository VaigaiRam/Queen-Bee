package com.example.vaigai.queen;

import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;

import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class pregnant extends AppCompatActivity  {
    EditText edittext,phone;
    EditText tv,lpdd,hd,wd,locd;
    Button b1;
    String weekinstr,msg,phne;
    String samp;
    String str1,str3;
    PlaceAutocompleteFragment autocompleteFragment;
    SQLiteDatabase mydatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pregnant);
        mydatabase = openOrCreateDatabase("queen",MODE_PRIVATE,null);
        addListenerOnEditText();
        addListenerOnButton();
        Intent intent = getIntent();
        phone=(EditText)findViewById(R.id.editText9);
        str1 = intent.getStringExtra("name");
        str3 = intent.getStringExtra("email");

        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment)
                getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

/*
* The following code example shows setting an AutocompleteFilter on a PlaceAutocompleteFragment to
* set a filter returning only results with a precise address.
*/
        AutocompleteFilter typeFilter = new AutocompleteFilter.Builder()
                .setTypeFilter(AutocompleteFilter.TYPE_FILTER_ADDRESS)
                .build();
        autocompleteFragment.setFilter(typeFilter);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener() {
            @Override
            public void onPlaceSelected(Place place) {
                // TODO: Get info about the selected place.
                String s=place.getName().toString();
                tv=(EditText) findViewById(R.id.edit);
                tv.setText(s);

            }

            @Override
            public void onError(Status status) {
                // TODO: Handle the error.

            }
        });

    }

    private void addListenerOnButton() {

        final Context context = this;

        b1 = (Button)findViewById(R.id.button2);

        b1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                lpdd=(EditText)findViewById(R.id.editText);
                hd=(EditText)findViewById(R.id.editText2);
                wd=(EditText)findViewById(R.id.editText3);
                locd=(EditText)findViewById(R.id.edit);
                EditText sp=(EditText)findViewById(R.id.editText9);


                ContentValues contentValues = new ContentValues();
                contentValues.put("week",weekinstr);
                contentValues.put("lpd",lpdd.getText().toString());
                contentValues.put("height",hd.getText().toString());
                contentValues.put("weight",wd.getText().toString());
                contentValues.put("spouse",sp.getText().toString());
                contentValues.put("location",locd.getText().toString());
                mydatabase.update("profile", contentValues,"email='"+str3+"'", null);
               // mydatabase.close();
                Intent intent=new Intent(getApplicationContext(),draw.class);




                Intent intent1 = new Intent(pregnant.this, draw.class);
                intent1.putExtra("name", weekinstr);
                intent1.putExtra("name1", str1);
                intent1.putExtra("email", str3);
                startActivity(intent1);

            }

        });

    }


    private void addListenerOnEditText() {
        edittext= (EditText) findViewById(R.id.editText);
        edittext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(pregnant.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

    }

    Calendar myCalendar = Calendar.getInstance();


    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            // TODO Auto-generated method stub
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }

    };

    private void updateLabel() {
        String myFormat = "dd/MM/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        edittext.setText(sdf.format(myCalendar.getTime()));


        try{
            String myFormat1 = "dd MM yyyy"; //In which you need put here
            SimpleDateFormat sdf1 = new SimpleDateFormat(myFormat1, Locale.US);

            String output1 = sdf1.format(myCalendar.getTime());
            Date date=new Date();
            String output2 = sdf1.format(date);
            Date date1=sdf1.parse(output2);
            Date date2=sdf1.parse(output1);
            long difference = date1.getTime() - date2.getTime();
            float daysBetween = (difference / (1000*60*60*24));
            int week=(int)(daysBetween/7);
            String days=Float.toString(daysBetween);
            weekinstr=Integer.toString(week);
            samp="cal date: "+output1+" current: "+output2+" days: "+Float.toString(difference)+" Weeks: "+weekinstr;
          //  Toast.makeText(this, samp, Toast.LENGTH_SHORT).show();


        }
        catch (Exception e)
        {
            e.printStackTrace();
        }



    }
}
