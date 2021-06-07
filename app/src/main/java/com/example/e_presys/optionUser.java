package com.example.e_presys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class optionUser extends AppCompatActivity {
    ImageButton txtoptiondosen, txtoptionmahasiswa;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option_user);
        txtoptiondosen = findViewById(R.id.txtoptiondosen);
        txtoptionmahasiswa = findViewById(R.id.txtoptionmahasiswa);

        txtoptiondosen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(optionUser.this,Register.class);
                i.putExtra("code",String.valueOf(1));
                startActivity(i);
                finish();
            }
        });
        txtoptionmahasiswa.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(optionUser.this,Register.class);
                i.putExtra("code",String.valueOf(0));
                startActivity(i);
                finish();
            }
        }));
    }

}