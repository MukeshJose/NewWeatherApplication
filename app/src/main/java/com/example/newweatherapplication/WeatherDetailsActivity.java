package com.example.newweatherapplication;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newweatherapplication.Adapter.ForecastAdapter;
import com.example.newweatherapplication.model.Root;
import com.example.newweatherapplication.retrofit.APIClient;
import com.example.newweatherapplication.retrofit.APIInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WeatherDetailsActivity extends AppCompatActivity {

    private RelativeLayout rlSectionView;
    private TextView tvCityName;
    private TextView tvTemperature;
    private ImageView ivWeatherIcon;
    private TextView tvWeatherText;
    String location_key = null;
    private RecyclerView rvForecastView;
    private ImageView ivTemperatureUnit;
    private ImageView ivWindIcon;
    private TextView tvWindText;
    private TextView tvWindValue;
    private ImageView ivPressureIcon;
    private TextView tvPressureText;
    private TextView tvPressureValue;
    private ImageView ivHumidityIcon;
    private TextView tvHumidityText;
    private TextView tvHumidityValue;
    private TextView tvDateTime;
    private TextView tvFiveDayForecastButton;
    private TextView tvTempFelt;
    private TextView tvVisibility;
    private TextView tvUv;
    private TextView tvWindDirection;
    private TextView tvWindDegree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_weather_details_screen);
        initView();

        APIInterface api = APIClient.getClient().create(APIInterface.class);
        APIInterface apiLocationSearch = APIClient.getClientLocationSearch().create(APIInterface.class);
        APIInterface apiCurrentClimate = APIClient.getClientCurrentCondition().create(APIInterface.class);
        APIInterface apiForecast = APIClient.getClientFullDayForecast().create(APIInterface.class);

        getLocationKey(api, apiCurrentClimate, apiForecast);

        tvFiveDayForecastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), FiveDayForecast.class));
            }
        });


    }


    private void getLocationKey(APIInterface api, APIInterface apiCurrentClimate, APIInterface apiForecast) {

        api.CALL_APICity_search(Constants.api_key, getIntent().getStringExtra("city_name")).enqueue(new Callback<List<Root>>() {
            @Override
            public void onResponse(Call<List<Root>> call, Response<List<Root>> response) {
                if (response.isSuccessful()) {
                    List<Root> root = response.body();
                    location_key = root.get(0).key;
                    tvCityName.setText(root.get(0).englishName);

                    FiveDayForecast fiveDayForecast = new FiveDayForecast(location_key);


                    getCurrentWeather(apiCurrentClimate);
                    getForecast(apiForecast);


                } else {
                    Toast.makeText(WeatherDetailsActivity.this, "failed", Toast.LENGTH_SHORT).show();
                }
            }

            private void getForecast(APIInterface apiForecast) {

                apiForecast.CALL_API_full_day_forecast(location_key, Constants.api_key, true).enqueue(new Callback<List<Root>>() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onResponse(Call<List<Root>> call, Response<List<Root>> response) {

                        if (response.isSuccessful()) {
                            List<Root> root = response.body();


                            LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false);
                            rvForecastView.setLayoutManager(layoutManager);
                            ForecastAdapter forecastAdapter = new ForecastAdapter(getApplicationContext(), root);
                            rvForecastView.setAdapter(forecastAdapter);
                        } else {
                            Toast.makeText(WeatherDetailsActivity.this, "forecast call failed", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<List<Root>> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Root>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getCurrentWeather(APIInterface apiCurrentClimate) {
        apiCurrentClimate.CALL_APICurrent_condition(location_key, Constants.api_key, true).enqueue(new Callback<List<Root>>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<List<Root>> call, Response<List<Root>> response) {
                if (response.isSuccessful()) {
                    List<Root> root = response.body();

                    String dateTime = String.valueOf(root.get(0).localObservationDateTime).substring(0, 10);
                    tvDateTime.setText(dateTime);

                    int temp_celsius = (int) root.get(0).temperature.metric.value;

                    tvTemperature.setText(String.valueOf(temp_celsius));
                    Glide.with(getApplicationContext()).load(R.drawable.ic_degree_celsius).into(ivTemperatureUnit);
                    String icon_url = null;
                    int weatherIconNumber = root.get(0).weatherIcon;
                    if (weatherIconNumber <= 9) {
                        icon_url = "https://developer.accuweather.com/sites/default/files/0";
                    } else if (weatherIconNumber > 9) {
                        icon_url = "https://developer.accuweather.com/sites/default/files/";
                    }

                    Glide.with(getApplicationContext()).load(icon_url + weatherIconNumber + "-s.png").into(ivWeatherIcon);
                    tvWeatherText.setText(root.get(0).weatherText);

                    Glide.with(getApplicationContext()).load(R.drawable.ic_wind).into(ivWindIcon);
                    tvWindText.setText("Wind");

                    int wind_speed = (int) root.get(0).wind.speed.metric.value;
                    tvWindValue.setText(wind_speed + " km/h");

                    Glide.with(getApplicationContext()).load(R.drawable.ic_pressure__2_).into(ivPressureIcon);
                    tvPressureText.setText("Pressure");

                    int pressure_value = (int) root.get(0).pressure.metric.value;
                    tvPressureValue.setText(pressure_value + " mb");

                    Glide.with(getApplicationContext()).load(R.drawable.ic_humidity).into(ivHumidityIcon);
                    tvHumidityText.setText("Humidity");
                    tvHumidityValue.setText(root.get(0).relativeHumidity + "%");
                    tvTempFelt.setText(String.valueOf((int) root.get(0).realFeelTemperature.metric.value));
                    tvVisibility.setText(String.valueOf((int) root.get(0).visibility.metric.value));
                    tvUv.setText(String.valueOf(root.get(0).uVIndexText));
                    tvWindDegree.setText(root.get(0).wind.direction.degrees + " ⁰");
                    tvWindDirection.setText(String.valueOf(root.get(0).wind.direction.english));


                    if (root.get(0).isDayTime) {
                        Resources resources = getResources();
                        Drawable drawableDay = getDrawable(R.drawable.day_sky_new);
                        rlSectionView.setBackground(drawableDay);
                    }else{
                        Drawable drawableNight = getDrawable(R.drawable.night_sky_new);
                        rlSectionView.setBackground(drawableNight);
                    }

                } else {
                    Toast.makeText(WeatherDetailsActivity.this, "failed", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(Call<List<Root>> call, Throwable t) {
                Toast.makeText(WeatherDetailsActivity.this, "hello   " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void initView() {
        rlSectionView = findViewById(R.id.rl_section_view);
        tvCityName = findViewById(R.id.tv_city_name);
        tvTemperature = findViewById(R.id.tv_temperature);
        ivWeatherIcon = findViewById(R.id.iv_weather_icon);
        tvWeatherText = findViewById(R.id.tv_weather_text);
        rvForecastView = findViewById(R.id.rv_forecast_view);
        ivTemperatureUnit = findViewById(R.id.iv_temperature_unit);
        ivWindIcon = findViewById(R.id.iv_wind_icon);
        tvWindText = findViewById(R.id.tv_wind_text);
        tvWindValue = findViewById(R.id.tv_wind_value);
        ivPressureIcon = findViewById(R.id.iv_pressure_icon);
        tvPressureText = findViewById(R.id.tv_pressure_text);
        tvPressureValue = findViewById(R.id.tv_pressure_value);
        ivHumidityIcon = findViewById(R.id.iv_humidity_icon);
        tvHumidityText = findViewById(R.id.tv_humidity_text);
        tvHumidityValue = findViewById(R.id.tv_humidity_value);
        tvDateTime = findViewById(R.id.tv_date_time);
        tvFiveDayForecastButton = findViewById(R.id.tv_five_day_forecast_button);
        tvTempFelt = findViewById(R.id.tv_temp_felt);
        tvVisibility = findViewById(R.id.tv_visibility);
        tvUv = findViewById(R.id.tv_uv);
        tvWindDirection = findViewById(R.id.tv_wind_direction);
        tvWindDegree = findViewById(R.id.tv_wind_degree);
    }
}