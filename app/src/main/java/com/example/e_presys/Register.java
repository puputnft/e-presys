package com.example.e_presys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Register extends AppCompatActivity {
    EditText nim,username,kelas,email,password,confirm_password;
    Spinner jurusan,prodi;
    ArrayList jurusanlist = new ArrayList<>();
    ArrayList prodilist = new ArrayList<>();
    public String jurusanselected,prodiselected;
    ImageButton signup;
    public String url = MainActivity.url;
    private String port = "9002/";
    public String status = "";
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
        status = code;
        jurusanlist.add("Jurusan");
        jurusanlist.add("ELEKTRO");
        prodilist.add("Prodi");
        prodilist.add("D4-Teknik Telekomunikasi");
        prodilist.add("D3-Teknik Telekomunikasi");
        jurusanselected="";
        prodiselected="";
        if (code.equals("0")){

        }
        else{
            nim.setHint("NIP");
            kelas.setHint("Kode Dosen");
            prodilist.add("D4-Teknik Telekomunikasi & D3-Teknik Telekomunikasi");
        }

        ArrayAdapter<String> jurusanadapter = new ArrayAdapter<>(this,R.layout.custom_spinner,jurusanlist);
        jurusanadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        jurusan.setAdapter(jurusanadapter);

        jurusan.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String m = jurusan.getSelectedItem().toString();
                if(!m.equals("Jurusan")){
                    jurusanselected = m;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<String> prodiadapter = new ArrayAdapter<>(this,R.layout.custom_spinner,prodilist);
        prodiadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        prodi.setAdapter(prodiadapter);

        prodi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String m = prodi.getSelectedItem().toString();
                if(!m.equals("Prodi")){
                    prodiselected = m;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });





        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nim.getText().toString().equals("")||username.getText().toString().equals("")||kelas.getText().toString().equals("")||email.getText().toString().equals("")||password.getText().toString().equals("")||confirm_password.getText().toString().equals("")||status.equals("")||jurusanselected.equals("")||prodiselected.equals("")) {
                    Toast.makeText(getApplicationContext(),"kosong",Toast.LENGTH_LONG).show();
                }
                else {
                    if(password.getText().toString().equals(confirm_password.getText().toString())){
                        register(nim.getText().toString(),username.getText().toString(),jurusanselected.toString(),prodiselected.toString(),kelas.getText().toString(),email.getText().toString(),password.getText().toString(),status);
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"password salah",Toast.LENGTH_LONG).show();
                    }
                }

            }
        });

    }
    public void register(String nim,String username,String jurusan,String prodi,String kelas,String email,String pass,String status){
        final Retrofit retrofit = new Retrofit.Builder().baseUrl(url+port).addConverterFactory(GsonConverterFactory.create()).build();
        postRegis postRegis = new postRegis(nim, username, jurusan, prodi, kelas, email, pass,status);
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