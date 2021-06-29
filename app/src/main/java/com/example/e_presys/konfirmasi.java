package com.example.e_presys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class konfirmasi extends AppCompatActivity {
    public String url = MainActivity.url;
    private String port = "9005/";
    ImageButton konfirmasi;
    EditText konfirmasi_user, konfirmasi_pass;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi);
        sharedPreferences = getSharedPreferences(Pilihan.root,MODE_PRIVATE);
        konfirmasi_user = findViewById(R.id.konfirmasi_username);
        konfirmasi_pass = findViewById(R.id.konfirmasi_password);
        konfirmasi = findViewById(R.id.konfirmasi);
        konfirmasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(konfirmasi_user.getText().toString().equals("")||konfirmasi_pass.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"Lengkapi Data",Toast.LENGTH_SHORT).show();
                }
                else{
                    confirm(konfirmasi_user.getText().toString(),konfirmasi_pass.getText().toString());
                 }
            }
        });
    }
    void confirm(String username,String password){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url+port).addConverterFactory(GsonConverterFactory.create()).build();
        postlogin postlogin = new postlogin(username, password);
        jsonplaceholder jsonplaceholder = retrofit.create(com.example.e_presys.jsonplaceholder.class);
        Call<postlogin>call = jsonplaceholder.postlogin(postlogin);
        call.enqueue(new Callback<com.example.e_presys.postlogin>() {
            @Override
            public void onResponse(Call<com.example.e_presys.postlogin> call, Response<com.example.e_presys.postlogin> response) {
                if (response.code()==200){
                    Toast.makeText(getApplicationContext(),"Berhasil",Toast.LENGTH_SHORT).show();
                    String alur = sharedPreferences.getString(Pilihan.sub_root,"kosong");
                    if(alur.equals("0")||alur.equals("1")){
                        startActivity(new Intent(com.example.e_presys.konfirmasi.this,Capture_Surat.class));
                        finish();
                    }
                    else if(alur.equals("4")){
                        Toast.makeText(getApplicationContext(),"Welcome to the class",Toast.LENGTH_SHORT).show();
                    }
                    else if(alur.equals("3")){
                        startActivity(new Intent(com.example.e_presys.konfirmasi.this,masuk_kelas.class));
                        finish();
                    }
                    else{
                        Toast.makeText(getApplicationContext(),alur,Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Gagal",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<com.example.e_presys.postlogin> call, Throwable t) {

            }
        });
    }
}