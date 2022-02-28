package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.ui.home.HomeFragment;
import com.example.myapplication.ui.slideshow.SlideshowFragment;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class login extends AppCompatActivity {
    EditText user, password;
    private Button login;
    CheckBox remember;
    Calendar calendar;
    SimpleDateFormat simpleDateFormat;

    public static String da;
    public static String dat(){
        return da;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user=findViewById(R.id.username);
        password=findViewById(R.id.pass);
        login=findViewById(R.id.login);
        remember=(CheckBox) findViewById(R.id.checkR);
        calendar=Calendar.getInstance();


        user.setText("");
        password.setText("");

        //save the cuurent date in a sharedpreferences
        SharedPreferences prefdate = getSharedPreferences("prefdate",0);
        SharedPreferences.Editor edit= prefdate.edit();

        //save the checkbox if its checked to avoid the login class
        SharedPreferences prefenrences = getSharedPreferences("checkbox",0);
        String checkbox =prefenrences.getString("remember","");

        //if its true the app login automatically
        if(checkbox.equals("true")){
            Intent i = new Intent(login.this, MenuActivity.class);
            startActivity(i);
         //if its false we get a  toast to sign in
        }else if (checkbox.equals("false")){
            Toast.makeText(login.this, "Sign in Please", Toast.LENGTH_SHORT).show();
        }

        //login button: gettext from edittext user and password. if username and pass equals to ralf or ibra
        //we get the timer from the mainactivity and we add 1 after every 1sec. then menu will open.
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=user.getText().toString();
                String pass= password.getText().toString();
                if((name.equals("Ralf") && pass.equals("Ralf")) || (name.equals("Ibra") && pass.equals("Ibra")))
                {
                    simpleDateFormat=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                    da=simpleDateFormat.format(calendar.getTime());
                    edit.putString("name", da);
                    edit.commit();

                    Timer timer = MainActivity.getAppTimer();
                    timer.scheduleAtFixedRate(new TimerTask() {
                        @Override
                        public void run() {
                            int appCounter = MainActivity.getAppCounter();
                            MainActivity.setAppCounter(appCounter+1);
                        }
                    },0,1000);

                    startActivity(new Intent(login.this, MenuActivity.class));

                    finish();
                }
            }
        });

        //to save the checkbox in sharedpreferences if its checked to let the app logged in if the checkbox
        //not checked login Act will open
        remember.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(compoundButton.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("checkbox",0);
                    SharedPreferences.Editor editor= preferences.edit();
                    editor.putString("remember", "true");
                    editor.apply();
                    Toast.makeText(login.this,"Checked",Toast.LENGTH_SHORT).show();
                }else if(!compoundButton.isChecked()){
                    SharedPreferences preferences = getSharedPreferences("checkbox",0);
                    SharedPreferences.Editor editor= preferences.edit();
                    editor.putString("remember", "false");
                    editor.apply();
                    Toast.makeText(login.this,"UnChecked",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}