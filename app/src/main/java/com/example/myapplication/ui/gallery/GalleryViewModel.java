package com.example.myapplication.ui.gallery;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.HashMap;

public class GalleryViewModel extends ViewModel {
    private final MutableLiveData<String> mText;

    public GalleryViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }


    protected void onCreate(Bundle savedInstanceState) {

    }
        //super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);
}