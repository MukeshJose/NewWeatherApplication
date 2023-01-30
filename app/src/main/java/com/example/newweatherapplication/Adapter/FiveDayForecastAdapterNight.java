package com.example.newweatherapplication.Adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newweatherapplication.R;
import com.example.newweatherapplication.model.Root;

public class FiveDayForecastAdapterNight extends RecyclerView.Adapter<FiveDayForecastAdapterNight.MyViewHolder> {
    Context context;
    Root root;

    public FiveDayForecastAdapterNight(Context context, Root root) {
        this.context = context;
        this.root = root;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.five_day_weather_custom_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        String date = String.valueOf(root.dailyForecasts.get(position).date).substring(0, 10);
        holder.tvForecastDate.setText(date);
        String icon_url = null;
        int weatherIconNumber = root.dailyForecasts.get(position).night.icon;
        if (weatherIconNumber <= 9) {
            icon_url = "https://developer.accuweather.com/sites/default/files/0";
        } else if (weatherIconNumber > 9) {
            icon_url = "https://developer.accuweather.com/sites/default/files/";
        }

        Glide.with(context).load(icon_url + weatherIconNumber + "-s.png").into(holder.ivWeatherIcon);
        holder.tvForecastCondition.setText(root.dailyForecasts.get(position).night.iconPhrase);
        int maxTemp = (int) root.dailyForecasts.get(position).temperature.maximum.value;
        holder.tvMaxTemp.setText(String.valueOf(maxTemp));
        holder.tvTempSeparation.setText("/");
        int minTemp = (int) root.dailyForecasts.get(position).temperature.minimum.value;
        holder.tvMinTemp.setText(String.valueOf(minTemp));


    }

    @Override
    public int getItemCount() {
        return root.dailyForecasts.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvForecastDate;
        private ImageView ivWeatherIcon;
        private TextView tvForecastCondition;
        private TextView tvMaxTemp;
        private TextView tvTempSeparation;
        private TextView tvMinTemp;
        private ImageView ivTempUnit;
        private RelativeLayout rlForeCastLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(View itemView) {
            tvForecastDate = itemView.findViewById(R.id.tv_forecast_date);
            ivWeatherIcon = itemView.findViewById(R.id.iv_weather_icon);
            tvForecastCondition = itemView.findViewById(R.id.tv_forecast_condition);
            tvMaxTemp = itemView.findViewById(R.id.tv_max_temp);
            tvTempSeparation = itemView.findViewById(R.id.tv_temp_separation);
            tvMinTemp = itemView.findViewById(R.id.tv_min_temp);
            rlForeCastLayout = itemView.findViewById(R.id.rl_forecast_layout);


        }
    }
}
