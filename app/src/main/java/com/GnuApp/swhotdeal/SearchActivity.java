package com.GnuApp.swhotdeal;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.GnuApp.swhotdeal.adapter.SearchAdapter;
import com.GnuApp.swhotdeal.data.HotDeal;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    Activity activity;
    ArrayList<HotDeal> hotDealArrayList;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    SearchView searchView;
    SearchAdapter mAdapter;
    Controller controller;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        String TAG = "SearchActivity";
        String beforeString = "";

        activity = this;
        hotDealArrayList = new ArrayList<>();
        layoutManager = new LinearLayoutManager(activity);
        recyclerView = (RecyclerView) findViewById(R.id.search_recycler);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(mAdapter);
        recyclerView.setLayoutManager(layoutManager);
        searchView = findViewById(R.id.search_view);
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
        SearchView searchView = findViewById(R.id.search_view);
        Intent search_intent = getIntent();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query)
            {
                String[] strings;
                strings = query.split(" ");
                int size = mAdapter.getItemCount();

                try {
                    for(int i = 0 ; i < size ; i++){
                        for(String s : strings){
                            if(s.length() > 0 &&s.charAt(0) == '#'){
                                if(hotDealArrayList.get(i).getSWName().contains(s.substring(1))) { continue; }
                            }
                            hotDealArrayList.remove(i);

                            if(size > 0) size--;
                            if(i > 0) i--;
                        }
                    }
                }catch (IndexOutOfBoundsException e){
                    e.printStackTrace();
                }
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                FirebaseFirestore db = FirebaseFirestore.getInstance();// 파이어베이스 데이터베이스 연동
                String platName = "steamDB";
                // 이러면 steamDB 밖에 못 불러오나??
                // 험블번들, ms store 같이 불러올 방법 생각해봐야함
                if(newText.equals(""))
                    db.collection(platName).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()){
                                hotDealArrayList.clear(); //기존 배열리스트가 존재하지 않게 초기화시켜줌.
                                for (QueryDocumentSnapshot document : task.getResult()){
                                    HotDeal hotDeal = document.toObject(HotDeal.class);
                                    hotDealArrayList.add(hotDeal); //데이터를 배열리스트에 담아 리사이클러 뷰로 보낼 준비
                                    Log.d("ClinicList", document.getId() + "=>" + document.getData());
                                }
                                mAdapter.notifyDataSetChanged(); //리스트 저장 및 새로고침
                            } else {
                                Log.w("SearchActivity", "Error getting documents.", task.getException());
                            }
                        }
                    });
                return true;
            }
        });
    }
}
