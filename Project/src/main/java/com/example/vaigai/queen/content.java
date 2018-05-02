package com.example.vaigai.queen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

public class content extends AppCompatActivity {

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        tv=(TextView)findViewById(R.id.person_age);
        Intent intent = getIntent();
        String str1 = intent.getStringExtra("name");
        String str2="You are "+str1+" weeks pregnant";
        tv.setText(str2);


    }
}

