package com.example.yanjx.myapplication;

import android.app.Activity;
import android.app.Person;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        List<Person> perList = new ArrayList<Person>();
//        for(int i=1,i<=20;i++){
//            perList.add(new Person("张三"+i,"1831234567"+i,5000.00+i*20));
//        }

        ListView listview = (ListView) findViewById(R.id.lv);

    }


}
