package com.akshaytech.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import java.util.concurrent.Delayed;

public class AnimationActivity extends AppCompatActivity {
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation);
        button = findViewById(R.id.btnstart);


    }

    public void Move(View view) {
        Animation animation = AnimationUtils.loadAnimation(AnimationActivity.this,R.anim.bounce);
        button.startAnimation(animation);
        Intent intent = new Intent(AnimationActivity.this,StartActivity.class);
        startActivity(intent);
    }
}
