package com.example.e_presys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class history extends AppCompatActivity {
    public String url = MainActivity.url;
    private String port = "9008/";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    public ArrayList daydatelist = new ArrayList();
    public ArrayList matakuliahlist = new ArrayList<>();
    public ArrayList dosenlist = new ArrayList<>();
    public ArrayList waktu_kehadiranlist = new ArrayList<>();
    public ArrayList keteranganlist = new ArrayList<>();
    public String key = login.token;
    public String secret = login.secret;
    public String stat = login.secret_status;
    public SharedPreferences sharedPreferences ;
    public String status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        sharedPreferences = getSharedPreferences(key, this.MODE_PRIVATE);
        String token_id = sharedPreferences.getString(secret,"kosong");
        status = sharedPreferences.getString(stat,"-1");

        if(status.equals("0")){
            ambil_history(token_id);
        }
        else{
            ambil_history_dosen(token_id);
        }


    }


    public void ambil_history(String id) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url+port).addConverterFactory(GsonConverterFactory.create()).build();
        jsonplaceholder jsonplaceholder = retrofit.create(com.example.e_presys.jsonplaceholder.class);
        final getHistory getHistory1 = new getHistory(id);
        Call<List<getHistory>>call = jsonplaceholder.gethistory(getHistory1);
        call.enqueue(new Callback<List<getHistory>>() {
            @Override
            public void onResponse(Call<List<getHistory>> call, Response<List<getHistory>> response) {
                if(response.code()==200){
                    List<getHistory> hasil = response.body();
                    if(hasil.isEmpty()){
                        Toast.makeText(getApplicationContext(),"Data kosong",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        for(getHistory getHistory:hasil){
                            daydatelist.add(getHistory.getDay()+","+getHistory.getDate());
                            matakuliahlist.add(getHistory.getMatakuliah());
                            dosenlist.add(getHistory.getDosen1()+","+getHistory.getDosen2()+","+getHistory.getDosen3());
                            waktu_kehadiranlist.add(getHistory.getTime());
                            keteranganlist.add(getHistory.getInfo());
                            Toast.makeText(getApplicationContext(),getHistory.getDate(),Toast.LENGTH_SHORT).show();
                        }

                        show_data();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<getHistory>> call, Throwable t) {

            }
        });
    }

    void ambil_history_dosen(String id){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url+port).addConverterFactory(GsonConverterFactory.create()).build();
        jsonplaceholder jsonplaceholder = retrofit.create(com.example.e_presys.jsonplaceholder.class);
        final getHistoryDosen getHistory1 = new getHistoryDosen(id);
        Call<List<getHistoryDosen>>call = jsonplaceholder.gethistorydosen(getHistory1);
        call.enqueue(new Callback<List<getHistoryDosen>>() {
            @Override
            public void onResponse(Call<List<getHistoryDosen>> call, Response<List<getHistoryDosen>> response) {
                if(response.code()==200){
                    List<getHistoryDosen> hasil = response.body();
                    if(hasil.isEmpty()){
                        Toast.makeText(getApplicationContext(),"Data kosong",Toast.LENGTH_SHORT).show();
                    }
                    else{
                        for(getHistoryDosen getHistory:hasil){
                            daydatelist.add(getHistory.getDay()+","+getHistory.getDate());
                            matakuliahlist.add(getHistory.getMatakuliah());
                            dosenlist.add(getHistory.getKelas());
                            waktu_kehadiranlist.add(getHistory.getTime());
                            keteranganlist.add(getHistory.getInfo());
                            Toast.makeText(getApplicationContext(),getHistory.getDate(),Toast.LENGTH_SHORT).show();
                        }

                        show_data();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<getHistoryDosen>> call, Throwable t) {

            }
        });
    }

    void show_data() {
        recyclerView = findViewById(R.id.history);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        status = sharedPreferences.getString(stat,"-1");
        adapter = new recyclerviewadapter2(daydatelist, matakuliahlist, dosenlist, waktu_kehadiranlist, keteranganlist, status,this);
        recyclerView.setAdapter(adapter);
    }
}