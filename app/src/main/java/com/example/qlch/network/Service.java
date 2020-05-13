package com.example.qlch.network;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {
    @GET("http://172.19.200.197:8001/AppLogin.aspx")
    Call<LoginRes> login(@Query("taikhoan") String username, @Query("matkhau") String password);
}
