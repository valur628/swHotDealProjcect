package com.GnuApp.swhotdeal.ui.search;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.GnuApp.swhotdeal.MainActivity;
import com.GnuApp.swhotdeal.R;
import com.GnuApp.swhotdeal.data.HotDeal;
import com.GnuApp.swhotdeal.adapter.SearchAdapter;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    ArrayList<HotDeal> hotDealArrayList;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    SearchView searchView;
    SearchAdapter mAdapter;
    boolean flag = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String TAG = "SearchFragment";
        String beforeString = "";

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_search, container, false);

        hotDealArrayList = new ArrayList<HotDeal>();
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView = rootView.findViewById(R.id.hotdeal_recycler);
        searchView = rootView.findViewById(R.id.search_view);

        return rootView;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        // Inflate the menu; this adds items to the action bar if it is present.

        inflater.inflate(R.menu.main, menu); // main
        MenuItem menuItem = menu.findItem(R.id.hotdeal_list_relative); // search

        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
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
//                FirebaseFirestore db = FirebaseFirestore.getInstance();// 파이어베이스 데이터베이스 연동
//                if(flag || newText.equals(""))
//                    db.collection(select_area).orderBy("name"). // 파이어베이스에서 Danbi01 collection을 연결한다.
//                                                                // 단비 앱에서 지역선택 부분, 우리 앱에서는 필요없음
//                            get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                                @Override
//                                public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                                    if (task.isSuccessful()){
//                                        hotDealArrayList.clear(); //기존 배열리스트가 존재하지 않게 초기화시켜줌.
//                                        for (QueryDocumentSnapshot document : task.getResult()){
//                                            HotDeal hotDeal = document.toObject(HotDeal.class);
//                                            hotDealArrayList.add(hotDeal); //데이터를 배열리스트에 담아 리사이클러 뷰로 보낼 준비
//                                            Log.d("ClinicList", document.getId() + "=>" + document.getData());
//
//                                            // adapter.notifyDataSetChanged();
//                                            flag =false;
//
//                                        }
//                                        //리스트 저장 및 새로고침
//
//                                    } else {
//                                        Log.w("ClinicList", "Error getting documents.", task.getException());
//                                    }
//                                }
//                            });
//                return true;

                return false;
            }
        });

        super.onCreateOptionsMenu(menu, inflater);
    }
}