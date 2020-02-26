package com.example.chengjihang.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //获取界面布局
        setContentView(getLayoutID());
        //初始化控件
        getinitView();
        //获取数据
        getinitData();

    }
    //获取界面布局
    protected abstract int getLayoutID();
    //初始化控件
    protected abstract void getinitView();
    //获取数据
    protected abstract void getinitData();
}
