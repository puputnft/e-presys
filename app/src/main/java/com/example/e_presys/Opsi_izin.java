package com.example.e_presys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Opsi_izin extends AppCompatActivity {
    ImageButton sakit,izin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opsi_izin);

        sakit = findViewById(R.id.buttonabsensakit);
        izin = findViewById(R.id.buttonabsenizin);

        sakit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent(Opsi_izin.this,OnlineMhs.class);
                MainActivity.keterangan_kode=0;
                startActivity(x);


            }
        });

        izin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent(Opsi_izin.this,OnlineMhs.class);
                MainActivity.keterangan_kode=1;
                startActivity(x);
            }
        });
    }
}