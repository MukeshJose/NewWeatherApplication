package com.example.newweatherapplication.model;

import com.google.gson.annotations.SerializedName;

public class RealFeelTemperature {

    @SerializedName("Metric")
    public Metric metric;
    @SerializedName("Imperial")
    public Imperial imperial;
}
