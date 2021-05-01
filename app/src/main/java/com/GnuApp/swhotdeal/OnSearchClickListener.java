package com.GnuApp.swhotdeal;

import android.view.View;

import com.GnuApp.swhotdeal.adapter.SearchAdapter;

public interface OnSearchClickListener {
    public void OnItemClick(SearchAdapter.SearchHolder holder, View view, int position, String title);
}


