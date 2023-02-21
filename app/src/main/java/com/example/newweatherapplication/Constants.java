package com.example.newweatherapplication;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newweatherapplication.model.Root;
import com.example.newweatherapplication.retrofit.APIInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Constants {


    public static String api_key = "GPknHj30rZAlWv8akYKrlJm41dGyQr4a";
    public static String base_url_city_search = "http://dataservice.accuweather.com/locations/v1/cities/";
    public static String base_url_Location_search = "http://dataservice.accuweather.com/locations/v1/";
    public static String base_url_current_condition = "http://dataservice.accuweather.com/currentconditions/v1/";
    public static String base_url_full_day_forecast = "http://dataservice.accuweather.com/forecasts/v1/hourly/12hour/";
    public static String base_url_five_day_forecast = "http://dataservice.accuweather.com/forecasts/v1/daily/5day/";


}
