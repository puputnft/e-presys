package com.example.e_presys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class Pilihan extends AppCompatActivity {
    ImageButton online, offline, absen, cek_data;
    public SharedPreferences sharedpreferences;
    public static String root = "root";
    public static String sub_root = "sub_root";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilihan);
        online = findViewById(R.id.txtbuttononline);
        offline = findViewById(R.id.txtbuttonoffline);
        absen = findViewById(R.id.txtbuttonabsen);
        cek_data = findViewById(R.id.txtbuttoncekdata);
        sharedpreferences = getSharedPreferences(root, Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = sharedpreferences.edit();
        MainActivity.keterangan_kode = 0;
        online.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent(Pilihan.this,OnlineMhs.class);
                editor.putString(sub_root,"3");
                editor.commit();
                startActivity(x);

            }
        });
        offline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent(Pilihan.this,OnlineMhs.class);
                editor.putString(sub_root,"4");
                editor.commit();
                startActivity(x);

            }
        });
        absen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString(sub_root,"5");
                editor.commit();
                Intent x = new Intent(Pilihan.this,Opsi_izin.class);
                startActivity(x);


            }
        });
        cek_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent x = new Intent(Pilihan.this,history.class);
                startActivity(x);

            }
        });
    }
}