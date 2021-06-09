package com.example.e_presys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OnlineMhs extends AppCompatActivity {
    public String url = MainActivity.url;
    private String port = "9001/";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    public ArrayList matakuliahlist = new ArrayList<>();
    public ArrayList dosenlist = new ArrayList<>();
    public ArrayList waktu_kuliahlist = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_mhs);
    }
    void ambil_jadwal(String id) {
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(url+port).addConverterFactory(GsonConverterFactory.create()).build();
        final respon_get_schedule respon_get_schedule = new respon_get_schedule(id);
        jsonplaceholder js = retrofit.create(jsonplaceholder.class);
        Call<List<respon_get_schedule>> toCall = js.getschedule(respon_get_schedule);
        toCall.enqueue(new Callback<List<com.example.e_presys.respon_get_schedule>>() {
            @Override
            public void onResponse(Call<List<com.example.e_presys.respon_get_schedule>> call, Response<List<com.example.e_presys.respon_get_schedule>> response) {
                if(response.code()==207){
                    List<respon_get_schedule> hasil = response.body();
                    if(hasil.isEmpty()) {

                    }
                    else {
                        for(respon_get_schedule respon_get_schedule1:hasil) {
                            dosenlist.add(respon_get_schedule1.getDosen());
                            matakuliahlist.add(respon_get_schedule1.getMatakuliah());
                        }
                        show_data();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<com.example.e_presys.respon_get_schedule>> call, Throwable t) {

            }
        });
    }
    void show_data() {
        recyclerView = findViewById(R.id.onlinemhs);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        adapter = new recycleviewadapter1(matakuliahlist,dosenlist,waktu_kuliahlist,this);
        recyclerView.setAdapter(adapter);
    }
}