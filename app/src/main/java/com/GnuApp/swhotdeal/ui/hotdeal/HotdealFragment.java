package com.GnuApp.swhotdeal.ui.hotdeal;

import android.graphics.Paint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.GnuApp.swhotdeal.MainActivity;
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
        hotDealArrayList = new ArrayList<>();
      
//        ArrayAdapter Adapter = new ArrayAdapter(getActivity(), android.R.layout.hotdeal_listview,);

        TextView maincost = (TextView) viewGroup.findViewById(R.id.main_cost);
        maincost.setPaintFlags(maincost.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);
      
        return viewGroup;
    }
}