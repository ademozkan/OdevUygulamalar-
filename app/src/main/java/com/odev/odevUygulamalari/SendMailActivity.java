package com.odev.odevUygulamalari;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.odevUygulamalari.R;

public class SendMailActivity extends AppCompatActivity implements View.OnClickListener {
    //Burada değiskenler tanımlanıstır.
    EditText editTextTo,editTextSubject,editTextMessage;
    Button send,attacthment,temizle;
    TextView tvAttachment;
    Uri URI=null;
    private static final int PICK_FROM_GALLERY = 101;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_mail);
        //userinterfacedeki elementler burda cekilip degişkenlere atanmstır.
        editTextTo=(EditText)findViewById(R.id.edText_Kime);
        editTextSubject=(EditText)findViewById(R.id.edText_Konu);
        editTextMessage=(EditText)findViewById(R.id.edText_Mesaj);
        tvAttachment=(TextView)findViewById(R.id.tvAttachment);

        send=(Button)findViewById(R.id.btn_Gonder);
        attacthment=(Button)findViewById(R.id.btn_Ek);
        temizle=(Button)findViewById(R.id.btn_Temizle);
        send.setOnClickListener(SendMailActivity.this);
        attacthment.setOnClickListener(SendMailActivity.this);
        temizle.setOnClickListener(SendMailActivity.this);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_FROM_GALLERY && resultCode == RESULT_OK) {
            URI = data.getData();
            tvAttachment.setText("\uD83D\uDCCE"+URI.getLastPathSegment());
            tvAttachment.setVisibility(View.VISIBLE);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    //Butona click işleminde yapılması gerekenler  tanımlanmıstır.
    @Override
    public void onClick(View v) {
        if (v.getId()==send.getId()){
            String to=editTextTo.getText().toString();
            String subject=editTextSubject.getText().toString();
            String message=editTextMessage.getText().toString();

            Intent email=new Intent(Intent.ACTION_SEND);
            //Gelen mail adresi birden cok olabilecegınden dizi tanımlanmıstır.
            email.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
            email.putExtra(Intent.EXTRA_SUBJECT,subject);
            email.putExtra(Intent.EXTRA_TEXT,message);
            if (URI != null) {
                email.putExtra(Intent.EXTRA_STREAM, URI);
            }

            email.setType("message/rfc822");
            startActivity(Intent.createChooser(email,"Gondermek istediginiz uygulamayı secin"));
            editTextTo.setText("");
            editTextMessage.setText("");
            editTextSubject.setText("");
            tvAttachment.setText("");

        }

        if (v.getId()==attacthment.getId()){
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            intent.putExtra("return-data", true);
            startActivityForResult(Intent.createChooser(intent, "Complete action using"), PICK_FROM_GALLERY);
        }
        if (v.getId()==temizle.getId()){
            editTextTo.setText("");
            editTextMessage.setText("");
            editTextSubject.setText("");
            tvAttachment.setText("");

        }

    }
}
