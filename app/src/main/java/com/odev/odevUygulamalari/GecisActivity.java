package com.odev.odevUygulamalari;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.odevUygulamalari.R;

public class GecisActivity extends AppCompatActivity implements View.OnClickListener {

    Button preferans;
    Button mail;
    Button sensor;
    Button kisiListe;
    Intent gecisYap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gecis);
        preferans=(Button)findViewById(R.id.btn_referans);
        mail=(Button)findViewById(R.id.btn_MailGondrme);
        sensor=(Button)findViewById(R.id.btn_Sensor);
        kisiListe=(Button)findViewById(R.id.btn_KisiListe);
        preferans.setOnClickListener(this);
        mail.setOnClickListener(this);
        sensor.setOnClickListener(this);
        kisiListe.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v.getId()==preferans.getId() ){
            gecisYap = new Intent(GecisActivity.this, PreferenceActivity.class);
            startActivity(gecisYap);
        }
        if (v.getId()==mail.getId() ){
            gecisYap = new Intent(GecisActivity.this, SendMailActivity.class);
            startActivity(gecisYap);
        }
        if (v.getId()==sensor.getId() ){
            gecisYap = new Intent(this, SensorActivity.class);
            startActivity(gecisYap);
        }
        if (v.getId()==kisiListe.getId() ){
            gecisYap = new Intent(GecisActivity.this, UserListActivity.class);
            startActivity(gecisYap);
        }
    }
}
