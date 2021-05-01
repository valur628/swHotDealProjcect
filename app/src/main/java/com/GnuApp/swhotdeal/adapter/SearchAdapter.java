package com.GnuApp.swhotdeal.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import com.GnuApp.swhotdeal.OnSearchClickListener;
import com.GnuApp.swhotdeal.R;
import com.GnuApp.swhotdeal.data.HotDeal;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchHolder> {

    private static ArrayList<HotDeal> mHotDeal;
    private Context context;
    OnSearchClickListener listener;

    public SearchAdapter (ArrayList<HotDeal> arrayList) {
        mHotDeal = arrayList;
        // context = con;
    }

    public class SearchHolder extends RecyclerView.ViewHolder {

        public TextView swName;
        public TextView disPrice;
        public TextView cost;
        public ImageView repPicture;
        public ImageView imgPlat;

        public SearchHolder(View itemView) {
            super(itemView);
            this.swName = (TextView) itemView.findViewById(R.id.text_sw_name);
            this.disPrice = (TextView) itemView.findViewById(R.id.text_dis_price);
            this.cost = (TextView) itemView.findViewById(R.id.text_cost);
            this.repPicture = (ImageView) itemView.findViewById(R.id.img_rep_pic);
            this.imgPlat = (ImageView) itemView.findViewById(R.id.img_plat);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    for(int i = 0 ; i < mHotDeal.size() ; i++)
                        Log.d("search_holder", mHotDeal.get(i).getSWName());

                    String title = mHotDeal.get(position).getSWName();
                    if(listener != null){
                        listener.OnItemClick(SearchHolder.this, view, position, title);
                    }
                }
            });
        }
    }

    //inflate the layout for your list
    @NotNull
    public SearchHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view, which defines the UI of the list item
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.hotdeal_list_relative, viewGroup, false);

        return new SearchHolder(view); // inline
    }

    @Override
    public void onBindViewHolder(@NonNull SearchHolder holder, int position) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element

        String repPicture = mHotDeal.get(position).getRepPicture();
        String swName = mHotDeal.get(position).getSWName();
        int disPrice = mHotDeal.get(position).getDisPrice();
        int cost = mHotDeal.get(position).getCost();

        Glide.with(holder.itemView).load(repPicture).into(holder.repPicture);
        // 파이어베이스에서 텍스트, 이미지 받아올 것.

        holder.swName.setText(swName);
        holder.cost.setText(cost);
        holder.disPrice.setText(disPrice);
        // holder.repPicture.setImageResource(Integer.parseInt(repPicture));
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