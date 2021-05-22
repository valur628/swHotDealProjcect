package com.GnuApp.swhotdeal.ui.search;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.GnuApp.swhotdeal.Controller;
import com.GnuApp.swhotdeal.R;
import com.GnuApp.swhotdeal.adapter.OnSearchClickListener;
import com.GnuApp.swhotdeal.data.HotDeal;
import com.GnuApp.swhotdeal.adapter.SearchAdapter;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class SearchFragment extends Fragment {

    ArrayList<HotDeal> hotDealArrayList;
    RecyclerView recyclerView;
//    RecyclerView.LayoutManager layoutManager;
    LinearLayoutManager layoutManager;
    SearchView searchView;
    SearchAdapter adapter;
    Controller controller;
    FirebaseFirestore db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String TAG = "SearchFragment";
        String beforeString = "";

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_search, container, false);

        hotDealArrayList = new ArrayList<>();
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        adapter = new SearchAdapter();
        controller = new Controller();
        recyclerView = rootView.findViewById(R.id.search_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setVisibility(View.GONE); // 레이아웃 숨김
        searchView = rootView.findViewById(R.id.search_view);

        getFirebaseData(hotDealArrayList, adapter);
        recyclerView.setAdapter(adapter);

//        ArrayList<HotDeal> listForSearch;
//        listForSearch = (ArrayList<HotDeal>)hotDealArrayList.clone();

        adapter.setOnItemClickListener((onClickListener, view, position) -> {
            HotDeal item = adapter.getItem(position);
            //Toast.makeText(getActivity(), "아이템 선택됨: " + item.getSWName(), Toast.LENGTH_LONG).show();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getPlatAddress()));
            startActivity(intent);
        });
//        아이템 클릭시 판매 사이트로 넘어감
//        작동 안됨 SearchFrag의 hotDealArrayList
//        실제로 저장되는 곳은 SearchAdapter의 mHotDeal

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
//                Log.d("search", "nothing");
                String[] stringArray;
                Log.d("onQueryTextSubmit", hotDealArrayList.toString());
                Log.d("search", hotDealArrayList.toString());
                Log.d("search", query);

                stringArray = query.split(" ");
                int size = hotDealArrayList.size();
                try {
                    for (int i = 0; i < size; i++) {
                        for (String s : stringArray) {
                            if (hotDealArrayList.get(i).getSWName().toLowerCase().contains(s.substring(1))) { // toLowerCase추가
                                continue;
                            }
                            hotDealArrayList.remove(i);
                            if (size > 0)
                                size--;
                            if (i > 0)
                                i--;
                        }
                    }
                } catch (IndexOutOfBoundsException e) {
                    e.printStackTrace();
                }

                Log.d("arrayList2", hotDealArrayList.toString());

                adapter.notifyDataSetChanged();
                recyclerView.setVisibility(View.VISIBLE);

                for (int i = 0; i < hotDealArrayList.size(); i++)
                    Log.d("onclick", hotDealArrayList.get(i).getSWName());

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.d("onQueryTextChange", hotDealArrayList.toString());
                // 여기서 검색하는 곳은 SearchFrag의 hotDealArrayList
                // 하지만 실제로 저장되는 곳은 SearchAdapter의 mHotDeal

                if (newText.equals(""))
                    getFirebaseData(hotDealArrayList, adapter);
                return true;
            }

            public void openRecyclerView(){

            }
        });
    return rootView;
    }

    public void getFirebaseData(ArrayList<HotDeal> arrayList, SearchAdapter adapter){
        FirebaseFirestore db = FirebaseFirestore.getInstance();// 파이어베이스 데이터베이스 연동
        db.collection("ScrapingDB").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()){
                arrayList.clear(); //기존 배열리스트가 존재하지 않게 초기화시켜줌.
                for (QueryDocumentSnapshot document : task.getResult()){
                    HotDeal hotDeal = document.toObject(HotDeal.class);
                    hotDeal.setDivide100();
                    Log.d("getFireBaseData", document.getId());
                    // adapter.addItem(hotDeal); //데이터를 ArrayList에 담아 리사이클러뷰의 mHotdeal로 보냄
                    arrayList.add(hotDeal);
//                        adapter.setItems(hotDeal);
                    Log.d("Hotdeal", document.getId() + "=>" + document.getData());
                }
                adapter.setItems(arrayList);
                adapter.notifyDataSetChanged(); //리스트 저장 및 새로고침
            } else {
                Log.w("", "Error getting documents.", task.getException());
            }
        });
    }
}