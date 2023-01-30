package com.example.newweatherapplication.model;

import com.google.gson.annotations.SerializedName;

public class Direction {
    @SerializedName("Degrees")
    public int degrees;
    @SerializedName("Localized")
    public String localized;
    @SerializedName("English")
    public String english;
}
