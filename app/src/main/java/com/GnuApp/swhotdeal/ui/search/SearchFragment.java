package com.GnuApp.swhotdeal.ui.search;

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
        searchView = rootView.findViewById(R.id.search_view);

        HotDeal hot = new HotDeal();
        adapter.addItem(new HotDeal("프로그람", 2077, 400000, 300000, 2500, "https://cs.gnu.ac.kr/cs/main.do", "경상대", "https://cs.gnu.ac.kr/csadmin/_Img/main_image/03.jpg", "https://cs.gnu.ac.kr/_Img/Layout/flogo.gif"));
        //adapter.addItem(new HotDeal("program", 2066, 500000, 400000, 2000, "https://naver.com", "네이버", "https://newgh.gnu.ac.kr/common/images/T1_layout/logo.png", "https://newgh.gnu.ac.kr/common/images/T1_layout/logo.png"));
        //adapter.addItem(hot);
        getFirebaseData(hotDealArrayList, adapter);

        //adapter.addTemp(); // 여기서 추가하는 곳은 SearchAdapter의 mHotDeal
        recyclerView.setAdapter(adapter);

//        adapter.setOnItemClickListener(new OnSearchClickListener() {
//            @Override
//            public void OnItemClick(View.OnClickListener onClickListener, View view, int position) {
//                HotDeal item = adapter.getItem(position);
//
//                Toast.makeText(getActivity(), "아이템 선택됨: " + item.getSWName(), Toast.LENGTH_LONG).show();
//            }
//        });
//        아이템 클릭시 판매 사이트로 넘어감
//        작동 안됨 SearchFrag의 hotDealArrayList
//        실제로 저장되는 곳은 SearchAdapter의 mHotDeal

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("onQueryTextChange", hotDealArrayList.toString()); // 여기서 검색하는 곳은 SearchFrag의 hotDealArrayList
                                                                                // 하지만 실제로 저장되는 곳은 SearchAdapter의 mHotDeal
                Log.d("search", "nothing");
                String[] stringArray;
                Log.d("onQueryTextSubmit", hotDealArrayList.toString());
                Log.d("search", hotDealArrayList.toString());
                Log.d("search", query);

                stringArray = query.split(" ");
                int size = hotDealArrayList.size();
                try {
                    for (int i = 0; i < size; i++) {
                        for (String s : stringArray) {
                            if (hotDealArrayList.get(i).getSWName().contains(s.substring(1))) {
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

                for (int i = 0; i < hotDealArrayList.size(); i++)
                    Log.d("onclick", hotDealArrayList.get(i).getSWName());

                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                FirebaseFirestore db = FirebaseFirestore.getInstance();// 파이어베이스 데이터베이스 연동
                if (newText.equals(""))
                    db.collection("ScrapingDB").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                hotDealArrayList.clear(); //기존 배열리스트가 존재하지 않게 초기화시켜줌.
                                for (QueryDocumentSnapshot document : task.getResult()) {
                                    HotDeal hotDeal = document.toObject(HotDeal.class);
                                    hotDealArrayList.add(hotDeal); //데이터를 배열리스트에 담아 리사이클러 뷰로 보낼 준비
                                    Log.d("SearchFragment", document.getId() + "=>" + document.getData());
                                }
                                adapter.notifyDataSetChanged();
                            } else {
                                Log.w("SearchFragment", "Error getting documents.", task.getException());
                            }
                        }
                    });
                return true;
            }
        });
    return rootView;
    }
    
    // 함수 실행하면 안됨
    public void getFirebaseData(ArrayList<HotDeal> arrayList, SearchAdapter adapter){
        FirebaseFirestore db = FirebaseFirestore.getInstance();// 파이어베이스 데이터베이스 연동
        db.collection("ScrapingDB").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    arrayList.clear(); //기존 배열리스트가 존재하지 않게 초기화시켜줌.
                    for (QueryDocumentSnapshot document : task.getResult()){
                        HotDeal hotDeal = document.toObject(HotDeal.class);
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
            }
        });
    }
}