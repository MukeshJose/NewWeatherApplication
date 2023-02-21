package com.example.newweatherapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import com.airbnb.lottie.LottieAnimationView;
import com.facebook.shimmer.ShimmerFrameLayout;

public class ApplicationSplashScreen extends AppCompatActivity {
    ShimmerFrameLayout shimmerFrameLayout;

    LottieAnimationView lottieAnimationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_splash_screen);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_application_splash_screen);

        lottieAnimationView = findViewById(R.id.splash_screen_animation);

        lottieAnimationView.animate().setDuration(5000);

//        lottieAnimationView.animate().translationX(2000).translationY(3000).setDuration(7000).setStartDelay(6000);
//        shimmerFrameLayout = findViewById(R.id.sl_application_splash_screen_shimmer);
//        shimmerFrameLayout.startShimmer();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 4600);
    }
}