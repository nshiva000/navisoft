package com.gmail.hanivisushiva.maps;



import com.gmail.hanivisushiva.maps.Models.Data;
import com.gmail.hanivisushiva.maps.Models.Login.SignIn;
import com.gmail.hanivisushiva.maps.Models.dCompany.DCompany;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface Api {


    @GET("plots.php")
    Call<Data> get_all();

    @FormUrlEncoded
    @POST("login.php")
    Call<SignIn> user_login(
            @Field("email") String email,
            @Field("password") String password
    );



    @FormUrlEncoded
    @POST("dcompany.php")
    Call<DCompany> get_Child(
            @Field("id") String id
    );



}
