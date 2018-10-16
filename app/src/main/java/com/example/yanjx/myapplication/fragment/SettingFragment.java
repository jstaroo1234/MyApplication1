package com.example.yanjx.myapplication.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yanjx.myapplication.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;

/**
 * A simple {@link Fragment} subclass.
 */

@ContentView(R.layout.fragment_setting)
public class SettingFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return x.view().inject(this,inflater,container);
//        return inflater.inflate(R.layout.fragment_home, container, false);
    }

}
