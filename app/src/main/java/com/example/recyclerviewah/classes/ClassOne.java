package com.example.recyclerviewah.classes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.recyclerviewah.R;

import java.util.HashMap;
import java.util.Map;

public class ClassOne extends AppCompatActivity {

    String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry",
            "WebOS","Ubuntu","Windows7","Max OS X"};

    String[] sizeArray = {"12","17","32","62","11","16","24","1"};

    private Map<String, Map<String, String>> mMenuView = new HashMap<String, Map<String, String>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_one);

        ArrayAdapter adapter = new ArrayAdapter<String>(this,R.layout.cusltom_list, R.id.label ,mobileArray);
//        ArrayAdapter2 adapter2 = new ArrayAdapter<String>(this,R.layout.cusltom_list, R.id.label2, sizeArray);

//        Adapter mAdapter = new NewAdapter(getActivity().getApplicationContext(), mMenuView);
//        mListView.setAdapter(mAdapter);

        ListView listView = (ListView) findViewById(R.id.lisViewId);
        listView.setAdapter(adapter);
//        listView.setAdapter(adapter2);


    }
}
