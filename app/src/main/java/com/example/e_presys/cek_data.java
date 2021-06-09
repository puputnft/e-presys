package com.example.e_presys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class cek_data extends AppCompatActivity {
    TextView mata_kuliah,dosen,waktu_kuliah;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cek_data);

        mata_kuliah = findViewById(R.id.txtmatakuliah);
        dosen = findViewById(R.id.txtdosen);
        waktu_kuliah = findViewById(R.id.txtwaktukuliah);


    }
}