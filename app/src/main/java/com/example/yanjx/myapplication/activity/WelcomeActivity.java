package com.example.yanjx.myapplication.activity;

import android.app.Activity;
import android.os.Bundle;

import com.example.yanjx.myapplication.R;

import org.xutils.view.annotation.ContentView;
import org.xutils.x;


@ContentView(R.layout.activity_welcome)
public class WelcomeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_welcome);
        x.view().inject(this);
    }
}
