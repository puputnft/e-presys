package com.example.e_presys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class masuk_kelas extends AppCompatActivity {
    public String url = MainActivity.url;
    private String port = "9007/";
    TextView link;
    public String key = login.token;
    public String secret = login.secret;
    public String status = login.secret_status;
    public String matkul = recycleviewadapter1.matkul;
    public String kelas = recycleviewadapter1.kelas_ngajar;
    public SharedPreferences sharedPreferences,sharedPreferences1 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masuk_kelas);
        sharedPreferences = getSharedPreferences(key, this.MODE_PRIVATE);
        sharedPreferences1 = getSharedPreferences(Pilihan.root,MODE_PRIVATE);
        String token_id = sharedPreferences.getString(secret,"kosong");
        String matakuliah = sharedPreferences.getString(matkul,"kosong");
        String stat = sharedPreferences.getString(status,"kosong");
        String cl = sharedPreferences.getString(kelas,"kosong");
        link = findViewById(R.id.link);
        if(stat.equals("0")){
            entry_kelas(token_id,matakuliah);
        }
        else{
            entry_kelas_dosen(token_id,matakuliah,cl);
        }


    }
    void entry_kelas (String id, String matakuliah){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url+port).addConverterFactory(GsonConverterFactory.create()).build();
        jsonplaceholder jsonplaceholder = retrofit.create(com.example.e_presys.jsonplaceholder.class);
        final postpresent postpresent = new postpresent(id,matakuliah);
        Call<postpresent>call = jsonplaceholder.getlink(postpresent);
        call.enqueue(new Callback<com.example.e_presys.postpresent>() {
            @Override
            public void onResponse(Call<com.example.e_presys.postpresent> call, Response<com.example.e_presys.postpresent> response) {
                if (response.code()==200){

                    postpresent postpresent1 =response.body();
                    String l = postpresent1.getLink();
                    String alur = sharedPreferences.getString(Pilihan.sub_root,"kosong");
                    if(alur.equals("4")){
                        link.setText("Welcome to the class");
                    }
                    else{
                        Toast.makeText(getApplicationContext(),l,Toast.LENGTH_SHORT).show();
                        link.setText(Html.fromHtml("<a href=\""+l+"\">link_kelas</a>"));
                        link.setMovementMethod(LinkMovementMethod.getInstance());
                    }

                }
                else{
                    Toast.makeText(getApplicationContext(),"masuk kelas ditolak" +
                            " "+response.code(),Toast.LENGTH_SHORT).show();
                    finish();
                }
            }

            @Override
            public void onFailure(Call<com.example.e_presys.postpresent> call, Throwable t) {

            }
        });
    }

    void entry_kelas_dosen(String id,String matakuliah,String kelas){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url+port).addConverterFactory(GsonConverterFactory.create()).build();
        jsonplaceholder jsonplaceholder = retrofit.create(com.example.e_presys.jsonplaceholder.class);
        postPresentDosen postPresentDosen = new postPresentDosen(id, matakuliah, kelas);
        Call<postPresentDosen>call = jsonplaceholder.getlinkdosen(postPresentDosen);
        call.enqueue(new Callback<com.example.e_presys.postPresentDosen>() {
            @Override
            public void onResponse(Call<com.example.e_presys.postPresentDosen> call, Response<com.example.e_presys.postPresentDosen> response) {
                if (response.code()==200){
                    postPresentDosen postpresent1 =response.body();
                    String l = postpresent1.getLink();
                    Toast.makeText(getApplicationContext(),l,Toast.LENGTH_SHORT).show();
                    link.setText(Html.fromHtml("<a href=\""+l+"\">link_kelas</a>"));
                    link.setMovementMethod(LinkMovementMethod.getInstance());
                }
                else{
                    Toast.makeText(getApplicationContext(),"Ga bisa masuk kelas lur"+response.code(),Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(masuk_kelas.this,Pilihan.class));
                    finish();
                }
            }

            @Override
            public void onFailure(Call<com.example.e_presys.postPresentDosen> call, Throwable t) {

            }
        });

    }
}