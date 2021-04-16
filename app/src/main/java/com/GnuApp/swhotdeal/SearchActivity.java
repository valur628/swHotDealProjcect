package com.GnuApp.swhotdeal;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.ImageButton;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class SearchActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Get the intent, verify the action and get the query
        Intent intentSearch = getIntent();
        if (Intent.ACTION_SEARCH.equals(intentSearch.getAction())) {
            String query = intentSearch.getStringExtra(SearchManager.QUERY);
            doSearch(query);
        }
    }

    private void doSearch(String query) {
        // 파이어베이스에서 검색한 것 리스트로 보여줌
    }

    SearchView searchView = findViewById(R.id.search_view);
    ImageButton selectButton = findViewById(R.id.search_button);
    searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            select_area_intent = getIntent();
            String select_area = select_area_intent.getStringExtra("Select_area");
            Log.d("onQueryTextChange" , arrayList.toString());

            Log.d("search", "nothing");


            String[] stringArray;
            Log.d("onQueryTextSubmit" , arrayList.toString());
            Log.d("search" , arrayList.toString());
            Log.d("search", query);

            stringArray = query.split(" ");
            int size = arrayList.size();
            try {
                for(int i = 0 ; i < size ; i++){
                    for(String s : stringArray){
                        if(s.length() > 0 &&s.charAt(0) == '#'){
                            if(arrayList.get(i).getExpert().contains(s.substring(1))){
                                continue;
                            }
                        }else{
                            if(s.length() > 0 && arrayList.get(i).getName().contains(s)){
                                continue;
                            }
                        }
                        arrayList.remove(i);
                        if(size > 0)
                            size--;
                        if(i > 0)
                            i--;
                    }
                }
            }catch (IndexOutOfBoundsException e){
                e.printStackTrace();
            }

            Log.d("arrayList2", arrayList.toString());

            adapter.notifyDataSetChanged();

            for(int i = 0 ; i < arrayList.size() ; i++)
                Log.d("onclick", arrayList.get(i).getName());
            return true;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            FirebaseFirestore db = FirebaseFirestore.getInstance();// 파이어베이스 데이터베이스 연동
            if(flag || newText.equals(""))
                db.collection(select_area).orderBy("name"). //파이어베이스에서 Danbi01 collection을 연결한다.
                        get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()){
                                    arrayList.clear(); //기존 배열리스트가 존재하지 않게 초기화시켜줌.
                                    for (QueryDocumentSnapshot document : task.getResult()){
                                        CounsellingCenter counselingCenter = document.toObject(CounsellingCenter.class);
                                        arrayList.add(counselingCenter); //데이터를 배열리스트에 담아 리사이클러 뷰로 보낼 준비
                                        Log.d("hwang", counselingCenter.getGcs().toString());
                                        Log.d("ClinicList", document.getId() + "=>" + document.getData());

                                        adapter.notifyDataSetChanged();
                                        flag =false;

                                    }
                                    //리스트 저장 및 새로고침

                                } else {
                                    Log.w("ClinicList", "Error getting documents.", task.getException());
                                }
                            }
                        });
            return true;

        }
    });
}
