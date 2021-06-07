package com.example.e_presys;

import androidx.appcompat.app.AppCompatActivity;

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

public class Register extends AppCompatActivity {
    EditText nim,username,jurusan,prodi,kelas,email,password,confirm_password;
    ImageButton signup;
    public String url = MainActivity.url;
    private String port = "9002/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nim = findViewById(R.id.txtnimregister);
        username = findViewById(R.id.txtusernameregister);
        jurusan = findViewById(R.id.txtjurusanregister);
        prodi = findViewById(R.id.txtprodiregister);
        kelas = findViewById(R.id.txtkelasregister);
        email = findViewById(R.id.txtemailregister);
        password = findViewById(R.id.txtpasswordregister);
        confirm_password = findViewById(R.id.txtconfirmpasswordregister);
        signup = findViewById(R.id.btnsignupregister);
        String code = getIntent().getStringExtra("code").toString();
        if (code.equals("0")){

        }
        else{
            nim.setHint("NIP");
            kelas.setHint("Kode Dosen");
        }
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nim.getText().equals("")||username.getText().equals("")||jurusan.getText().equals("")||prodi.getText().equals("")||kelas.getText().equals("")||email.getText().equals("")||password.getText().equals("")||confirm_password.getText().equals("")) {
                    Toast.makeText(getApplicationContext(),"kosong",Toast.LENGTH_LONG).show();
                }

                else {
                    if(password.getText().toString().equals(confirm_password.getText().toString())){
                        register(nim.getText().toString(),username.getText().toString(),jurusan.getText().toString(),prodi.getText().toString(),kelas.getText().toString(),email.getText().toString(),password.getText().toString());
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"password salah",Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

    }
    public void register(String nim,String username,String jurusan,String prodi,String kelas,String email,String pass){
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(url+port).addConverterFactory(GsonConverterFactory.create()).build();
        postRegis postRegis = new postRegis(nim, username, jurusan, prodi, kelas, email, pass);
        jsonplaceholder jsonplaceholder = retrofit.create(com.example.e_presys.jsonplaceholder.class);
        Call<postRegis>call = jsonplaceholder.postregist(postRegis);
        call.enqueue(new Callback<com.example.e_presys.postRegis>() {
            @Override
            public void onResponse(Call<com.example.e_presys.postRegis> call, Response<com.example.e_presys.postRegis> response) {
                if(response.code()==203){
                    Toast.makeText(getApplicationContext(),"input berhasil",Toast.LENGTH_LONG).show();

                }
                else{
                    Toast.makeText(getApplicationContext(),"sudah terdaftar",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<com.example.e_presys.postRegis> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"lost connection",Toast.LENGTH_LONG).show();
            }
        });
    }

}