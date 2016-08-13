package com.mgl.test.hugetest.activities;


import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.mgl.test.hugetest.R;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = SplashActivity.class.getName();
    private CountDownTimer timer;
    private ImageView splashImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        init();
    }

    private void initView() {
        splashImageView = (ImageView) findViewById(R.id.imageView_splash);
    }

    private void init() {
        //timer = new CountDownTimer(1800, 200) {
        timer = new CountDownTimer(200, 200) {
            @Override
            public void onTick(long l) {
                Log.e(TAG, "time " + l);
                splashImageView.setRotation(splashImageView.getRotation() + 45);
            }

            @Override
            public void onFinish() {
                Log.e(TAG, "go to activirty");
                goToMainActivity();
            }
        };
        timer.start();
    }

    private void goToMainActivity() {
        Intent intent = new Intent(getBaseContext(), CurrencyExchangeActivity.class);
        startActivity(intent);
    }

}
