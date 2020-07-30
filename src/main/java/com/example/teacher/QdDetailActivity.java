package com.example.teacher;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class QdDetailActivity extends Activity {
    String[] ids ={"0","1","2","3","4","5","6","7"};//学号
    String names[]={"tim","tom","tony","brown","jack","jane","black","green"};
    String[] infos = {"y"};//是否签到
    ListView listView;
    int[] imageids = new int[]{R.drawable.bac};//签到时间
    List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
    Handler handler;
    String id;
    String name;
    ClientThread clientThread;
    String time;
    String content;
    @SuppressLint("HandlerLeak")
    String[] strarray;
    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qd_detail);
        new Thread(clientThread).start();
        clientThread = new ClientThread(handler);
        listView = findViewById(R.id.qiandaoliebiao);
        handler = new Handler() {

            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0x12345) {
                }else{
                    Toast.makeText(QdDetailActivity.this,"error",Toast.LENGTH_LONG).show();
                }
            }

        };
        Intent i1 = getIntent();
        content=i1.getStringExtra("content");
        strarray = content.split(" ");
        for (int i = 0; i < strarray.length; i++) {
            System.out.println(strarray[i] + " ");
        }
        id = strarray[0];
        insert(ids,id);
        name = strarray[1];
        insert(names,name);
        view(id,name);
    }
    private void view(String id, String name) {
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("MM-dd hh:mm:ss");// 设置日期时间格式
        System.out.println(df.format(date));
        time = df.format(date);
        System.out.println(time);
        System.out.print(name + "签到成功！");
        for (int i = 0; i < names.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id",ids[i]);
            map.put("name",names[i]);
            map.put("y","y");//key,value
            map.put("time",time);
            listItems.add(map);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems,
                R.layout.weiqinddaorenshu, new String[]{"id", "name", "y", "time"},
                new int[]{R.id.xuehao, R.id.name, R.id.info, R.id.img});
        listView.setAdapter(simpleAdapter);
    }
    private static String[] insert(String[] arr, String str) {
        int size = arr.length;  //获取数组长度
        String[] tmp = new String[size + 1];  //新建临时字符串数组，在原来基础上长度加一
        for (int i = 0; i < size; i++){  //先遍历将原来的字符串数组数据添加到临时字符串数组
            tmp[i] = arr[i];
        }
        tmp[size] = str;  //在最后添加上需要追加的数据
        return tmp;  //返回拼接完成的字符串数组
    }
}

