package com.GnuApp.swhotdeal.ui.hotdeal;

import android.content.Intent;
import android.graphics.Paint;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

public class HotdealFragment extends Fragment {

    ArrayList<HotDeal> hotDealArrayList;
    RecyclerView recyclerView;
    LinearLayoutManager layoutManager;
    SearchAdapter adapter;
    FirebaseFirestore db;
    FragmentManager manager;
    TextView main_cost;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        String TAG = "HotdealFragment";
        String beforeString = "";

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment_hotdeal, container, false);


        hotDealArrayList = new ArrayList<>();
        layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        adapter = new SearchAdapter();
        recyclerView = rootView.findViewById(R.id.hotdeal_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        main_cost = rootView.findViewById(R.id.main_cost);
        main_cost.setPaintFlags(main_cost.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        recyclerView.setHasFixedSize(true);

        HotDeal hot = new HotDeal();
        adapter.addItem(new HotDeal("프로그람", 2077, 400000, 300000, 2500, "https://cs.gnu.ac.kr/cs/main.do", "경상대", "https://cs.gnu.ac.kr/csadmin/_Img/main_image/03.jpg", "https://cs.gnu.ac.kr/_Img/Layout/flogo.gif"));
        getFirebaseData(hotDealArrayList, adapter);

        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener((onClickListener, view, position) -> {
            HotDeal item = adapter.getItem(position);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getPlatAddress()));
            startActivity(intent);
        });

        return rootView;
    }

    public void getFirebaseData(ArrayList<HotDeal> arrayList, SearchAdapter adapter){
        FirebaseFirestore db = FirebaseFirestore.getInstance();// 파이어베이스 데이터베이스 연동
        db.collection("ScrapingDB").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
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
            }
        });
    }
}