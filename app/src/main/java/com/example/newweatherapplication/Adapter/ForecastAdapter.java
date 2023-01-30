package com.example.newweatherapplication.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newweatherapplication.R;
import com.example.newweatherapplication.model.Root;

import java.util.List;


public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.MyViewHolder> {

    Context context;
    List<Root> root;


    public ForecastAdapter(Context context, List<Root> root) {
        this.context = context;
        this.root = root;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_view_custom_layout, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String time = String.valueOf(root.get(position).dateTime).substring(11, 16);
        holder.tvForecastTime.setText(time);

        String icon_url = null;
        int weatherIconNumber = root.get(position).weatherIcon;
        if (weatherIconNumber <= 9) {
            icon_url = "https://developer.accuweather.com/sites/default/files/0";
        } else if (weatherIconNumber > 9) {
            icon_url = "https://developer.accuweather.com/sites/default/files/";
        }

        Glide.with(context).load(icon_url + weatherIconNumber + "-s.png").into(holder.ivWeatherIcon);
        int temp_fahrenheit = (int) root.get(0).temperature.value;
        int temp_celsius = (int) ((temp_fahrenheit - 32) * 0.55555);

        holder.tvTemperatureCustom.setText(String.valueOf(temp_celsius));
    }

    @Override
    public int getItemCount() {
        return root.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvForecastTime;
        private ImageView ivWeatherIcon;
        private TextView tvTemperatureCustom;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            initView(itemView);
        }

        private void initView(View itemView) {
            tvForecastTime = itemView.findViewById(R.id.tv_forecast_time);
            ivWeatherIcon = itemView.findViewById(R.id.iv_weather_icon);
            tvTemperatureCustom = itemView.findViewById(R.id.tv_temperature_custom);
        }
    }
}
