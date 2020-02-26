package com.example.chengjihang.activity;

import android.util.Log;
import android.widget.ListView;

import com.example.chengjihang.R;
import com.example.chengjihang.adapter.Myadapter;
import com.example.chengjihang.base.BaseActivity;
import com.example.chengjihang.bean.BeanInfo;
import com.example.chengjihang.uitils.NetUitis;
import com.google.gson.Gson;

import java.util.List;

public class MainActivity extends BaseActivity {


    private ListView lv;
    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void getinitView() {
        lv = findViewById(R.id.lv);
    }

    @Override
    protected void getinitData() {
        String path="http://blog.zhaoliang5156.cn/api/news/news_data.json";
        Log.i("xxx",path);
        NetUitis.getInstance().getJson(path, new NetUitis.ICallWork() {
            @Override
            public void onSuccess(String json) {
                Log.i("xxx",json);
                Gson gson=new Gson();
                BeanInfo beanInfo=gson.fromJson(json,BeanInfo.class);
                List<BeanInfo.ResultsBean> results=beanInfo.getResults();

//                Myadapter myadapter=new Myadapter(this,results);

            }

            @Override
            public void onError(String msg) {

            }
        });


    }
}
