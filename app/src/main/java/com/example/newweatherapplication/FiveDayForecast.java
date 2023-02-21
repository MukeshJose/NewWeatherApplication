package com.example.newweatherapplication;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newweatherapplication.Adapter.FiveDayForecastAdapter;
import com.example.newweatherapplication.Adapter.FiveDayForecastAdapterNight;
import com.example.newweatherapplication.model.Root;
import com.example.newweatherapplication.retrofit.APIClient;
import com.example.newweatherapplication.retrofit.APIInterface;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.switchmaterial.SwitchMaterial;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FiveDayForecast extends AppCompatActivity {

    private RecyclerView rvFiveDayForecast;
    ShimmerFrameLayout shimmerFrameLayoutForecast;
    static String locationKey;
    private SwitchMaterial swDayNightToggle;
    private ImageView ivWeatherIcon;
    private TextView tvToday;
    private TextView tvDate;
    private TextView tvCurrentTemperature;
    private TextView tvWeatherCondition;
    private RelativeLayout relativeLayout;

    public FiveDayForecast() {
    }

    public FiveDayForecast(String location_key) {
        locationKey = location_key;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_five_day_forecast);
        initView();
        relativeLayout.setVisibility(View.GONE);
        shimmerFrameLayoutForecast.setVisibility(View.VISIBLE);
        shimmerFrameLayoutForecast.startShimmer();

        APIInterface apiFiveDayForecast = APIClient.getClientFiveDayForecast().create(APIInterface.class);
        APIInterface apiCurrentCondition = APIClient.getClientCurrentCondition().create(APIInterface.class);
        getTodayCondition(apiCurrentCondition, locationKey);
        getFiveDayForecast(apiFiveDayForecast, locationKey);
    }

    private void getTodayCondition(APIInterface apiCurrentCondition, String locationKey) {

        apiCurrentCondition.CALL_APICurrent_condition(locationKey, Constants.api_key, true).enqueue(new Callback<List<Root>>() {
            @Override
            public void onResponse(Call<List<Root>> call, Response<List<Root>> response) {
                if (response.isSuccessful()) {
                    List<Root> root = response.body();
                    String icon_url = null;
                    int weatherIconNumber = root.get(0).weatherIcon;
                    if (weatherIconNumber <= 9) {
                        icon_url = "https://developer.accuweather.com/sites/default/files/0";
                    } else if (weatherIconNumber > 9) {
                        icon_url = "https://developer.accuweather.com/sites/default/files/";
                    }
                    Glide.with(getApplicationContext()).load(icon_url + weatherIconNumber + "-s.png").into(ivWeatherIcon);
                    String dateTime = String.valueOf(root.get(0).localObservationDateTime).substring(0, 10);
                    tvDate.setText(dateTime);
                    tvCurrentTemperature.setText((String.valueOf((int) root.get(0).temperature.metric.value)));
                    tvWeatherCondition.setText(root.get(0).weatherText);


                    shimmerFrameLayoutForecast.stopShimmer();
                    shimmerFrameLayoutForecast.setVisibility(View.GONE);
                    relativeLayout.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<List<Root>> call, Throwable t) {
                Toast.makeText(FiveDayForecast.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getFiveDayForecast(APIInterface apiFiveDayForecast, String locationKey) {

        apiFiveDayForecast.CALL_API_five_day_forecast(locationKey, Constants.api_key, true).enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                if (response.isSuccessful()) {
                    if (!swDayNightToggle.isChecked()) {
                        Root root = response.body();
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
                        rvFiveDayForecast.setLayoutManager(linearLayoutManager);
                        FiveDayForecastAdapter fiveDayForecastAdapter = new FiveDayForecastAdapter(getApplicationContext(), root);
                        rvFiveDayForecast.setAdapter(fiveDayForecastAdapter);


                    }
                    swDayNightToggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

                        @Override
                        public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                            if (swDayNightToggle.isChecked()) {
                                Root root = response.body();
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
                                rvFiveDayForecast.setLayoutManager(linearLayoutManager);
                                FiveDayForecastAdapterNight fiveDayForecastAdapterNight = new FiveDayForecastAdapterNight(getApplicationContext(), root);
                                rvFiveDayForecast.setAdapter(fiveDayForecastAdapterNight);
                            } else {
                                Root root = response.body();
                                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
                                rvFiveDayForecast.setLayoutManager(linearLayoutManager);
                                FiveDayForecastAdapter fiveDayForecastAdapter = new FiveDayForecastAdapter(getApplicationContext(), root);
                                rvFiveDayForecast.setAdapter(fiveDayForecastAdapter);
                            }
                        }
                    });

                } else {
                    Toast.makeText(FiveDayForecast.this, "fiveDay Forecast failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Toast.makeText(FiveDayForecast.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void initView() {
        rvFiveDayForecast = findViewById(R.id.rv_five_day_forecast);
        swDayNightToggle = findViewById(R.id.sw_day_night_toggle);
        ivWeatherIcon = findViewById(R.id.iv_weather_icon);
        tvToday = findViewById(R.id.tv_today);
        relativeLayout = findViewById(R.id.rl_forecast_layout);
        shimmerFrameLayoutForecast = findViewById(R.id.forecast_shimmer);
        tvDate = findViewById(R.id.tv_date);
        tvCurrentTemperature = findViewById(R.id.tv_current_temperature);
        tvWeatherCondition = findViewById(R.id.tv_weather_condition);
    }
}