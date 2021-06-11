package com.example.e_presys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static String url = " http://156.67.221.101:";
    private String port = "9000/";
    public static int keterangan_kode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        startActivity(new Intent(MainActivity.this,login.class));
        connect();
    }

    private void connect(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url+port).addConverterFactory(GsonConverterFactory.create()).build();
        jsonplaceholder jsonplaceholder = retrofit.create(com.example.e_presys.jsonplaceholder.class);
        Call<getWelcome>call = jsonplaceholder.postWelcome();
        call.enqueue(new Callback<getWelcome>() {
            @Override
            public void onResponse(Call<getWelcome> call, Response<getWelcome> response) {
                getWelcome getWelcome = response.body();
                String m = getWelcome.getMessage();
                Toast.makeText(getApplicationContext(),m,Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,login.class));
                finish();
            }

            @Override
            public void onFailure(Call<getWelcome> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Lost Connection",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}