package com.GnuApp.swhotdeal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.view.View;

import com.GnuApp.swhotdeal.SearchActivity;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_MENU = 101;

    Activity activity;
    Button searchButton;
    ArrayList<HotDeal> hotDealArrayList;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String TAG = "MainActivity";
        String beforeString = "";

        activity = this;
        hotDealArrayList = new ArrayList<>();
        layoutManager = new LinearLayoutManager(activity);
        recyclerView = findViewById(R.id.hotdeal_recycler);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(layoutManager);
        // recyclerView를 만들긴 했는데 어떻게 다룰지 안 만듦.
        // recyclerView를 다루기 위한 어댑터를 만들자!

        searchButton = findViewById(R.id.button_search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivityForResult(intent, REQUEST_CODE_MENU);
            }
        });
    }
}