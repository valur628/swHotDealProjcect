package com.GnuApp.swhotdeal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;

import com.GnuApp.swhotdeal.data.HotDeal;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_MENU = 101;

    private Intent select_area_intent;
    private ArrayList<HotDeal> hotDealArrayList;
    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.hotdeal_recycler);
    private RecyclerView.LayoutManager layoutManager;

    String beforeString = "";
    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        activity = this;

        hotDealArrayList = new ArrayList<HotDeal>();

        layoutManager = new LinearLayoutManager(activity);
        recyclerView = findViewById(R.id.hotdeal_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        Button searchButton = findViewById(R.id.button_search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivityForResult(intent, REQUEST_CODE_MENU);
            }
        });


    }
}