package com.example.yanjx.myapplication.activity;

import android.app.Activity;
import android.app.Person;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yanjx.myapplication.R;
import com.example.yanjx.myapplication.fragment.HomeFragment;
import com.example.yanjx.myapplication.fragment.SettingFragment;
import com.example.yanjx.myapplication.mode.Content;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.Event;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;

@ContentView(R.layout.activity_main)
public class MainActivity extends FragmentActivity implements View.OnClickListener{

    private FragmentManager fm;
    @ViewInject(R.id.btn_home)
    private Button bnt_home;
    @ViewInject(R.id.btn_setting)
    private Button bnt_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        Log.i("test",this.getClass() + " onCreate......");

        fm = getSupportFragmentManager();
        bnt_home.setOnClickListener(this);
        bnt_setting.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Log.i("test",v.toString());
        switch ((String) v.getTag()){
            case "home":
                FragmentTransaction ft = fm.beginTransaction();
                ft.replace(R.id.framelayout,new HomeFragment());
                ft.commit();
                break;
            case "setting":
                fm.beginTransaction().replace(R.id.framelayout,new SettingFragment()).commit();

                break;


        };


    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK) {
            Log.i("test","onKeyDown......");
            exitapp();
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }


    boolean isExit = false;
    private void exitapp() {
        if(!isExit) {
            isExit = true;
            Toast.makeText(this,"再按一次推出app",Toast.LENGTH_LONG).show();
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    isExit = false;
                }
            },2000);

        } else {
//            Intent intent = new Intent(Intent.ACTION_MAIN);
//            intent.addCategory(Intent.CATEGORY_HOME);
//            startActivity(intent);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        }

    }



}
