package com.GnuApp.swhotdeal.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.GnuApp.swhotdeal.R;
import com.GnuApp.swhotdeal.data.HotDeal;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchHolder> {

    private final ArrayList<HotDeal> mHotDeal;

    public static class SearchHolder extends RecyclerView.ViewHolder {

        public TextView swName;
        public TextView disPrice;
        public TextView cost;
        public ImageView repPicture;
        public ImageView imgPlat;

        public SearchHolder(View itemView) {
            super(itemView);
            swName = (TextView) itemView.findViewById(R.id.text_sw_name);
            disPrice = (TextView) itemView.findViewById(R.id.text_dis_price);
            cost = (TextView) itemView.findViewById(R.id.text_cost);
            repPicture = (ImageView) itemView.findViewById(R.id.img_rep_pic);
            imgPlat = (ImageView) itemView.findViewById(R.id.img_plat);
        }
    }

    public SearchAdapter (ArrayList<HotDeal> arrayList) {
        mHotDeal = arrayList;
    }

    //inflate the layout for your list
    @NotNull
    public SearchAdapter.SearchHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.hotdeal_list_relative, viewGroup, false);

        return new SearchAdapter.SearchHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchHolder holder, int position) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        HotDeal hotDeal = mHotDeal.get(position);

        String swName = hotDeal.getSWName();
        int disPrice = hotDeal.getDisPrice();
        int cost = hotDeal.getCost();
        String repPicture = hotDeal.getRepPicture();
        // 플랫폼 이미지는 나중에 구현하자.

        holder.swName.setText(swName);
        holder.cost.setText(cost);
        holder.disPrice.setText(disPrice);
        holder.repPicture.setImageResource(Integer.parseInt(repPicture));
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