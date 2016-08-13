package com.mgl.test.hugetest.activities;


import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.mgl.test.hugetest.R;
import com.mgl.test.hugetest.utils.animations.GeneralAnimations;
import com.mgl.test.hugetest.utils.manager.FontManager;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = SplashActivity.class.getName();
    private ImageView splashImageView;
    private TextView textViewName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        init();
    }

    private void initView() {
        splashImageView = (ImageView) findViewById(R.id.imageView_splash);
        textViewName = (TextView) findViewById(R.id.textView_creator_name);
    }

    private void init() {
        loadFonts();
        showLogoAnimation();
    }

    private void loadFonts() {
        FontManager.init(this);
    }



    private void showLogoAnimation() {
        GeneralAnimations.animateFadeIn(splashImageView, 1000, 250, new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                showBottomName();
            }
        });
    }

    private void showBottomName() {
        GeneralAnimations.animateFadeIn(textViewName, 1250, 0, new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                goToMainActivity();
                finish();
            }
        });
    }

    private void goToMainActivity() {
        Intent intent = new Intent(getBaseContext(), CurrencyExchangeActivity.class);
        startActivity(intent);
    }

}
