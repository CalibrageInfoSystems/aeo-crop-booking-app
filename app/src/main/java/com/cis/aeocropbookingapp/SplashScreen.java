package com.cis.aeocropbookingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreen extends AppCompatActivity {

    ImageView imglogo;
    private static int SPLASH_TIME_OUT = 6000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen2);

        imglogo = findViewById(R.id.imglogo);

        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.logo_spalsh);
        imglogo.startAnimation(myanim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                    finish();
            }


        }, SPLASH_TIME_OUT);
    }
}