package com.example.e_presys;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface jsonplaceholder {
    @POST("/user/regist")
    Call<postRegis>postregist(@Body postRegis postRegis);
    @POST("/user/login")
    Call<postlogin>postlogin(@Body postlogin postlogin);
    @POST("/welcome")
    Call<getWelcome>postWelcome();
    @POST("/schedule")
    Call<List<respon_get_schedule>> getschedule (@Body respon_get_schedule get_schedule);
    @POST("/schedule")
    Call<List<respon_get_schedule_dosen>>getscheduleDosen(@Body respon_get_schedule_dosen respon_get_schedule_dosen);
    @POST("/user/present")
    Call<postpresent>getlink(@Body postpresent postpresent);
    @POST("/user/present")
    Call<postPresentDosen>getlinkdosen(@Body postPresentDosen postPresentDosen);
    @POST("/user/izin")
    Call<postIzin>postIzin(@Body postIzin postIzin);
    @POST("/user/izin")
    Call<postIzinDosen>postizindosen(@Body postIzinDosen postIzinDosen);
    @POST("/user/sakit")
    Call<postSakit>postSakit(@Body postSakit postSakit);
    @POST("/user/sakit")
    Call<postSakitDosen>postsakitdosen(@Body postSakitDosen postSakitDosen);
    @POST("user/history")
    Call<List<getHistory>>gethistory(@Body getHistory getHistory);
    @POST("user/history")
    Call<List<getHistoryDosen>>gethistorydosen(@Body getHistoryDosen getHistoryDosen);
    @Multipart
    @POST("/uploadfile")
    Call<ResponseBody>uploadimage(@Part MultipartBody.Part file);
    @POST("lokasi")
    Call<postLokasi>postlokasi(@Body postLokasi postLokasi);


}
