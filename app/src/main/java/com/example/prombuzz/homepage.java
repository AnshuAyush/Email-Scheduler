package com.example.prombuzz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

public class homepage extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        // Go to settings
        CardView settings = findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(homepage.this , MainActivity4.class);
                startActivity(intent);
            }
        });

        // Compose card view animation
        CardView compose_email = findViewById(R.id.compose_email);
        Animation compose_animation = AnimationUtils.loadAnimation(homepage.this , R.anim.compose_animation);
        compose_email.setAnimation(compose_animation);



        //setting card animation
        settings.setAnimation(compose_animation);

        //more about us animation
        CardView more_about_us = findViewById(R.id.moreaboutus);
        more_about_us.setAnimation(compose_animation);


        // flying mail animation
        Animation rightflyingmail = AnimationUtils.loadAnimation(homepage.this , R.anim.torightforflyingmail);
        LottieAnimationView flying_mail = findViewById(R.id.flying_mail);
        flying_mail.setAnimation(rightflyingmail);




        //history
        CardView history = findViewById(R.id.history);
        history.setAnimation(compose_animation);


        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotohistory = new Intent(homepage.this , historypage.class);
                startActivity(gotohistory);
            }
        });




        // Go to send email and schedualar
        compose_email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go_send_email_schedualar = new Intent(homepage.this , sendemailandschedular.class);
                startActivity(go_send_email_schedualar);
            }
        });


    }


}