package com.akshaytech.tictactoe;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;

public class StartActivity extends AppCompatActivity {
    EditText editText1,editText2;
    String firstName,secondName;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        button = findViewById(R.id.btnSubmit);
        editText1 = findViewById(R.id.firstName);
        editText2 = findViewById(R.id.secondName);

    }

    public void Submit(View view) {
        firstName = editText1.getText().toString();
        secondName = editText2.getText().toString();
        Animation animation = AnimationUtils.loadAnimation(StartActivity.this,R.anim.bounce);
        button.startAnimation(animation);
        Intent intent = new Intent(StartActivity.this,MainActivity.class);
        intent.putExtra("first",firstName);
        intent.putExtra("second",secondName);
        startActivity(intent);
    }
}
