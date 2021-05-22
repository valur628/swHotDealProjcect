package com.GnuApp.swhotdeal.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

// import com.GnuApp.swhotdeal.OnSearchClickListener;
import com.GnuApp.swhotdeal.R;
import com.GnuApp.swhotdeal.data.HotDeal;

import org.jetbrains.annotations.NotNull;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchHolder> implements OnSearchClickListener {

    ArrayList<HotDeal> mHotDeal = new ArrayList<>();
    Context context;
    OnSearchClickListener listener;

    public class SearchHolder extends RecyclerView.ViewHolder {
        public TextView TVswName;
        public TextView TVdisPrice;
        public TextView TVcost;
        public ImageView IVrepPicture;
        public ImageView IVimgPlat;

        public SearchHolder(View itemView, OnSearchClickListener listener) {
            super(itemView);
            this.TVswName = itemView.findViewById(R.id.text_sw_name);
            this.TVdisPrice = itemView.findViewById(R.id.text_dis_price);
            this.TVcost = itemView.findViewById(R.id.text_cost);
            this.IVrepPicture = itemView.findViewById(R.id.img_rep_pic);
            this.IVimgPlat = itemView.findViewById(R.id.img_plat);

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

        @SuppressLint("SetTextI18n")
        public void setItem(HotDeal hotDeal) {
            DecimalFormat df = new DecimalFormat("#.##");
            TVswName.setText(hotDeal.getSWName());
            Glide.with(itemView).load(hotDeal.getRepPicture()).into(IVrepPicture);

            String cost = df.format(hotDeal.getCost());
            String disPrice = df.format(hotDeal.getDisPrice());

            String currency = hotDeal.getCurrency();
            if ("KRW".equals(currency)) {
                TVdisPrice.setText("₩ "+cost);
                TVcost.setText("₩ "+cost);
            } else if ("USD".equals(currency)) {
                TVdisPrice.setText("$ "+disPrice);
                TVcost.setText("$ "+disPrice);
            }
            TVcost.setPaintFlags(TVcost.getPaintFlags()| Paint.STRIKE_THRU_TEXT_FLAG);

            String platName = hotDeal.getPlatName();
            if ("Steam".equals(platName)) {
                Glide.with(itemView).load(R.drawable.steam).into(IVimgPlat);
            } else if ("HumbleBundle".equals(platName)) {
                Glide.with(itemView).load(R.drawable.logo_humble_bundle).into(IVimgPlat);
            }
            else {
                Glide.with(itemView).load(R.drawable.img_esd_none).into(IVimgPlat);
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

    public void setItems(ArrayList<HotDeal> mHotDeal) {
        this.mHotDeal = mHotDeal;
    }

    public HotDeal getItem(int position) {
        return mHotDeal.get(position);
    }

    public void setItem(int position, HotDeal item){
        mHotDeal.set(position, item);
    }

    public void setOnItemClickListener(OnSearchClickListener listener) {
        this.listener = listener;
    }

//    public Filter getFilter() {
//        if (valueFilter == null) {
//            valueFilter = new ValueFilter();
//        }
//        return valueFilter;
//    }
}