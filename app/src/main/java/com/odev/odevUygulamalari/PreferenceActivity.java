package com.odev.odevUygulamalari;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.odevUygulamalari.R;

public class PreferenceActivity extends AppCompatActivity {
    RadioButton rb;
    SharedPreferences sharedpreferences;

    public final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "usernameKey";
    public static final String Gender = "genderKey";
    public static final String Age = "ageKey2";
    public static final String Weight = "weightKey2";
    public static final String Height = "heightKey2";
    public static final String ApplicationMode = "applicationKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preference);
        sharedpreferences =getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);

        // Ekrandaki objeleri yakalıyoruz.
        EditText userName=(EditText)findViewById(R.id.txtUserName);
        RadioButton radioF=(RadioButton)findViewById(R.id.radioF);
        final RadioButton radioM=(RadioButton)findViewById(R.id.radioM);
        final RadioGroup radioG=(RadioGroup) findViewById(R.id.radioGrp);
        final EditText age=(EditText)findViewById(R.id.txtAge);
        final EditText weight=(EditText)findViewById(R.id.txtWeight);
        final EditText height=(EditText)findViewById(R.id.txtHeight);
        final Spinner spin=(Spinner)findViewById(R.id.spinner1);
        Spinner dropdown = (Spinner) findViewById(R.id.spinner1);
        Button save=(Button) findViewById(R.id.btnSave);

        // Dropdown'a değerleri dolduruyoruz.
       //dropdown = findViewById(R.id.spinner1);
        String[] color = new String[]{"Light", "Dark", "Blue"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, color);
        dropdown.setAdapter(adapter);

        //DB okuma işlemleri

        // Username
        final String name = sharedpreferences.getString(Name, "Alınamadı");
        userName.setEnabled(false); // TextInput disable ediyoruz.
        userName.setText(name);

        // Application Mode
        String applicationMode = sharedpreferences.getString(name+ApplicationMode, "0");
        dropdown.setSelection(getArrayItemIndex(color, applicationMode));

        // Gender
        String genderMode=sharedpreferences.getString(name+Gender,"Female");
        if (radioF.getText().toString().equals(genderMode)){
            radioF.setChecked(true);
        }else radioM.setChecked(true);

        //Age
        int ageMode=sharedpreferences.getInt(name+Age,0);
        age.setText(String.valueOf(ageMode));


        //Weight
        int weightMode=sharedpreferences.getInt(name+Weight,0);
        weight.setText(String.valueOf(weightMode));

        //Height
        float heightMode=sharedpreferences.getFloat(name+Height,1.70f);
        height.setText(String.valueOf(heightMode));



        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = sharedpreferences.edit();

                int selectedId = radioG.getCheckedRadioButtonId();
                rb = (RadioButton) findViewById(selectedId);

                Boolean hataolmadı=true;

                //Hata kontrolu
                try {

                    //degerleri db ye atama
                    editor.putString(name+Gender, rb.getText().toString());
                    editor.putInt(name+Age,Integer.parseInt(age.getText().toString()));
                    editor.putInt(name+Weight,Integer.parseInt(weight.getText().toString()));
                    editor.putFloat(name+Height,Float.parseFloat(height.getText().toString()));
                    editor.putString(name+ApplicationMode, spin.getSelectedItem().toString());
                    editor.commit();

                }catch (Exception e){
                    hataolmadı=false;

                    AlertDialog.Builder builder = new AlertDialog.Builder(PreferenceActivity.this);
                    builder.setTitle("Hatalı degisiklik yaptınız");
                    builder.setMessage("Girdiğiniz degerleri kontrol ederek tekrar giriniz");
                    builder.setPositiveButton("Tamam", null);
                    builder.show();



                }


                if (hataolmadı==true){
                    AlertDialog.Builder builder = new AlertDialog.Builder(PreferenceActivity.this);
                    builder.setTitle("Mesaj");
                    builder.setMessage("Değisiklikler Kaydedildi. Lütfen tekrar giriş yapın.");
                    builder.setPositiveButton("Tamam",new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int id) {

                            Intent gecisYap;

                            gecisYap = new Intent(PreferenceActivity.this, MainActivity.class);
                            startActivity(gecisYap);

                        }
                    });
                    builder.show();
                }




            }
        });
    }

    private int getArrayItemIndex(String[] array, String item){
        for(int i=0; i < array.length; i++)
            if(array[i].contains(item)) {
                return i;
            }

        return 0;
    }
}
