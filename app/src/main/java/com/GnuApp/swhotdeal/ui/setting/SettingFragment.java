package com.GnuApp.swhotdeal.ui.setting;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.GnuApp.swhotdeal.Dev_Info;
import com.GnuApp.swhotdeal.MainActivity;
import com.GnuApp.swhotdeal.R;

public class SettingFragment extends Fragment {

    public SettingFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_setting, container, false);
        String[] settingList = {"개발자 정보"};
        ListView listView = (ListView) view.findViewById(R.id.hotdeal_list);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1, settingList);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                adapterView.getItemIdAtPosition(position);
                Intent intent = new Intent(getActivity(), Dev_Info.class);
                startActivity(intent);
            }
        });

        return view;
    }

//    @Override
//    public void onListItemClick(ListView l, View v, int position, long id){
//        String strText = (String) l.getItemAtPosition(position);
//        Log.d("Fragment: ", position +  ": " + strText);
//        Toast.makeText(this.getContext(), "클릭: " + position + " " + strText, Toast.LENGTH_SHORT).show();
//    }
}

