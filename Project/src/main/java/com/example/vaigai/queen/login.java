package com.example.vaigai.queen;


import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Locale;

public class login extends AppCompatActivity {

    Button b;
    EditText et,edittext,et1;
    EditText named,dobd,mobd,emaild,passd;
    SQLiteDatabase mydatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mydatabase = openOrCreateDatabase("queen",MODE_PRIVATE,null);
        mydatabase.execSQL("DROP TABLE IF EXISTS profile");
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS profile(name VARCHAR,dob VARCHAR,mobile INT,email VARCHAR,pass VARCHAR,catagory VARCHAR,lpd VARCHAR,height INT,weight INT,spouse VARCHAR,location VARCHAR,week VARCHAR,babyDOB VARCHAR,medication VARCHAR,alarm VARCHAR);");
        EditText password = (EditText) findViewById(R.id.editText11);
        password.setTypeface(Typeface.DEFAULT);
        password.setTransformationMethod(new PasswordTransformationMethod());
        onclickbutton();
        addListenerOnEditText();

    }


    private void addListenerOnEditText() {
        edittext= (EditText) findViewById(R.id.editText4cal);
        edittext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                new DatePickerDialog(login.this, date, myCalendar
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
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(myFormat, Locale.US);

        edittext.setText(sdf.format(myCalendar.getTime()));



    }

    private void onclickbutton() {
        final Context context = this;
        b = (Button)findViewById(R.id.button3);



        b.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {


                named=(EditText)findViewById(R.id.editText3);
                dobd=(EditText)findViewById(R.id.editText4cal);
                mobd=(EditText)findViewById(R.id.editText5);
                emaild=(EditText)findViewById(R.id.editText7);
                passd=(EditText)findViewById(R.id.editText11);
                String names=named.getText().toString();
                String emails=emaild.getText().toString();

     /*  String SQLiteQuery = "INSERT INTO profile (name,dob,mobile,email,pass) VALUES('"+named.getText().toString()+"', '"+dobd.getText().toString()+"', '"+mobd.getText().toString()+"','"+emaild.getText().toString()+"','"+passd.getText().toString()+"');";
        mydatabase.execSQL("insert into profile (name,email) values('"+names+"','naal')");
        mydatabase.execSQL(SQLiteQuery);
*/
               // Toast.makeText(context,"hello"+names,Toast.LENGTH_LONG).show();
                ContentValues contentValues = new ContentValues();
                contentValues.put("name",named.getText().toString());
                contentValues.put("dob", dobd.getText().toString());
                contentValues.put("mobile", mobd.getText().toString());
                contentValues.put("email", emaild.getText().toString());
                contentValues.put("pass", passd.getText().toString());
                mydatabase.insertOrThrow("profile", null, contentValues);
                //mydatabase.close();


                Intent intent = new Intent(context, option.class);
                intent.putExtra("name", named.getText().toString());
                intent.putExtra("email", emaild.getText().toString());
                startActivity(intent);

            }

        });
    }
}
