package com.example.newweatherapplication.retrofit;

import android.widget.Toast;

import com.example.newweatherapplication.Constants;
import com.example.newweatherapplication.WeatherDetailsActivity;
import com.example.newweatherapplication.model.Root;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface APIInterface {

    @GET("search")
    Call<List<Root>> CALL_APICity_search(@Query("apikey") String api_key, @Query("q") String city_name);

    @GET("204287")
    Call<List<Root>> CALL_API_location_search(@Query("apikey") String api_key);

    @GET("{user}")
    Call<List<Root>> CALL_APICurrent_condition(@Path("user") String user, @Query("apikey") String api_key, @Query("details") Boolean details);

    @GET("{user}")
    Call<List<Root>> CALL_API_full_day_forecast(@Path("user") String user, @Query("apikey") String api_key, @Query("details") Boolean details);

    @GET("{user}")
    Call<Root> CALL_API_five_day_forecast(@Path("user") String user, @Query("apikey") String api_key, @Query("metric") Boolean metric);
}


