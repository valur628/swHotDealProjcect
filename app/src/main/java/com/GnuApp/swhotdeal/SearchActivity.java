package com.GnuApp.swhotdeal;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    Activity activity;
    ArrayList<HotDeal> hotDealArrayList;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    SearchView searchView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        String TAG = "SearchActivity";
        String beforeString = "";

        activity = this;
        hotDealArrayList = new ArrayList<>();
        layoutManager = new LinearLayoutManager(activity);
        recyclerView = (RecyclerView) findViewById(R.id.hotdeal_recycler);
        searchView = (SearchView) findViewById(R.id.search_view);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(layoutManager);
        // recyclerView를 만들긴 했는데 어떻게 다룰지 안 만듦.
        // recyclerView를 다루기 위한 어댑터를 만들자!

        // Get the intent, verify the action and get the query
        Intent intentSearch = getIntent();
        if (Intent.ACTION_SEARCH.equals(intentSearch.getAction())) {
            String query = intentSearch.getStringExtra(SearchManager.QUERY);
            doSearch(query);
        }
    }

    private void doSearch(String query) {
        SearchView searchView = (SearchView) findViewById(R.id.search_view);
        Intent search_intent = getIntent();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}
