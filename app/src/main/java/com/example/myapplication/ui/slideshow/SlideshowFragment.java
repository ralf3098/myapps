package com.example.myapplication.ui.slideshow;

import android.app.Activity;
import android.content.DialogInterface;
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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.MainActivity;
import com.example.myapplication.MenuActivity;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentSlideshowBinding;
import com.example.myapplication.datalistview;
import com.example.myapplication.ui.home.HomeFragment;

import java.util.Timer;
import java.util.TimerTask;

public class SlideshowFragment extends Fragment implements View.OnClickListener {

    View rootview;
    private FragmentSlideshowBinding binding;
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState);

    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_slideshow, container, false);
        Button logout = (Button)rootview.findViewById(R.id.logout);
        logout.setOnClickListener(this);
        return rootview;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    //logout button: contain a dialog confirmation and delete all saved data
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.logout:
                //dialog
                new AlertDialog.Builder(getActivity())
                        .setTitle("logout")
                        .setMessage("are you sure you want to logout?")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {
                        Button list = (Button) rootview.findViewById(R.id.listview);
                        //reset data from sharedpreferences
                        SharedPreferences preferences = getActivity().getSharedPreferences("checkbox",0);
                        SharedPreferences.Editor editor= preferences.edit();
                        editor.putString("remember", "false");
                        editor.apply();
                        //finish this acti
                        getActivity().finish();
                        //main activity(splash screen) will open
                        Intent ii = new Intent(getActivity(), MainActivity.class);
                        startActivity(ii);
                        ((Activity) getActivity()).overridePendingTransition(0, 0);
                    }
                    //no: in the dialog it will cancel the confirmation of logout
                }).setNegativeButton("no", null).show();


                break;
        }
    }
}