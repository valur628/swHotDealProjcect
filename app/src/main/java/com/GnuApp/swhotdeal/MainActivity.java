package com.GnuApp.swhotdeal;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.GnuApp.swhotdeal.ui.hotdeal.HotdealFragment;
import com.GnuApp.swhotdeal.ui.hotdeal.MenuFragment;
import com.GnuApp.swhotdeal.ui.search.SearchFragment;
import com.GnuApp.swhotdeal.ui.setting.SettingFragment;
import com.GnuApp.swhotdeal.SearchActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_MENU = 101;

    Activity activity;
    Button searchButton;
    ArrayList<HotDeal> hotDealArrayList;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;

    static final String[] HOTDEAL_LIST = new String[50];

    int loadNumber;
    TextView swName;
    TextView devName;
    Date disPeriod;
    TextView currency;
    int cost;
    int disPrice;
    int disRate;
    TextView platAddress;
    TextView platName;
    TextView repPicture;
    TextView othPicture;
    int keyNumber;
    TextView keySetting;
    TextView mainTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String TAG = "MainActivity";
        String beforeString = "";

        activity = this;
        hotDealArrayList = new ArrayList<>();
        layoutManager = new LinearLayoutManager(activity);
        recyclerView = findViewById(R.id.hotdeal_recycler);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(layoutManager);
        // recyclerView를 만들긴 했는데 어떻게 다룰지 안 만듦.
        // recyclerView를 다루기 위한 어댑터를 만들자!

        searchButton = findViewById(R.id.button_search);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivityForResult(intent, REQUEST_CODE_MENU);
            }
        });
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavi);

        getSupportFragmentManager().beginTransaction().add(R.id.main_frame,new HotdealFragment()).commit();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch(menuItem.getItemId()){
                    case R.id.navigation_hotdeal:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new HotdealFragment()).commit();
                        break;
                    case R.id.navigation_search:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new SearchFragment()).commit();
                        break;
                    case R.id.navigation_setting:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new SettingFragment()).commit();
                        break;
                    case R.id.navigation_menu:
                        getSupportFragmentManager().beginTransaction().replace(R.id.main_frame, new MenuFragment()).commit();
                        break;
                }
                return true;
            }
        });
    }
}