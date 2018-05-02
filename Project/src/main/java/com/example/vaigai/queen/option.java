package com.example.vaigai.queen;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class option extends AppCompatActivity {
    TextView tv;
    ImageButton ib1,ib2,ib3;
    String str1,str3;
    SQLiteDatabase mydatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        mydatabase = openOrCreateDatabase("queen",MODE_PRIVATE,null);
        tv=(TextView)findViewById(R.id.textView6);
        Intent intent = getIntent();
         str1 = intent.getStringExtra("name");
         str3 = intent.getStringExtra("email");
        String str2="Hello "+str1;
        tv.setText(str2);
        addListenerOnImageButton();
    }

    public void addListenerOnImageButton() {

        final Context context = this;

        ib1 = (ImageButton)findViewById(R.id.imageButton2);
        ib2 = (ImageButton)findViewById(R.id.imageButton5);
        ib3 = (ImageButton)findViewById(R.id.imageButton6);

        ib1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                ContentValues contentValues = new ContentValues();
                contentValues.put("catagory","pregnant");
                mydatabase.update("profile", contentValues,"email='"+str3+"'", null);
                //mydatabase.close();

                Intent intent = new Intent(context, pregnant.class);
                intent.putExtra("name", str1);
                intent.putExtra("email", str3);
                startActivity(intent);

            }

        });

        ib2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                ContentValues contentValues = new ContentValues();
                contentValues.put("catagory","new_mom");
                mydatabase.update("profile", contentValues,"email='"+str3+"'", null);

                              //  mydatabase.close();

                Intent intent = new Intent(context, newmom.class);

                startActivity(intent);

            }

        });

        ib3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                ContentValues contentValues = new ContentValues();
                contentValues.put("catagory","planning");
                mydatabase.update("profile", contentValues,"email='"+str3+"'", null);
              //  mydatabase.close();

                Intent intent = new Intent(context, planning.class);
                startActivity(intent);

            }

        });

    }
}
