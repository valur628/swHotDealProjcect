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

import com.GnuApp.swhotdeal.adapter.OnSearchClickListener;
import com.bumptech.glide.Glide;

// import com.GnuApp.swhotdeal.OnSearchClickListener;
import com.GnuApp.swhotdeal.R;
import com.GnuApp.swhotdeal.data.HotDeal;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchHolder> implements OnSearchClickListener {

    ArrayList<HotDeal> mHotDeal = new ArrayList<HotDeal>();
    Context context;
    OnSearchClickListener listener;

    public class SearchHolder extends RecyclerView.ViewHolder {

        public TextView swName;
        public TextView disPrice;
        public TextView cost;
        public ImageView repPicture;
        public ImageView imgPlat;

        public SearchHolder(View itemView, OnSearchClickListener listener) {
            super(itemView);
            this.swName = itemView.findViewById(R.id.text_sw_name);
            this.disPrice = itemView.findViewById(R.id.text_dis_price);
            this.cost = itemView.findViewById(R.id.text_cost);
            this.repPicture = itemView.findViewById(R.id.img_rep_pic);
            this.imgPlat = itemView.findViewById(R.id.img_plat);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();

//                    for(int i = 0 ; i < mHotDeal.size() ; i++)
//                        Log.d("search_holder", mHotDeal.get(i).getSWName());
//
//                    String title = mHotDeal.get(position).getSWName();

                    if(listener != null){
                        listener.OnItemClick(this, view, position);
                    }
                }
            });
        }

        public void setItem(HotDeal hotDeal) {
            swName.setText(hotDeal.getSWName());
            disPrice.setText(hotDeal.getDisPrice());
            cost.setText(hotDeal.getCost());
            Glide.with(itemView).load(hotDeal.getRepPicture()).into(repPicture);

            String platName = hotDeal.getPlatName();
//            Log.d("sss" , String.valueOf(platName.equals("Steam"))); 에러 발생 Yoda conditions
            Log.d("platName" , hotDeal.getSWName() + ": " + String.valueOf("Steam".equals(platName)));

            if ("Steam".equals(platName)) {
                Glide.with(itemView).load(R.drawable.steam).into(imgPlat);
            } else if ("HumbleBundle".equals(platName)) {
                Glide.with(itemView).load(R.drawable.logo_humble_bundle).into(imgPlat);
            }
            else {
                Glide.with(itemView).load(R.drawable.img_esd_none).into(imgPlat);
            }
        }
    }

    //inflate the layout for your list
    @NotNull
    public SearchHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
//        // Create a new view, which defines the UI of the list item
//        View view = LayoutInflater.from(viewGroup.getContext())
//                .inflate(R.layout.hotdeal_list_relative, viewGroup, false);
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.hotdeal_list, viewGroup, false);

        return new SearchHolder(itemView, this); // inline
    }

    @Override
    public void onBindViewHolder(@NonNull SearchHolder holder, int position) {
        HotDeal hotDeal = mHotDeal.get(position);
        holder.setItem(hotDeal);
    }

    @Override
    public void onAttachedToRecyclerView(@NotNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    // list 크기 반환
    public int getItemCount() {
        return mHotDeal.size();
    }

    @Override
    public void OnItemClick(View.OnClickListener onClickListener, View view, int position) {
        if (listener != null) {
            listener.OnItemClick(onClickListener, view, position);
        }
    }

    public void addItem(HotDeal item) {
        mHotDeal.add(item);
    }
    // 리사이클러 뷰에 아이템 추가

    public void addTemp() {
        HotDeal item = new HotDeal();
        Log.d("HotDeal()", "addTemp: 생성자");
        mHotDeal.add(item);
    }

    public void addDBData() {
        HotDeal item = new HotDeal();
        Log.d("HotDeal()", "addTemp: 생성자");
        mHotDeal.add(item);
    }

    public void setItems(ArrayList<HotDeal> mHotDeal) {
        this.mHotDeal = mHotDeal;
    }

    public HotDeal getItem(int position) {
        return mHotDeal.get(position);
    }

    public void setItems(int position, HotDeal item){
        mHotDeal.set(position, item);
    }

    public void setOnItemClickListener(OnSearchClickListener listener) {
        this.listener = listener;
    }

//    public void getPlatform(ArrayList<HotDeal> mHotDeal) {
//        this
//    }
}