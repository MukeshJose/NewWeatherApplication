package com.example.newweatherapplication.model;

import com.google.gson.annotations.SerializedName;

public class Day {

    @SerializedName("Icon")
    public int icon;
    @SerializedName("IconPhrase")
    public String iconPhrase;
    @SerializedName("HasPrecipitation")
    public boolean hasPrecipitation;
}
