package com.example.newweatherapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    private EditText etSearchBar;
    private Button btSearchButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        btSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), WeatherDetailsActivity.class);
                intent.putExtra("city_name", etSearchBar.getText().toString());
                startActivity(intent);
            }
        });
    }

    private void initView() {
        etSearchBar = findViewById(R.id.et_search_bar);
        btSearchButton = findViewById(R.id.bt_search_button);
    }
}
