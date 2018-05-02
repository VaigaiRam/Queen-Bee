package com.example.vaigai.queen;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class choice extends AppCompatActivity {

    ImageButton ib1;
    TextView tv;
    EditText et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        tv=(TextView)findViewById(R.id.textView9);
        et=(EditText)findViewById(R.id.editText6);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
        Intent intent = getIntent();
        String str1 = intent.getStringExtra("name");
        String str2="Hello "+str1;
        tv.setText(str2);
        addListenerOnImageButton();
    }

    public void addListenerOnImageButton() {

        final Context context = this;

        ib1 = (ImageButton)findViewById(R.id.imageButton3);

        ib1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

                Intent intent = new Intent(context, pregnant.class);
                startActivity(intent);

            }

        });

    }
}
