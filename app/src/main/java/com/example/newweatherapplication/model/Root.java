package com.example.newweatherapplication.model;


import com.google.gson.annotations.SerializedName;


import java.util.ArrayList;
import java.util.Date;

public class Root {


    @SerializedName("Version")
    public int version;
    @SerializedName("Key")
    public String key;
    @SerializedName("Type")
    public String type;
    @SerializedName("Rank")
    public int rank;
    @SerializedName("LocalizedName")
    public String localizedName;
    @SerializedName("EnglishName")
    public String englishName;
    @SerializedName("PrimaryPostalCode")
    public String primaryPostalCode;
    @SerializedName("Region")
    public Region region;
    @SerializedName("Country")
    public Country country;
    @SerializedName("AdministrativeArea")
    public AdministrativeArea administrativeArea;
    @SerializedName("TimeZone")
    public TimeZone timeZone;
    @SerializedName("GeoPosition")
    public GeoPosition geoPosition;
    @SerializedName("IsAlias")
    public boolean isAlias;
    @SerializedName("SupplementalAdminAreas")
    public ArrayList<SupplementalAdminArea> supplementalAdminAreas;
    @SerializedName("DataSets")
    public ArrayList<String> dataSets;
    @SerializedName("Pressure")
    public Pressure pressure;



    @SerializedName("LocalObservationDateTime")
    public Date localObservationDateTime;
    @SerializedName("EpochTime")
    public int epochTime;
    @SerializedName("WeatherText")
    public String weatherText;
    @SerializedName("WeatherIcon")
    public int weatherIcon;
    @SerializedName("HasPrecipitation")
    public boolean hasPrecipitation;
    @SerializedName("PrecipitationType")
    public Object precipitationType;
    @SerializedName("IsDayTime")
    public boolean isDayTime;
    @SerializedName("Temperature")
    public Temperature temperature;
    @SerializedName("MobileLink")
    public String mobileLink;
    @SerializedName("Link")
    public String link;

    @SerializedName("DateTime")
    public Date dateTime;
    @SerializedName("EpochDateTime")
    public int epochDateTime;
    @SerializedName("IconPhrase")
    public String iconPhrase;
    @SerializedName("IsDaylight")
    public boolean isDaylight;
    @SerializedName("RealFeelTemperature")
    public RealFeelTemperature realFeelTemperature;
    @SerializedName("RealFeelTemperatureShade")
    public RealFeelTemperatureShade realFeelTemperatureShade;
    @SerializedName("WetBulbTemperature")
    public WetBulbTemperature wetBulbTemperature;
    @SerializedName("DewPoint")
    public DewPoint dewPoint;
    @SerializedName("Wind")
    public Wind wind;
    @SerializedName("WindGust")
    public WindGust windGust;
    @SerializedName("RelativeHumidity")
    public int relativeHumidity;
    @SerializedName("IndoorRelativeHumidity")
    public int indoorRelativeHumidity;
    @SerializedName("Visibility")
    public Visibility visibility;

    @SerializedName("UVIndex")
    public int uVIndex;
    @SerializedName("UVIndexText")
    public String uVIndexText;
    @SerializedName("PrecipitationProbability")
    public int precipitationProbability;
    @SerializedName("ThunderstormProbability")
    public int thunderstormProbability;
    @SerializedName("RainProbability")
    public int rainProbability;
    @SerializedName("SnowProbability")
    public int snowProbability;
    @SerializedName("IceProbability")
    public int iceProbability;

    @SerializedName("Headline")
    public Headline headline;
    @SerializedName("DailyForecasts")
    public ArrayList<DailyForecast> dailyForecasts;
}