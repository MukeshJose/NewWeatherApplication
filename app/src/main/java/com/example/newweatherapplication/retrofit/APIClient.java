package com.example.newweatherapplication.retrofit;

import com.example.newweatherapplication.Constants;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {

    private static Retrofit retrofit = null;
    private static Retrofit retrofitLocationSearch = null;
    private static Retrofit retrofitCurrentCondition = null;
    private static Retrofit retrofitFullDayForecast = null;
    private static Retrofit retrofitFiveDayForecast = null;

    public static Retrofit getClient() {
        retrofit = new Retrofit.Builder()
                .baseUrl(Constants.base_url_city_search)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit;
    }

    public static Retrofit getClientLocationSearch() {
        retrofitLocationSearch = new Retrofit.Builder()
                .baseUrl(Constants.base_url_Location_search)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofitLocationSearch;
    }

    public static Retrofit getClientCurrentCondition() {
        retrofitCurrentCondition = new Retrofit.Builder()
                .baseUrl(Constants.base_url_current_condition)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofitCurrentCondition;
    }

    public static Retrofit getClientFullDayForecast() {
        retrofitFullDayForecast = new Retrofit.Builder()
                .baseUrl(Constants.base_url_full_day_forecast)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofitFullDayForecast;
    }

    public static Retrofit getClientFiveDayForecast() {
        retrofitFiveDayForecast = new Retrofit.Builder()
                .baseUrl(Constants.base_url_five_day_forecast)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofitFiveDayForecast;
    }
}