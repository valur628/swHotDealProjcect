package com.GnuApp.swhotdeal;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.GnuApp.swhotdeal.data.HotDeal;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private Intent select_area_intent;
    private ArrayList<HotDeal> hotDealArrayList;
    private RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView = (RecyclerView) findViewById(R.id.hotdeal_recycler);
    ArrayAdapter<String> adapter;
    ArrayList<String> dealList;

    String beforeString = "";
    Activity activity;

//    private String object;
    private Intent search_intent;
    SearchView searchView = (SearchView) findViewById(R.id.search_view);

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        getSupportActionBar().hide();
        activity = this;
        hotDealArrayList = new ArrayList<HotDeal>();
        layoutManager = new LinearLayoutManager(activity);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        // Get the intent, verify the action and get the query
        Intent intentSearch = getIntent();
        if (Intent.ACTION_SEARCH.equals(intentSearch.getAction())) {
            String query = intentSearch.getStringExtra(SearchManager.QUERY);
            doSearch(query);
        }
    }

    private void doSearch(String query) {
        SearchView searchView = (SearchView) findViewById(R.id.search_view);
//      CharSequence fireQuery = searchView.getQuery();
        search_intent = getIntent();

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
