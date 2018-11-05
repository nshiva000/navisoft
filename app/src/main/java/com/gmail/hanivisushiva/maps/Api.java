package com.gmail.hanivisushiva.maps;



import com.gmail.hanivisushiva.maps.Models.Data;
import com.gmail.hanivisushiva.maps.Models.Login.SignIn;
import com.gmail.hanivisushiva.maps.Models.Project.Project;
import com.gmail.hanivisushiva.maps.Models.dCompany.DCompany;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface Api {


    //@GET("plots.php")
    //Call<Data> get_all();
    @FormUrlEncoded
    @POST("allplots.php")
    Call<Data> get_all(
            @Field("pid") String id
    );

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

    @FormUrlEncoded
    @POST("project.php")
    Call<Project> get_projects(
            @Field("did") String did,
            @Field("pid") String pid
    );


    @FormUrlEncoded
    @POST("allprojects.php")
    Call<Project> get_all_projects(
            @Field("pid") String pid
    );



}
