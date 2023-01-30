package com.example.newweatherapplication.model;

import com.google.gson.annotations.SerializedName;

public class Country {

    @SerializedName("ID")
    public String iD;
    @SerializedName("LocalizedName")
    public String localizedName;
    @SerializedName("EnglishName")
    public String englishName;
}
