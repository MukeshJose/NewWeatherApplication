package com.example.newweatherapplication.Adapter;

import static android.content.Context.MODE_PRIVATE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newweatherapplication.Constants;
import com.example.newweatherapplication.InsertLocation;
import com.example.newweatherapplication.MainActivity;
import com.example.newweatherapplication.R;
import com.example.newweatherapplication.WeatherDetailsActivity;
import com.example.newweatherapplication.model.Root;
import com.example.newweatherapplication.retrofit.APIClient;
import com.example.newweatherapplication.retrofit.APIInterface;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertLocationAdapter extends RecyclerView.Adapter<InsertLocationAdapter.MyViewHolder> {

    public InsertLocationAdapter() {
    }

    Context context;
    Root root;

    public InsertLocationAdapter(Context context, ArrayList<InsertLocation> exampleList) {
        this.context = context;
        mExampleList = exampleList;
    }

    private ArrayList<InsertLocation> mExampleList;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView weatherIcon;
        public TextView cityName;
        public TextView temperatureText;
        public TextView temperatureCustom;
        public ImageView removeButton;

        public MyViewHolder(View itemView) {
            super(itemView);
            removeButton = itemView.findViewById(R.id.iv_remove_button);
            cityName = itemView.findViewById(R.id.tv_city_name);
            temperatureText = itemView.findViewById(R.id.tv_temperature_text);
            temperatureCustom = itemView.findViewById(R.id.tv_temperature_custom);
            weatherIcon = itemView.findViewById(R.id.iv_weather_icon);
        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.insert_custom_layout, parent, false);
        MyViewHolder evh = new MyViewHolder(v);
        return evh;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        InsertLocation currentItem = mExampleList.get(position);
        getLocationName(currentItem, holder);
        getWeatherDetails(currentItem, holder);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToDetails();
            }

            private void goToDetails() {
                Intent intent = new Intent(context, WeatherDetailsActivity.class);
                intent.putExtra("city_name", root.englishName);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        holder.removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getLocationName(currentItem, holder);
                getWeatherDetails(currentItem, holder);
                mExampleList.remove(position);
                notifyItemRemoved(position);
                saveRemovedData();

                Toast.makeText(context, "item removed" + position, Toast.LENGTH_SHORT).show();
            }

            private void saveRemovedData() {
                SharedPreferences sharedPreferences = context.getSharedPreferences("shared preferences", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                String json = gson.toJson(mExampleList);
                editor.putString("task list", json);
                editor.apply();
                Toast.makeText(context, "item removed", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getWeatherDetails(InsertLocation currentItem, MyViewHolder holder) {

        APIInterface apiSavedCityWeather = APIClient.getClientCurrentCondition().create(APIInterface.class);
        apiSavedCityWeather.CALL_APICurrent_condition(currentItem.getLine1(), Constants.api_key, true).enqueue(new Callback<List<Root>>() {
            @Override
            public void onResponse(Call<List<Root>> call, Response<List<Root>> response) {
                if (response.isSuccessful()) {
                    List<Root> rootList = response.body();
                    holder.temperatureText.setText(rootList.get(0).weatherText);
                    holder.temperatureCustom.setText(String.valueOf((int) rootList.get(0).temperature.metric.value));
                    int weatherIconNumber = rootList.get(0).weatherIcon;
                    String icon_url = null;
                    if (weatherIconNumber <= 9) {
                        icon_url = "https://developer.accuweather.com/sites/default/files/0";
                    } else if (weatherIconNumber > 9) {
                        icon_url = "https://developer.accuweather.com/sites/default/files/";
                    }

                    Glide.with(context).load(icon_url + weatherIconNumber + "-s.png").into(holder.weatherIcon);

                }
            }

            @Override
            public void onFailure(Call<List<Root>> call, Throwable t) {

            }
        });
    }

    private void getLocationName(InsertLocation currentItem, MyViewHolder holder) {
        APIInterface apiSavedCities = APIClient.getClientLocationSearch().create(APIInterface.class);
        apiSavedCities.CALL_API_location_search(currentItem.getLine1(), Constants.api_key).enqueue(new Callback<Root>() {
            @Override
            public void onResponse(Call<Root> call, Response<Root> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(context, "successsss", Toast.LENGTH_SHORT).show();
                    root = response.body();
                    holder.cityName.setText(root.englishName);
                } else {
                    Toast.makeText(context, "faileddddd", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Root> call, Throwable t) {
                Toast.makeText(context, "heloooo" + t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

    }

    @Override
    public int getItemCount() {
        return mExampleList.size();
    }
}
