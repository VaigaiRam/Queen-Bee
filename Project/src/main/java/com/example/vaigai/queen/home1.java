package com.example.vaigai.queen;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class home1 extends AppCompatActivity {
TextView tv;
ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home1);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.thumb)
                .setContentTitle("Queen Bee")
                .setContentText("Welcome Queen Bee!!!");
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(001, mBuilder.build());
        tv=(TextView) findViewById(R.id.textView15);
        iv=(ImageView)findViewById(R.id.imageView2);
        onClickTextView();
    }

    private void onClickTextView() {
        iv.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent intent = new Intent(home1.this, login.class);
                startActivity(intent);
            }
        });
    }
}
