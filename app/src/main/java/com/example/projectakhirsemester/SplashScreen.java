package com.example.projectakhirsemester;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreen extends AppCompatActivity {

    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            EasySplashScreen config = new EasySplashScreen(SplashScreen.this)
                    .withFullScreen()
                    .withTargetActivity(MainActivity.class)
                    .withSplashTimeOut(3000)
                    .withBackgroundColor(Color.parseColor("#2FAB89"))
                    .withAfterLogoText("RaqibAtid")
                    .withLogo(R.drawable.bgro);
            config.getAfterLogoTextView().setTextColor(Color.WHITE);
            View easySplashScreen = config.create();
            setContentView(easySplashScreen);
        }
    }

