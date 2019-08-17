package com.dytstudio.signup;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class InternetConnection extends AppCompatActivity {
    LottieAnimationView lottieAnimationView;
    Button back;
    TextView title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet_connection2);
        title = (TextView) findViewById(R.id.titlebar);
        title.setText("Internet Connection" );

        lottieAnimationView = (LottieAnimationView)findViewById(R.id.lottie);
        back = (Button)findViewById(R.id.backbtn);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(InternetConnection.this,SplashScreen.class);
                startActivity(intent);
            }
        });
        startCheckAnimation();
    }

    @RequiresApi(api = Build.VERSION_CODES.HONEYCOMB)
    private void startCheckAnimation() {

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f, 1f).setDuration(3000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                lottieAnimationView.setProgress((Float) animation.getAnimatedValue());
            }
        });
        if (lottieAnimationView.getProgress() == 0f) {

            valueAnimator.start();

        } else {
            lottieAnimationView.setProgress(0f);
        }
    }
}
