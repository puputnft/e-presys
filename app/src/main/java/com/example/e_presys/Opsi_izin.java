package com.example.e_presys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Opsi_izin extends AppCompatActivity {
    ImageButton sakit,izin;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opsi_izin);
        sharedPreferences = getSharedPreferences(Pilihan.root,MODE_PRIVATE);
        sakit = findViewById(R.id.buttonabsensakit);
        izin = findViewById(R.id.buttonabsenizin);
        final SharedPreferences.Editor editor = sharedPreferences.edit();
        sakit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent(Opsi_izin.this,OnlineMhs.class);
                editor.putString(Pilihan.sub_root,"0");
                editor.commit();
                startActivity(x);


            }
        });

        izin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent(Opsi_izin.this,OnlineMhs.class);
                editor.putString(Pilihan.sub_root,"1");
                editor.commit();
                startActivity(x);
            }
        });
    }
}