package com.example.newweatherapplication.model;

import com.google.gson.annotations.SerializedName;

public class Metric {
     @SerializedName("Value")
    public double value;
     @SerializedName("Unit")
    public String unit;
     @SerializedName("UnitType")
    public int unitType;
}
