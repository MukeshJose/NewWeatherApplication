package com.example.newweatherapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.Date;

public class DailyForecast {

    @SerializedName("Date")
    public Date date;
    @SerializedName("EpochDate")
    public int epochDate;
    @SerializedName("Temperature")
    public Temperature temperature;
    @SerializedName("Day")
    public Day day;
    @SerializedName("Night")
    public Night night;
    @SerializedName("Sources")
    public ArrayList<String> sources;
}
