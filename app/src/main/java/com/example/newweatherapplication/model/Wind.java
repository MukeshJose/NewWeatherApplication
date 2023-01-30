package com.example.newweatherapplication.model;

import com.google.gson.annotations.SerializedName;

public class Wind {
    @SerializedName("Direction")
    public Direction direction;
    @SerializedName("Speed")
    public Speed speed;

}
