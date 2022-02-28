package com.example.myapplication.ui.gallery;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.myapplication.MainActivity;
import com.example.myapplication.MenuActivity;
import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentGalleryBinding;
import com.example.myapplication.databinding.FragmentHomeBinding;
import com.example.myapplication.datalistview;
import com.example.myapplication.login;

import java.util.ArrayList;
import java.util.HashMap;

public class GalleryFragment extends Fragment implements View.OnClickListener{

    View rootview;
    private FragmentHomeBinding binding;
    public static int resume ;
    public void onCreate(Bundle savedInstanceState) { super.onCreate(savedInstanceState); }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        rootview = inflater.inflate(R.layout.fragment_gallery, container, false);
        Button listview = (Button)rootview.findViewById(R.id.listview);
        listview.setOnClickListener(this);
        return rootview;
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    @Override

    //click listview button to display datalistview
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.listview:
                Button list = (Button) rootview.findViewById(R.id.listview);

                Intent ii = new Intent(getActivity(), datalistview.class);
                startActivity(ii);
                ((Activity) getActivity()).overridePendingTransition(0, 0);
                break;
        }
    }
}
