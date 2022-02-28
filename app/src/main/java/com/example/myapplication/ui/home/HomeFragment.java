package com.example.myapplication.ui.home;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.MainActivity;
import com.example.myapplication.MenuActivity;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentHomeBinding;
import com.example.myapplication.datalistview;
import com.example.myapplication.login;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

public class HomeFragment extends Fragment {

    View rootview;
    private FragmentHomeBinding binding;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_home, container, false);
        TextView datt = (TextView)rootview.findViewById(R.id.date);

        //get date from sharedpreferences and set it in textview
        SharedPreferences sh = this.getActivity().getSharedPreferences("prefdate", 0);
        String s1 =sh.getString("name","");
        datt.setText(s1);

        //get counter from mainactivity and set it in textview.
        int counter = MainActivity.getAppCounter();
        TextView timee = (TextView) rootview.findViewById(R.id.timerText);
        timee.setText(""+counter);
        return rootview;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}