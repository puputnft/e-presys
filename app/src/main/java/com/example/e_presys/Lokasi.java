package com.example.e_presys;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Lokasi extends AppCompatActivity implements LocationListener {
    public String url = "http://192.168.1.4:";
    private String port = "9004/";
    LocationManager locationManager;
    FusedLocationProviderClient fusedLocationProviderClient;
    private static final int REQUEST_CODE = 101;
    Location location;
    public String slng;
    public String slat;
    public String key = login.token;
    public String secret = login.secret;
    public String mruangan = recyclerviewadapter3.offruangan;
    public SharedPreferences sharedPreferences;
    public String token_id,ruangan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lokasi);
        sharedPreferences = getSharedPreferences(key, this.MODE_PRIVATE);
        token_id = sharedPreferences.getString(secret,"kosong");
        ruangan = sharedPreferences.getString(mruangan,"kosong");
        locationManager = (LocationManager)this.getSystemService(Context.LOCATION_SERVICE);
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);



        fetchLastLocation();




    }


    @Override
    public void onLocationChanged(@NonNull Location location) {

    }

    public void fetchLastLocation() {
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String []{Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_CODE);
        }
        Task<Location> task = fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location!=null){
                    location = location;
//                    Toast.makeText(getApplicationContext(), location.getLatitude()+"\n"+location.getLongitude(), Toast.LENGTH_SHORT).show();
                    double lng = location.getLongitude();
                    double lat = location.getLatitude();
                    Lokasi.this.slng = String.valueOf(lng);
                    Lokasi.this.slat = String.valueOf(lat);
                    token_id = sharedPreferences.getString(secret,"kosong");
                    ruangan = sharedPreferences.getString(mruangan,"kosong");
                    cek_lokasi(token_id,String.valueOf(lat),String.valueOf(lng),ruangan);

                }
            }
        });
    }


    @SuppressLint("MissingSuperCall")
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case REQUEST_CODE :
                if(grantResults.length>0&&(grantResults[0]==PackageManager.PERMISSION_GRANTED)){
                    fetchLastLocation();
                }
                else {
                    Toast.makeText(getApplicationContext(),"silahkan enable permission gps apps ini",Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    void cek_lokasi(String id,String lat,String lng,String ruangan){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(MainActivity.url+port).addConverterFactory(GsonConverterFactory.create()).build();
        postLokasi postLokasi = new postLokasi(id, lat, lng, ruangan);
        jsonplaceholder js = retrofit.create(jsonplaceholder.class);
        Call<postLokasi> call = js.postlokasi(postLokasi);
        call.enqueue(new Callback<com.example.e_presys.postLokasi>() {
            @Override
            public void onResponse(Call<com.example.e_presys.postLokasi> call, Response<com.example.e_presys.postLokasi> response) {
                if(response.code()==203){
//                    Toast.makeText(getApplicationContext(),response.body().getMessage(),Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Lokasi.this,konfirmasi.class));
                    onBackPressed();
                }
                else{
                    Toast.makeText(getApplicationContext(),String.valueOf(response.code()),Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<com.example.e_presys.postLokasi> call, Throwable t) {

            }
        });
    }


    public void onBackPressed(){
        finish();
    }


}