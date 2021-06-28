package com.example.e_presys;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
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

public class OnlineMhs extends AppCompatActivity {
    public String url = MainActivity.url;
    private String port = "9001/";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    public ArrayList matakuliahlist = new ArrayList<>();
    public ArrayList dosen1list = new ArrayList<>();
    public ArrayList dosen2list = new ArrayList<>();
    public ArrayList dosen3list = new ArrayList<>();
    public ArrayList waktu_kuliahlist = new ArrayList<>();
    public ArrayList ruanganlist = new ArrayList<>();
    public String key = login.token;
    public String secret = login.secret;
    public String status = login.secret_status;
    public String stat;
    public SharedPreferences sharedPreferences,sharedPreference1 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_online_mhs);
        sharedPreferences = getSharedPreferences(key, this.MODE_PRIVATE);
        sharedPreference1 = getSharedPreferences(Pilihan.root,MODE_PRIVATE);
        String token_id = sharedPreferences.getString(secret,"kosong");
        stat = sharedPreferences.getString(status,"-1");
        if (stat.equals("0")){
            ambil_jadwal(token_id);
        }
        else{
            ambil_jadwal_dosen(token_id);
        }



    }
    void ambil_jadwal(String id) {
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(url+port).addConverterFactory(GsonConverterFactory.create()).build();
        final respon_get_schedule respon_get_schedule = new respon_get_schedule(id);
        jsonplaceholder js = retrofit.create(jsonplaceholder.class);
        Call<List<respon_get_schedule>> toCall = js.getschedule(respon_get_schedule);
        toCall.enqueue(new Callback<List<com.example.e_presys.respon_get_schedule>>() {
            @Override
            public void onResponse(Call<List<com.example.e_presys.respon_get_schedule>> call, Response<List<com.example.e_presys.respon_get_schedule>> response) {
                if(response.code()==200){
                    List<respon_get_schedule> hasil = response.body();
                    if(hasil.isEmpty()) {
                        Toast.makeText(getApplicationContext(),"Schedule empty",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        for(respon_get_schedule respon_get_schedule1:hasil) {
                            dosen1list.add(respon_get_schedule1.getDosen1());
                            dosen2list.add(respon_get_schedule1.getDosen2());
                            dosen3list.add(respon_get_schedule1.getDosen3());
                            matakuliahlist.add(respon_get_schedule1.getMatakuliah());
                            waktu_kuliahlist.add(respon_get_schedule1.getTime_start()+"-"+respon_get_schedule1.getTime_end());
                            ruanganlist.add(respon_get_schedule1.getRuangan());
                        }
                        show_data();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),String.valueOf(response.code()),Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<com.example.e_presys.respon_get_schedule>> call, Throwable t) {

            }
        });
    }

    void ambil_jadwal_dosen(String id){
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(url+port).addConverterFactory(GsonConverterFactory.create()).build();
        final respon_get_schedule_dosen respon_get_schedule_dosen = new respon_get_schedule_dosen(id);
        jsonplaceholder js = retrofit.create(jsonplaceholder.class);
        Call<List<com.example.e_presys.respon_get_schedule_dosen>> toCall = js.getscheduleDosen(respon_get_schedule_dosen);
        toCall.enqueue(new Callback<List<com.example.e_presys.respon_get_schedule_dosen>>() {
            @Override
            public void onResponse(Call<List<com.example.e_presys.respon_get_schedule_dosen>> call, Response<List<com.example.e_presys.respon_get_schedule_dosen>> response) {
                if(response.code()==200){
                    List<respon_get_schedule_dosen> hasil = response.body();
                    if(hasil.isEmpty()) {
                        Toast.makeText(getApplicationContext(),"Schedule empty",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        for(respon_get_schedule_dosen respon_get_schedule1:hasil) {
                            dosen1list.add(respon_get_schedule1.getKelas());
                            dosen2list.add(" ");
                            dosen3list.add(" ");
                            matakuliahlist.add(respon_get_schedule1.getMatakuliah());
                            waktu_kuliahlist.add(respon_get_schedule1.getTime_start()+"-"+respon_get_schedule1.getTime_end());
                            ruanganlist.add(respon_get_schedule1.getRuangan());
                        }
                        show_data();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),String.valueOf(response.code()),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<com.example.e_presys.respon_get_schedule_dosen>> call, Throwable t) {

            }
        });
    }


    void show_data() {
        recyclerView = findViewById(R.id.onlinemhs);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        stat = sharedPreferences.getString(status,"-1");
        String alur = sharedPreference1.getString(Pilihan.sub_root,"kosong");
        if (alur.equals("3")){
            adapter = new recycleviewadapter1(matakuliahlist,dosen1list,dosen2list,dosen3list,waktu_kuliahlist,stat,this);
        }
        else if(alur.equals("4")){
            adapter = new recyclerviewadapter3(matakuliahlist,dosen1list,dosen2list,dosen3list,waktu_kuliahlist,ruanganlist,stat,this);
        }
        else{
            adapter = new recycleviewadapter1(matakuliahlist,dosen1list,dosen2list,dosen3list,waktu_kuliahlist,stat,this);
        }
        recyclerView.setAdapter(adapter);
    }
}