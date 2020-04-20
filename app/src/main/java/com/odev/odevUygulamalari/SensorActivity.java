package com.odev.odevUygulamalari;

//Isık ve Ivme sensor Uygulamasını iceriyor

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.odevUygulamalari.R;

import java.util.List;

public class SensorActivity extends AppCompatActivity implements SensorEventListener {

    TextView sensorListesi;
    TextView isikSiddeti;
    TextView ivmeSiddeti;
    LinearLayout linearLayout;
    private SensorManager mySensorManager;
    CountDownTimer countDownTimer;
    boolean k=true;
    float x=0;
    float y=0;
    float z=0;
    int xsaniye1=614646465;
    int ysaniye1=965264622;
    int zsaniye1=668183664;
    int xsaniye10=876864362;
    int ysaniye10=674666325;
    int zsaniye10=323532463;
    int sayac=0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);

        sensorListesi = (TextView) findViewById(R.id.txt_SensorListesi);
        isikSiddeti = (TextView) findViewById(R.id.txt_SensorDegeri);
        ivmeSiddeti = (TextView) findViewById(R.id.txt_ivme);
        linearLayout = (LinearLayout) findViewById(R.id.LnrLayout);


        sensorListesi.setVisibility(View.GONE);

        mySensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        List<Sensor> mList = mySensorManager.getSensorList(Sensor.TYPE_ALL);

        for (int i = 1; i < mList.size(); i++) {
            sensorListesi.setVisibility(View.VISIBLE);

            sensorListesi.append(String.valueOf(i) + "-)" + mList.get(i).getName() + "\n" + mList.get(i).getVendor() + "\n" + mList.get(i).getVersion() + "\n");
        }

        Sensor isikSensoru = mySensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        Sensor ivmeSensoru = mySensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);


        mySensorManager.registerListener(this, ivmeSensoru, SensorManager.SENSOR_DELAY_NORMAL);
        mySensorManager.registerListener(this, isikSensoru, SensorManager.SENSOR_DELAY_FASTEST);


        countDownTimer=new CountDownTimer(86400000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (k){
                    xsaniye1=Math.round(x);
                    ysaniye1=Math.round(y);
                    zsaniye1=Math.round(z);


                }else {
                    xsaniye10=Math.round(x);
                    ysaniye10=Math.round(y);
                    zsaniye10=Math.round(z);

                }
                k=!k;


                if (xsaniye1==xsaniye10 && ysaniye1==ysaniye10 &&zsaniye1==zsaniye10){
                    sayac++;


                    if (sayac % 5==0){


                        AlertDialog.Builder builder = new AlertDialog.Builder(SensorActivity.this);
                        builder.setTitle("Uyarı");
                        builder.setMessage("Uzun 5 saniye ivme  degişmediğinden cıkıs yapılacaktır.");
                        builder.setPositiveButton("Tamam",new DialogInterface.OnClickListener(){
                            public void onClick(DialogInterface dialog, int id) {
                                ActivityCompat.finishAffinity(SensorActivity.this);
                            }
                        });
                        builder.show();

                        Handler hndler= new Handler();
                        hndler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                ActivityCompat.finishAffinity(SensorActivity.this);

                            }

                        },3000);








                    }


                }else {
                    sayac=0;
                }




            }

            @Override
            public void onFinish() {

            }
        }.start();


    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_LIGHT) {
            float sensordenGelenDeger = event.values[0];
            isikSiddeti.setText("Işık Sensör Degeri:" + String.valueOf(sensordenGelenDeger)+"  Kapanma Süresi:"+String.valueOf(sayac));


            if (sensordenGelenDeger < 2) {
                isikSiddeti.setTextColor(Color.WHITE);
                sensorListesi.setTextColor(Color.WHITE);
                ivmeSiddeti.setTextColor(Color.WHITE);
                linearLayout.setBackgroundColor(Color.BLACK);
            } else {
                isikSiddeti.setTextColor(Color.BLACK);
                sensorListesi.setTextColor(Color.BLACK);
                ivmeSiddeti.setTextColor(Color.BLACK);
                linearLayout.setBackgroundColor(Color.WHITE);
            }

        }
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            ivmeSiddeti.setText("X : " + Math.round(event.values[0])+ "\nY : " + Math.round(event.values[1]) + "\nZ : " + Math.round(event.values[2]));
            x=event.values[0];
            y=event.values[1];
            z=event.values[2];
        }


    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

}
