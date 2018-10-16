package com.example.yanjx.myapplication.fragment;


import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.yanjx.myapplication.R;
import com.example.yanjx.myapplication.activity.MainActivity;
import com.example.yanjx.myapplication.activity.MainActivity_BAK;
import com.example.yanjx.myapplication.mode.Content;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.image.ImageOptions;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */

@ContentView(R.layout.fragment_home)
public class HomeFragment extends Fragment {


    @ViewInject(R.id.lv)
    private ListView listView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i("test","onCreateView......");

        return x.view().inject(this,inflater,container);
//        return inflater.inflate(R.layout.fragment_home, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Log.i("test","onViewCreated......");

        RequestParams params = new RequestParams("http://hiwbs101083.jsp.jspee.com.cn/ajaxServlet");
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
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

    private class MyAdapter extends BaseAdapter {

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
                list_item = View.inflate(getActivity(),R.layout.list_item,null);
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
