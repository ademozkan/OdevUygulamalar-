package com.odev.odevUygulamalari;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import com.odevUygulamalari.R;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //SharedPreferences tanımlıyoruz.
    SharedPreferences sharedpreferences;
    public final String MyPREFERENCES = "MyPrefs" ;
    public static final String Name = "usernameKey";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void loginEvent(View view){

        // Ekrandaki component değerlerini alıyoruz.
        EditText kullaniciAdi=(EditText)findViewById(R.id.txtKullaniciAdi);
        EditText parola=(EditText)findViewById(R.id.txtParola);

        //Kullanıcıdan gelen verileri kontrol icin hazırlıyoruz.
        Person person = new Person();
        person.setName(kullaniciAdi.getText().toString());
        person.setPassword(parola.getText().toString());


        if(!isValidUserAndPassword(person)){
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setTitle("Giris Yapılamadı");
            builder.setMessage("Kullanıcı Adı veya Parola Hatalıdır");
            builder.setPositiveButton("Tamam", null);
            builder.show();

            // Ekrandaki textInputu temizliyoruz.
            parola.setText("");
        }
    }

    // Kullanıcı listemzde var mı kontrol ediyoruz.
    private Boolean isValidUserAndPassword(Person person){
        //Dataları alıyoruz.
        List<Person> personList = new UserData().getUserData();

        // For ile Kullanıcı kontrolü yapıyoruz.
        for (Person item:personList) {
            if (item.getName().equals(person.getName()) && item.getPassword().equals(person.getPassword())) {
                sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedpreferences.edit();

                editor.putString(Name, person.getName());
                editor.commit();

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("Giris Basarılı");
                builder.setMessage("Girisiniz Yapılıyor");
                builder.setPositiveButton("Tamam", null);
                builder.show();

                Handler hndler= new Handler();
                hndler.postDelayed(new Runnable() {
                    @Override
                    public void run() {


                        Intent gecisYap = new Intent(MainActivity.this, GecisActivity.class);
                        startActivity(gecisYap);



                    }

                },3000);


                return true;
            }
        }

        return false;
    }

}
