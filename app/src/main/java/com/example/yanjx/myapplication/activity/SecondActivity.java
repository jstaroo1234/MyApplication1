package com.example.yanjx.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;


import com.example.yanjx.myapplication.R;

import org.xutils.view.annotation.Event;
import org.xutils.x;

public class SecondActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        x.view().inject(this);
        Log.i("test",this.getClass() + " onCreate......");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i("test",this.getClass() + " onStart......");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("test",this.getClass() + " onResume......");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i("test",this.getClass() + " onPause......");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i("test",this.getClass() + " onStop......");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("test",this.getClass() + " onDestroy......");
    }

    @Event(value = R.id.btn_set)
    private void openMain(View view){
        Log.i("test",view.toString());
        Intent intent=new Intent();
//        intent.setAction(Intent.ACTION_MAIN);
//        intent.addCategory(Intent.CATEGORY_LAUNCHER);
        intent.setClass(this,MainActivity.class);
        startActivity(intent);

    }
}
