package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    public static Timer timer = new Timer();
    public static int counter = 0;
    //I  used getter and setter in mainactivity to be related to all activities
    //get the timer, set counter = sec, then return counter.
    public static Timer getAppTimer(){
        return timer;
    }
    public static void setAppCounter(int seconds){
        counter = seconds;
    }
    public static int getAppCounter(){
        return counter;
    }

    //stay at this activity for 2 seconds
    public static int SPLASH_TIME_OUT=2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //after 2 seconds login activity will open.
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                Intent f = new Intent(MainActivity.this, login.class);
                startActivity(f);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}