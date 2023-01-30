package com.example.newweatherapplication.model;

import com.google.gson.annotations.SerializedName;

public class Temperature {

    @SerializedName("Metric")
    public Metric metric;
    @SerializedName("Imperial")
    public Imperial imperial;
    @SerializedName("Value")
    public double value;
    @SerializedName("Unit")
    public String unit;
    @SerializedName("UnitType")
    public int unitType;

    @SerializedName("Minimum")
    public Minimum minimum;
    @SerializedName("Maximum")
    public Maximum maximum;
}

