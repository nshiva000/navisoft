package com.gmail.hanivisushiva.maps;


import com.gmail.hanivisushiva.maps.Models.Data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface Api {


    @GET("plots.php")
    Call<Data> get_all();



}
