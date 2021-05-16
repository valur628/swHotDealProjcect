package com.GnuApp.swhotdeal;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Dev_Info extends Activity {

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dev__info);

        mTextView = (TextView) findViewById(R.id.text);
    }
}