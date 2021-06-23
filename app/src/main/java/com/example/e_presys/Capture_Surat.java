package com.example.e_presys;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.security.acl.Permission;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Capture_Surat extends AppCompatActivity  {
    File photofile = null;
    public static int CAPTURE_IMAGE_REQUEST = 1;
    public String mCurrentPhotoPath;
    private static final String IMAGE_DIRECTORY_NAME = "e-presys";
    public static String url = MainActivity.url;
    private String port = "9000/";
    private String port_1 = "9010/";
    public String key = login.token;
    public String secret = login.secret;
    public SharedPreferences sharedPreferences ;
    public String matkul = recycleviewadapter1.matkul;
    public String dosen = recycleviewadapter1.mdosen1;
    public String dosen1;
    public String matakuliah;
    public String token_id;
    ImageButton capture;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_capture_surat);
        sharedPreferences = getSharedPreferences(key, this.MODE_PRIVATE);
        token_id = sharedPreferences.getString(secret,"kosong");
        matakuliah = sharedPreferences.getString(matkul,"kosong");
        dosen1 = sharedPreferences.getString(dosen, "kosong");
        capture = findViewById(R.id.buttoncapture);
        capture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.keterangan_kode==0){
                    sakit(token_id,matakuliah,dosen1);
                }
                else {
                    izin(token_id,matakuliah,dosen1);
                }
            }
        });
    }
    public void sakit(String id, String matakuliah, String dosen) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url+"9009/").addConverterFactory(GsonConverterFactory.create()).build();
        jsonplaceholder jsonplaceholder = retrofit.create(com.example.e_presys.jsonplaceholder.class);
        final postSakit postSakit = new postSakit(id, matakuliah, dosen);
        Call<postSakit>call = jsonplaceholder.postSakit(postSakit);
        call.enqueue(new Callback<com.example.e_presys.postSakit>() {
            @Override
            public void onResponse(Call<com.example.e_presys.postSakit> call, Response<com.example.e_presys.postSakit> response) {
                if (response.code()==200) {
                    postSakit postSakit1 = response.body();
                    Toast.makeText(getApplicationContext(),postSakit1.getMessage(),Toast.LENGTH_SHORT).show();
                    captureImage();
                }
                else {
                    Toast.makeText(getApplicationContext(),String.valueOf(response.code()),Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<com.example.e_presys.postSakit> call, Throwable t) {

            }
        });
    }
    public void izin(String id, String matakuliah, String dosen){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url+"9006/").addConverterFactory(GsonConverterFactory.create()).build();
        jsonplaceholder jsonplaceholder = retrofit.create(com.example.e_presys.jsonplaceholder.class);
        postIzin postIzin = new postIzin(id, matakuliah, dosen);
        Call<postIzin>call = jsonplaceholder.postIzin(postIzin);
        call.enqueue(new Callback<com.example.e_presys.postIzin>() {
            @Override
            public void onResponse(Call<com.example.e_presys.postIzin> call, Response<com.example.e_presys.postIzin> response) {
                if (response.code()==200) {
                    postIzin postIzin1 = response.body();
                    Toast.makeText(getApplicationContext(),postIzin1.getMessage(),Toast.LENGTH_SHORT).show();
                    captureImage();
                }
                else {
                    Toast.makeText(getApplicationContext(),"proses gagal",Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<com.example.e_presys.postIzin> call, Throwable t) {

            }
        });
    }
    private void captureImage(){
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE},0);
        }
        else{
            Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            if(takePictureIntent.resolveActivity(getPackageManager())!=null){
                try{
                    photofile = createImageFile();
                    displayMessage(getBaseContext(),photofile.getAbsolutePath());
                    Log.i("Mayank",photofile.getAbsolutePath());

                    if(photofile!=null){
                        Uri photoURI = FileProvider.getUriForFile(this,"com.example.e_presys.fileprovider",photofile);
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT,photoURI);
                        startActivityForResult(takePictureIntent,CAPTURE_IMAGE_REQUEST);
                    }
                }catch (Exception e){
                    displayMessage(getBaseContext(),e.getMessage().toString());
                }
            }
            else{
                displayMessage(getBaseContext(),"Null");
            }

        }
    }
    private File createImageFile() throws IOException {
        String set = null;
        if(MainActivity.keterangan_kode==0){
            set = "Sakit";
        }
        else {
            set = "Izin";
        }
        String imageFileName = set+"_"+token_id+"_"+matakuliah+"_"+dosen1+"_";
        File storageDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),IMAGE_DIRECTORY_NAME);
        storageDir.mkdir();
        File image = File.createTempFile(imageFileName,".png",storageDir);
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

    private void displayMessage(Context context, String message){
        Toast.makeText(context,message, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==CAPTURE_IMAGE_REQUEST && resultCode==RESULT_OK){
            uploadimage();
        }
        else{
            displayMessage(getBaseContext(),"Request cancelled or something went wrong");
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 0) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                captureImage();
            }
        }
    }
    void uploadimage(){
        RequestBody reqfile = RequestBody.create(MediaType.parse("multipart/form-data"),photofile);
        MultipartBody.Part body = MultipartBody.Part.createFormData("file",photofile.getName(),reqfile);
        Retrofit retro = new Retrofit.Builder().baseUrl(url+port_1).addConverterFactory(GsonConverterFactory.create()).build();
        jsonplaceholder jsonplaceholder = retro.create(com.example.e_presys.jsonplaceholder.class);
        Call<ResponseBody>call = jsonplaceholder.uploadimage(body);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                displayMessage(getBaseContext(),"berhasil");
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}