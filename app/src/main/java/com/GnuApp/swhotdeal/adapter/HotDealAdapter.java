package com.GnuApp.swhotdeal.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.GnuApp.swhotdeal.R;
import com.GnuApp.swhotdeal.data.HotDeal;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class HotDealAdapter extends RecyclerView.Adapter<HotDealAdapter.HotDealHolder> {

    private ArrayList<HotDeal> mHotDeal;
    private HotDealHolder hotDealHolder;
    DecimalFormat df = new DecimalFormat("#.##");

    public static class HotDealHolder extends RecyclerView.ViewHolder {

        public HotDeal hotDeal;
        public TextView swName;
        public TextView disPrice;
        public TextView cost;
        public ImageView repPicture;
        public ImageView imgPlat;

        public HotDealHolder(View itemView) {
            super(itemView);
            swName = (TextView) itemView.findViewById(R.id.text_sw_name);
            disPrice = (TextView) itemView.findViewById(R.id.text_dis_price);
            cost = (TextView) itemView.findViewById(R.id.text_cost);
            repPicture = (ImageView) itemView.findViewById(R.id.img_rep_pic);
            imgPlat = (ImageView) itemView.findViewById(R.id.img_plat);
        }
    }

    public HotDealAdapter (ArrayList<HotDeal> arrayList) {
        mHotDeal = arrayList;
    }

    //inflate the layout for your list
    @NotNull
    public HotDealAdapter.HotDealHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.hotdeal_list_relative, viewGroup, false);

        return new HotDealAdapter.HotDealHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotDealHolder holder, int position) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        HotDeal hotDeal = mHotDeal.get(position);

        String disPrice = df.format(hotDeal.getDisPrice());
        String cost = df.format(hotDeal.getCost());
        String repPicture = hotDeal.getRepPicture();
        // 플랫폼 이미지는 나중에 구현하자. 경우의 수에 따라 로컬에서 불러오는 것으로? 1, 2, 3

        hotDealHolder.disPrice.setText(disPrice);
        hotDealHolder.cost.setText(cost);
        hotDealHolder.repPicture.setImageResource(Integer.parseInt(repPicture));
        // 파이어베이스에서 텍스트, 이미지 받아올 것.
        // 플랫폼 이미지는 나중에 구현하자.
    }

    @Override
    public void onAttachedToRecyclerView(@NotNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    // list 크기 반환
     public int getItemCount() {
        return mHotDeal.size();
    }
}