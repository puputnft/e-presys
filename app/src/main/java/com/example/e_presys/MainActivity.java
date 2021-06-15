package com.example.e_presys;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.kishan.askpermission.AskPermission;
import com.kishan.askpermission.ErrorCallback;
import com.kishan.askpermission.PermissionCallback;
import com.kishan.askpermission.PermissionInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity implements PermissionCallback, ErrorCallback {
    public static String url = " http://156.67.221.101:";
    private String port = "9000/";
    public static int keterangan_kode;
    private static final int REQUEST_PERMISSIONS = 20;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        startActivity(new Intent(MainActivity.this,login.class));
        reqPermission();
        connect();
    }

    private void connect(){
        Retrofit retrofit = new Retrofit.Builder().baseUrl(url+port).addConverterFactory(GsonConverterFactory.create()).build();
        jsonplaceholder jsonplaceholder = retrofit.create(com.example.e_presys.jsonplaceholder.class);
        Call<getWelcome>call = jsonplaceholder.postWelcome();
        call.enqueue(new Callback<getWelcome>() {
            @Override
            public void onResponse(Call<getWelcome> call, Response<getWelcome> response) {
                getWelcome getWelcome = response.body();
                String m = getWelcome.getMessage();
                Toast.makeText(getApplicationContext(),m,Toast.LENGTH_SHORT).show();
                startActivity(new Intent(MainActivity.this,login.class));
                finish();
            }

            @Override
            public void onFailure(Call<getWelcome> call, Throwable t) {
                Toast.makeText(getApplicationContext(),"Lost Connection",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    private void reqPermission() {
        new AskPermission.Builder(this).setPermissions(
                Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION)
                .setCallback(this)
                .setErrorCallback(this)
                .request(REQUEST_PERMISSIONS);
    }
    @Override
    public void onShowRationalDialog(final PermissionInterface permissionInterface, int requestCode) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("We need permissions for this app.");
        builder.setPositiveButton("oke", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                permissionInterface.onDialogShown();
            }
        });
        builder.setNegativeButton("cancel", null);
        builder.show();
    }

    @Override
    public void onShowSettings(final PermissionInterface permissionInterface, int requestCode) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("We need permissions for this app. Open setting screen?");
        builder.setPositiveButton("oke", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                permissionInterface.onSettingsShown();
            }
        });
        builder.setNegativeButton("cancel", null);
        builder.show();
    }

    @Override
    public void onPermissionsGranted(int requestCode) {
        Toast.makeText(this, "Permissions Received.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPermissionsDenied(int requestCode) {
        Toast.makeText(this, "Permissions Denied.", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}