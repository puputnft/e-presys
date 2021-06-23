package com.example.e_presys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class login extends AppCompatActivity {
    public String url = MainActivity.url;
    private String port = "9005/";
    EditText txtusername,txtpass;
    ImageButton login;
    TextView regist;
    public static final String token = "token" ;
    public static final String secret = "secret";
    public static final String secret_status = "secret_status";
    public SharedPreferences sharedpreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtusername = findViewById(R.id.txtusernamelogin);
        txtpass = findViewById(R.id.txtpasswordlogin);
        login = findViewById(R.id.buttonlogin);
        regist = findViewById(R.id.buttonsignup);
        sharedpreferences = getSharedPreferences(token, Context.MODE_PRIVATE);
        regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(com.example.e_presys.login.this,optionUser.class));

            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtusername.getText().toString().equals("")||txtpass.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(),"complete the form",Toast.LENGTH_SHORT).show();
                }
                else{
                    masuk(txtusername.getText().toString(),txtpass.getText().toString());
                }

            }
        });

    }

    public void masuk(String username,String password){
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(url+port).addConverterFactory(GsonConverterFactory.create()).build();
        final postlogin postlogin = new postlogin(username, password);
        jsonplaceholder jsonplaceholder = retrofit.create(com.example.e_presys.jsonplaceholder.class);
        Call<postlogin>call = jsonplaceholder.postlogin(postlogin);
        call.enqueue(new Callback<com.example.e_presys.postlogin>() {
            @Override
            public void onResponse(Call<com.example.e_presys.postlogin> call, Response<com.example.e_presys.postlogin> response) {
                if(response.code()==200){
                    postlogin postlogin1 = response.body();

                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.putString(secret, postlogin1.getMessage());
                    editor.putString(secret_status,postlogin1.getStatus());
                    editor.commit();
                    Intent x = new Intent(com.example.e_presys.login.this,Pilihan.class);
                    startActivity(x);
                    finish();

                }
                else{
                        Toast.makeText(getApplicationContext(),"unregistered account",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<com.example.e_presys.postlogin> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"lost connection",Toast.LENGTH_LONG).show();
            }
        });
    }
}