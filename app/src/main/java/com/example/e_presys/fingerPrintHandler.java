package com.example.e_presys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.CancellationSignal;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.content.Context.MODE_PRIVATE;

@RequiresApi(api = Build.VERSION_CODES.M)
public class fingerPrintHandler extends FingerprintManager.AuthenticationCallback {
    private Context context;
    public String key = login.token;
    public String secret = login.secret;
    public String status = login.secret_status;
    public String stat;
    public SharedPreferences sharedPreferences,sharedPreference1;
    public String url = MainActivity.url;
    private String port = "9001/";

    public fingerPrintHandler(Context context){
        this.context = context;
    }

    public void startAuth(FingerprintManager fingerprintManager,FingerprintManager.CryptoObject cryptoObject){
        CancellationSignal cancellationSignal = new CancellationSignal();
        fingerprintManager.authenticate(cryptoObject,cancellationSignal,0,this,null);
    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {
        super.onAuthenticationError(errorCode, errString);
        this.notif("Terdapat error authentikasi : "+errString);
        launch2();
    }
    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
        super.onAuthenticationHelp(helpCode, helpString);
        this.notif("Error : "+helpString);
        launch2();
    }
    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        super.onAuthenticationSucceeded(result);
        this.notif("Autentikasi sukses");
        sharedPreferences = ((Activity)context).getSharedPreferences(key, ((Activity)context).MODE_PRIVATE);
        String token_id = sharedPreferences.getString(secret,"kosong");
        ambil_jadwal(token_id);

    }

    @Override
    public void onAuthenticationFailed() {
        super.onAuthenticationFailed();
        this.notif("Autentikasi gagal");
    }
    private void notif(String s){
        TextView label = (TextView)((Activity)context).findViewById(R.id.notif_finger_print_auth);
        label.setText(s);
    }

    private void finish(){
        this.finish();
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
                    sharedPreference1 = ((Activity)context).getSharedPreferences(Pilihan.root,MODE_PRIVATE);
                    String alur = sharedPreference1.getString(Pilihan.sub_root,"kosong");
                    if(alur.equals("0")||alur.equals("1")){
                        launch_to_capture_surat();
                    }
                    else if(alur.equals("4")){
                        notif("Welcome to class");
                    }
                    else if(alur.equals("3")){
                        launch_to_class();
                    }
                    else{
                        notif(alur);
                    }

                }
                else{
                    notif(String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<List<com.example.e_presys.respon_get_schedule>> call, Throwable t) {

            }
        });

    }

    void launch_to_class(){
        this.context.startActivity(new Intent(this.context,masuk_kelas.class));
        ((fingerPrint)context).finish();
    }

    void launch2(){
        Intent i = new Intent(this.context,konfirmasi.class);
        this.context.startActivity(i);
        ((fingerPrint)context).finish();
    }

    void launch_to_capture_surat(){
        this.context.startActivity(new Intent(this.context,Capture_Surat.class));
        ((fingerPrint)context).finish();
    }
}
