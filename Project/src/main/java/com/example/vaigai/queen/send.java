package com.example.vaigai.queen;

import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class send extends AppCompatActivity {
    ImageButton ib;
    String msg,phne,str3;
    SQLiteDatabase mydatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
        Intent intent = getIntent();
        str3 = intent.getStringExtra("email");
        mydatabase = openOrCreateDatabase("queen",MODE_PRIVATE,null);
        ib=(ImageButton)findViewById(R.id.imageButton7);
        ib.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor resultSet = mydatabase.rawQuery("Select spouse from profile where email='"+str3+"'",null);
                resultSet.moveToFirst();
                if (resultSet.getCount()!=0) {

                    phne = resultSet.getString(0);
                }
                msg="SOS!!..It's Baby Time!!\n\tMommy needs attention";
                Intent intent=new Intent(getApplicationContext(),draw.class);

                PendingIntent pi= PendingIntent.getActivity(getApplicationContext(), 0, intent,0);

//Toast.makeText(settings.this, "hour"+hour+"min"+minute, Toast.LENGTH_LONG).show();



//Get the SmsManager instance and call the sendTextMessage method to send message
                SmsManager sms= SmsManager.getDefault();
                sms.sendTextMessage(phne, null, msg,pi,null);

                Toast.makeText(getApplicationContext(),"Message sent to "+phne,
                        Toast.LENGTH_LONG).show();

            }
        });
    }
}
