package com.example.yanjx.myapplication.activity;

import android.app.Activity;
import android.app.Person;
import android.os.Bundle;
import android.util.Log;
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
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView listview = (ListView) findViewById(R.id.lv);
        RequestParams params = new RequestParams("http://hiwbs101083.jsp.jspee.com.cn/ajaxServlet");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Log.i("test","result: "+result);
                Gson gson=new Gson();
                List<Content> conList = gson.fromJson(result,new TypeToken<List<Content>>(){}.getType());
                listview.setAdapter(new MyAdapter(conList));

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
            if(convertView!=null){
                list_item = convertView;
            }else{
                list_item = View.inflate(MainActivity.this,R.layout.list_item,null);
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
