package com.example.newweatherapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.ImageFormat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newweatherapplication.Adapter.InsertLocationAdapter;
import com.example.newweatherapplication.model.Root;
import com.example.newweatherapplication.retrofit.APIClient;
import com.example.newweatherapplication.retrofit.APIInterface;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.textfield.TextInputEditText;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private EditText etSearchBar;
    private ImageView btSearchButton;
    private TextView tvClearAllButton;
    private RelativeLayout relativeLayout;

    public MainActivity() {
    }

    ArrayList<InsertLocation> mExampleList;
    RecyclerView mRecyclerView;
    InsertLocationAdapter insertLocationAdapter;
    ShimmerFrameLayout shimmerFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_main_activity);
        initView();
//        relativeLayout.setVisibility(View.GONE);
//        shimmerFrameLayout.setVisibility(View.VISIBLE);
//        shimmerFrameLayout.startShimmer();
        loadData();
        buildRecyclerView();


        tvClearAllButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mExampleList = new ArrayList<>();
                SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                Gson gson = new Gson();
                String json = gson.toJson(mExampleList);
                editor.putString("task list", json);
                editor.apply();
                Toast.makeText(MainActivity.this, "saved", Toast.LENGTH_SHORT).show();
                buildRecyclerView();
            }
        });


        btSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), WeatherDetailsActivity.class);
                intent.putExtra("city_name", etSearchBar.getText().toString());
                startActivity(intent);

            }
        });
    }

    public void loadData() {
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("task list", null);
        Type type = new TypeToken<ArrayList<InsertLocation>>() {
        }.getType();


        mExampleList = gson.fromJson(json, type);
        if (mExampleList == null) {
            mExampleList = new ArrayList<>();
        }
        WeatherDetailsActivity weatherDetailsActivity = new WeatherDetailsActivity(mExampleList);
//        shimmerFrameLayout.stopShimmer();
//        shimmerFrameLayout.setVisibility(View.GONE);
//        relativeLayout.setVisibility(View.VISIBLE);

    }

    private void initView() {
        etSearchBar = findViewById(R.id.et_search_bar);
        tvClearAllButton = findViewById(R.id.tv_all_clear_button);
        btSearchButton = findViewById(R.id.iv_search_button);
        relativeLayout = findViewById(R.id.rl_main_layout);
        shimmerFrameLayout = findViewById(R.id.sl_main_activity_shimmer);
    }

    public void buildRecyclerView() {

        mRecyclerView = findViewById(R.id.savedCitiesView);
        mRecyclerView.setHasFixedSize(true);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        insertLocationAdapter = new InsertLocationAdapter(getApplicationContext(), mExampleList);
        mRecyclerView.setLayoutManager(mGridLayoutManager);
        mRecyclerView.setAdapter(insertLocationAdapter);

        Toast.makeText(this, "entered recyclerView", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}


