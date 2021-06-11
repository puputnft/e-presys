package com.example.e_presys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Pilihan extends AppCompatActivity {
    ImageButton online, offline, absen, cek_data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilihan);
        online = findViewById(R.id.txtbuttononline);
        offline = findViewById(R.id.txtbuttonoffline);
        absen = findViewById(R.id.txtbuttonabsen);
        cek_data = findViewById(R.id.txtbuttoncekdata);

        online.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent(Pilihan.this,OnlineMhs.class);
                MainActivity.keterangan_kode=3;
                startActivity(x);

            }
        });
        offline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent(Pilihan.this,OnlineMhs.class);
                startActivity(x);

            }
        });
        absen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent(Pilihan.this,Opsi_izin.class);
                startActivity(x);


            }
        });
        cek_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent(Pilihan.this,cek_data.class);
                startActivity(x);

            }
        });
    }
}