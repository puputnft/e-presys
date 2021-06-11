package com.example.e_presys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;
import android.widget.Toast;

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
    public String matkul = recycleviewadapter1.matkul;
    public SharedPreferences sharedPreferences ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masuk_kelas);
        sharedPreferences = getSharedPreferences(key, this.MODE_PRIVATE);
        String token_id = sharedPreferences.getString(secret,"kosong");
        String matakuliah = sharedPreferences.getString(matkul,"kosong");
        link = findViewById(R.id.link);
        entry_kelas(token_id,matakuliah);

    }
    void entry_kelas (String id, String matakuliah){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url+port).addConverterFactory(GsonConverterFactory.create()).build();
        jsonplaceholder jsonplaceholder = retrofit.create(com.example.e_presys.jsonplaceholder.class);
        final postpresent postpresent = new postpresent(id,matakuliah);
        Call<postpresent>call = jsonplaceholder.getlink(postpresent);
        call.enqueue(new Callback<com.example.e_presys.postpresent>() {
            @Override
            public void onResponse(Call<com.example.e_presys.postpresent> call, Response<com.example.e_presys.postpresent> response) {
                if (response.code()==208 || response.code()==209 || response.code()==210 || response.code()==211 || response.code()==213 || response.code()==214){
                    postpresent postpresent1 =response.body();
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
            public void onFailure(Call<com.example.e_presys.postpresent> call, Throwable t) {

            }
        });


    }
}