package com.GnuApp.swhotdeal.ui.hotdeal;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.GnuApp.swhotdeal.R;
import com.GnuApp.swhotdeal.adapter.HotDealAdapter;
import com.GnuApp.swhotdeal.data.HotDeal;

import java.util.ArrayList;

public class HotdealFragment extends Fragment {

    TextView maintitle;
    ArrayList<HotDeal> hotDealArrayList;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    HotDealAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup viewGroup = (ViewGroup)inflater.inflate(R.layout.fragment_hotdeal, container, false);
        hotDealArrayList = new ArrayList<HotDeal>();

        recyclerView = viewGroup.findViewById(R.id.hotdeal_recycler);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
//        adapter = new HotDealAdapter(hotDealArrayList);
//        recyclerView.setAdapter(adapter);
//        recyclerView.setHasFixedSize(true);
        return viewGroup;
    }
}