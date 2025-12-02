package com.taskflow.app.ui;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.taskflow.app.R;

public class SplashActivity extends AppCompatActivity {
    private ImageView logoIcon;
    private TextView appName;
    private TextView subtitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        
        logoIcon = findViewById(R.id.logoIcon);
        appName = findViewById(R.id.appName);
        subtitle = findViewById(R.id.subtitle);

        startAnimation();
    }

    private void startAnimation() {
        // Анимация иконки
        ObjectAnimator scaleX = ObjectAnimator.ofFloat(logoIcon, "scaleX", 0f, 1f);
        ObjectAnimator scaleY = ObjectAnimator.ofFloat(logoIcon, "scaleY", 0f, 1f);
        ObjectAnimator alpha = ObjectAnimator.ofFloat(logoIcon, "alpha", 0f, 1f);
        
        AnimatorSet iconAnimator = new AnimatorSet();
        iconAnimator.playTogether(scaleX, scaleY, alpha);
        iconAnimator.setDuration(800);
        iconAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        
        // Анимация названия
        ObjectAnimator nameAlpha = ObjectAnimator.ofFloat(appName, "alpha", 0f, 1f);
        ObjectAnimator nameTranslation = ObjectAnimator.ofFloat(appName, "translationY", 50f, 0f);
        
        AnimatorSet nameAnimator = new AnimatorSet();
        nameAnimator.playTogether(nameAlpha, nameTranslation);
        nameAnimator.setDuration(600);
        nameAnimator.setStartDelay(400);
        nameAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        
        // Анимация подзаголовка
        ObjectAnimator subtitleAlpha = ObjectAnimator.ofFloat(subtitle, "alpha", 0f, 1f);
        ObjectAnimator subtitleTranslation = ObjectAnimator.ofFloat(subtitle, "translationY", 30f, 0f);
        
        AnimatorSet subtitleAnimator = new AnimatorSet();
        subtitleAnimator.playTogether(subtitleAlpha, subtitleTranslation);
        subtitleAnimator.setDuration(600);
        subtitleAnimator.setStartDelay(700);
        subtitleAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        
        // Запускаем все анимации
        iconAnimator.start();
        nameAnimator.start();
        subtitleAnimator.start();
        
        // Переход на главный экран
        new Handler().postDelayed(() -> {
            startActivity(new Intent(this, MainActivity.class));
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
            finish();
        }, 2500);
    }
}
