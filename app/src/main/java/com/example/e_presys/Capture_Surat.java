package com.example.e_presys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Capture_Surat extends AppCompatActivity {
    public static String url = MainActivity.url;
    private String port = "9000/";
    public String key = login.token;
    public String secret = login.secret;
    public SharedPreferences sharedPreferences ;
    public String matkul = recycleviewadapter1.matkul;
    public String dosen = recycleviewadapter1.dosen;
    ImageButton capture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_surat);
        sharedPreferences = getSharedPreferences(key, this.MODE_PRIVATE);
        final String token_id = sharedPreferences.getString(secret,"kosong");
        final String matakuliah = sharedPreferences.getString(matkul,"kosong");
        final String dosen1 = sharedPreferences.getString(dosen, "kosong");
        capture = findViewById(R.id.buttoncapture);
        capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.keterangan_kode==0){
                    sakit(token_id,matakuliah,dosen1);
                }
                else {
                    izin(token_id,matakuliah,dosen1);
                }
            }
        });
    }
    public void sakit(String id, String matakuliah, String dosen) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url+"9009/").addConverterFactory(GsonConverterFactory.create()).build();
        jsonplaceholder jsonplaceholder = retrofit.create(com.example.e_presys.jsonplaceholder.class);
        final postSakit postSakit = new postSakit(id, matakuliah, dosen);
        Call<postSakit>call = jsonplaceholder.postSakit(postSakit);
        call.enqueue(new Callback<com.example.e_presys.postSakit>() {
            @Override
            public void onResponse(Call<com.example.e_presys.postSakit> call, Response<com.example.e_presys.postSakit> response) {
                if (response.code()==603) {
                    postSakit postSakit1 = response.body();
                    Toast.makeText(getApplicationContext(),postSakit1.getMessage(),Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"proses gagal",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<com.example.e_presys.postSakit> call, Throwable t) {

            }
        });
    }
    public void izin(String id, String matakuliah, String dosen){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url+"9006/").addConverterFactory(GsonConverterFactory.create()).build();
        jsonplaceholder jsonplaceholder = retrofit.create(com.example.e_presys.jsonplaceholder.class);
        postIzin postIzin = new postIzin(id, matakuliah, dosen);
        Call<postIzin>call = jsonplaceholder.postIzin(postIzin);
        call.enqueue(new Callback<com.example.e_presys.postIzin>() {
            @Override
            public void onResponse(Call<com.example.e_presys.postIzin> call, Response<com.example.e_presys.postIzin> response) {
                if (response.code()==600) {
                    postIzin postIzin1 = response.body();
                    Toast.makeText(getApplicationContext(),postIzin1.getMessage(),Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(),"proses gagal",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<com.example.e_presys.postIzin> call, Throwable t) {

            }
        });
    }
}