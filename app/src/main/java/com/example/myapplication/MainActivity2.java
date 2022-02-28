package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView t1,t2,t3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        //get data
        String userId = getIntent().getStringExtra("userId");
        String id = getIntent().getStringExtra("id");
        String completed = getIntent().getStringExtra("completed");

        t1=findViewById(R.id.tex1);
        t2=findViewById(R.id.tex2);
        t3=findViewById(R.id.tex3);

        //set data in textview
        t1.setText(userId);
        t2.setText(id);
        t3.setText(completed);

        if(t3.getText().equals("true"))
        {
            t3.setTextColor(Color.parseColor("#008000"));
        }else{
            t3.setTextColor(Color.parseColor("#FF0000"));
        }
    }
}