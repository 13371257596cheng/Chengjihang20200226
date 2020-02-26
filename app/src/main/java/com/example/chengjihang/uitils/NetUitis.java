package com.example.chengjihang.uitils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Handler;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetUitis {
    public static NetUitis netUitis=new NetUitis();

    private NetUitis(){

    }

    public static NetUitis getInstance() {
        return netUitis;
    }

    //判断有网无网
    public boolean isWork(Context context){
       ConnectivityManager cm= (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
       if (cm!=null){
           return true;
       }else {
           return false;
       }
    }

    //获取json文件
    public void getJson(final String path, final ICallWork iCallWork){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url=new URL(path);
                    HttpURLConnection conn= (HttpURLConnection) url.openConnection();
                    //请求方式
                    conn.setRequestMethod("GET");
                    //连接超时
                    conn.setConnectTimeout(5000);
                    //读取超时
                    conn.setReadTimeout(5000);
                    int responseCode = conn.getResponseCode();
                    if (responseCode==200){
                        InputStream inputStream=conn.getInputStream();
                        int len=0;
                        byte[] buffer=new byte[1024];
                        StringBuilder sb=new StringBuilder();
                        while ((len=inputStream.read(buffer))!=-1){
                            String s=new String(buffer,0,len);
                            sb.append(s);
                        }

                        final String json=sb.toString();
                        inputStream.close();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                if (iCallWork!=null){
                                    iCallWork.onSuccess(json);
                                }
                            }
                        });
                    }else {
                        if (iCallWork!=null){
                            iCallWork.onError("网络请求失败");
                        }
                    }
                } catch (final Exception e) {
                    e.printStackTrace();
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            if (iCallWork!=null){
                                iCallWork.onError(e.toString());
                            }
                        }
                    });
                }
            }
        }).start();
    }




//创建Handler
    Handler handler=new Handler();

    //创建判断接口
    public interface ICallWork{
        void onSuccess(String json);
        void onError(String msg);
    }


}
