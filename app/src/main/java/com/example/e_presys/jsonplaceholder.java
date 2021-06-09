package com.example.e_presys;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface jsonplaceholder {
    @POST("/user/regist")
    Call<postRegis>postregist(@Body postRegis postRegis);
    @POST("/user/login")
    Call<postlogin>postlogin(@Body postlogin postlogin);
    @POST("/welcome")
    Call<getWelcome>postWelcome();
    @POST("/schedule")
    Call<List<respon_get_schedule>> getschedule (@Body respon_get_schedule get_schedule);

}
