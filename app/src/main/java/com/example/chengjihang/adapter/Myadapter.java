package com.example.chengjihang.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chengjihang.R;
import com.example.chengjihang.bean.BeanInfo;
import com.example.chengjihang.uitils.NetUitis;

import java.util.ArrayList;
import java.util.List;

public class Myadapter extends BaseAdapter {
    Context context;
    List<BeanInfo.ResultsBean> results=new ArrayList<>();

    public Myadapter(NetUitis.ICallWork context, List<BeanInfo.ResultsBean> results) {
//        this.context= (Context) context;
        this.results=results;
    }

    @Override
    public int getCount() {
        return results.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Shop shop=null;
        if (view==null){
            shop=new Shop();
            view=View.inflate(context, R.layout.item,null);
            shop.imag=view.findViewById(R.id.iv);
            shop.tittle=view.findViewById(R.id.text);
            view.setTag(shop);

        }else {
            shop= (Shop) view.getTag();
        }

        BeanInfo.ResultsBean bannerBean=results.get(i);


        return view;
    }

    class Shop{
        ImageView imag;
        TextView tittle;

    }


}
