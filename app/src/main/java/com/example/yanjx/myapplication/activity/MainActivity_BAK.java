package com.example.yanjx.myapplication.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yanjx.myapplication.R;
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

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

@ContentView(R.layout.activity_main)
public class MainActivity_BAK extends Activity {

    @ViewInject(R.id.lv)
    private ListView listView = null;


    @Event(value = R.id.btn_setting)
    private void openSecond(View view){
        Intent intent = new Intent();
        intent.setClass(this,SecondActivity.class);
        startActivity(intent);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        x.view().inject(this);
        Log.i("test",this.getClass() + " onCreate......");

//        btnSet.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Log.i("test","setOnClickListener.....");
//            }
//        });



//        setContentView(R.layout.activity_main);
//        final ListView listview = (ListView) findViewById(R.id.lv);
        RequestParams params = new RequestParams("http://hiwbs101083.jsp.jspee.com.cn/ajaxServlet");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("test","result: "+result);
                Gson gson=new Gson();
                List<Content> conList = gson.fromJson(result,new TypeToken<List<Content>>(){}.getType());
                listView.setAdapter(new MyAdapter(conList));

            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                Toast.makeText(x.app(),ex.getMessage(),Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(CancelledException cex) {
                Toast.makeText(x.app(),"cancel",Toast.LENGTH_LONG).show();

            }

            @Override
            public void onFinished() {

            }
        });



    }




    private class MyAdapter extends BaseAdapter{

        private List<Content> conList;

        public MyAdapter(List<Content> conList){
            this.conList = conList;
        }


        @Override
        public int getCount() {
            return conList.size();
        }

        @Override
        public Object getItem(int position) {
            Log.i("test","position:"+position);
            return conList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Log.i("test","position: "+position+",View: "+convertView+",ViewGroup: "+parent);

            Content content= (Content) getItem(position);

            View list_item = null;
            if(convertView != null){
                list_item = convertView;
            }else{
                list_item = View.inflate(MainActivity_BAK.this,R.layout.list_item,null);
            }

            ImageView img_url = (ImageView)list_item.findViewById(R.id.img_url);
            TextView txt_titel = (TextView)list_item.findViewById(R.id.txt_titel);
            TextView txt_date = (TextView)list_item.findViewById(R.id.txt_date);
//            txt_url.setText(content.getImgUrl());
            ImageOptions options = new ImageOptions.Builder().build();
            x.image().bind(img_url,content.getImgUrl(), options);
            txt_titel.setText(content.getTitel());
            txt_date.setText(content.getDate());
            return list_item;
        }
    }


}
