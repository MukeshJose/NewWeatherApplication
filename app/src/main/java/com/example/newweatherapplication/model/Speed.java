package com.example.newweatherapplication.model;

import com.google.gson.annotations.SerializedName;

public class Speed {

    @SerializedName("Metric")
    public Metric metric;
    @SerializedName("Imperial")
    public Imperial imperial;
}
