package com.GnuApp.swhotdeal;

import android.util.Log;

import androidx.annotation.NonNull;

import com.GnuApp.swhotdeal.adapter.HotDealAdapter;
import com.GnuApp.swhotdeal.adapter.SearchAdapter;
import com.GnuApp.swhotdeal.data.HotDeal;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Controller {
    FirebaseFirestore db;

    public Controller(){
    }

    public void setTemporaryData(String platName, ArrayList<HotDeal> arrayList, SearchAdapter adapter){
    }
    
    public void getFirebaseData(String platName, ArrayList<HotDeal> arrayList, SearchAdapter adapter){
        db.collection(platName).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
                    arrayList.clear(); //기존 배열리스트가 존재하지 않게 초기화시켜줌.
                    for (QueryDocumentSnapshot document : task.getResult()){
                        HotDeal hotDeal = document.toObject(HotDeal.class);
                        adapter.addItem(hotDeal); //데이터를 배열리스트에 담아 리사이클러 뷰로 보낼 준비
                        Log.d("Hotdeal", document.getId() + "=>" + document.getData());
                    }
                    adapter.notifyDataSetChanged(); //리스트 저장 및 새로고침
                } else {
                    Log.w("ClinicList", "Error getting documents.", task.getException());
                }
            }
        });
    }
}
