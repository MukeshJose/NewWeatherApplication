package com.example.newweatherapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class Headline {

    @SerializedName("EffectiveDate")
    public Date effectiveDate;
    @SerializedName("EffectiveEpochDate")
    public int effectiveEpochDate;
    @SerializedName("Severity")
    public int severity;
    @SerializedName("Text")
    public String text;
    @SerializedName("Category")
    public String category;
    @SerializedName("EndDate")
    public Object endDate;
    @SerializedName("EndEpochDate")
    public Object endEpochDate;
}
