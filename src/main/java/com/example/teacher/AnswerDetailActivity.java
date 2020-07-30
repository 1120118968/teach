package com.example.teacher;
import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class AnswerDetailActivity extends AppCompatActivity {
    String nam[]={"tim","tom","tony","brown","jack","jane","black","green"};
    String[] ids ={"0","1","2","3","4","5","6","7"};//学号
    String name;
    String id;
    String psd;
    String tzs;
    String tjs;
    String tflag;
    TextView t_xid;
    TextView t_name;
    TextView t_zs;
    TextView t_js;
    TextView t_id;
    TextView t_flag;
    ListView listView;
    List<Map<String, Object>> listItems = new ArrayList<Map<String, Object>>();
    Handler handler;
    ClientThread clientThread;
    String content;
    Button search;
    private boolean bindService;
    private MyService.Work work;
    private MyService myService;
    String xinxi;
    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_detail);
        clientThread = new ClientThread(handler);
        new Thread(clientThread).start();//①
        bindService();
        t_xid = findViewById(R.id.xid);
        t_zs = findViewById(R.id.xzs);
        t_id = findViewById(R.id.xtid);
        t_flag = findViewById(R.id.xflag);
        t_js = findViewById(R.id.xjs);
        t_name = findViewById(R.id.xname);
        listView = findViewById(R.id.listview);
        search = findViewById(R.id.search);
        Intent i = getIntent();
        name = i.getStringExtra("name");
        id = i.getStringExtra("tchid");
        psd = i.getStringExtra("loginpsd");
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Command = "查询答题情况";//写入题目信息
                String Attribute = "教师";
                Message msg = new Message();
                msg.what = 0x345;
                String content ;
                content = id + " " + name + " " + psd +" "+ Command +" " + Attribute ;
                work.startWork(content);
            }
        });
    }
    private void bindService() {
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
        bindService = bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
    }
    private ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            work = (MyService.Work) iBinder;
            myService = work.getMyService();
            myService.registerCallBack(callBack);
        }
        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            if (myService != null) {
                myService.unRegisterCallBack(callBack);
            }
        }
    };
    private MyService.CallBack callBack = new MyService.CallBack() {
        @Override
        public void postMessage(String message) {
            //tv_text.setText("service do result-----> " + message);
            System.out.println(message);//收到服务转发的消息
            xinxi = message;
            view(id,name,xinxi);
        }
    };
    public void unBindService() {
        if (bindService && serviceConnection != null) {
            unbindService(serviceConnection);
        }
    }
    @Override
    protected void onDestroy() {
        unBindService();
        super.onDestroy();
    }
    private void view(String id, String name,String xinxi) {
        System.out.print(xinxi + "收到信息");
        String[] names = xinxi.split(",");
        tzs = names[0];
        tjs = names[2];
        String tid = names[4];
        String tflag = names[3];
        t_name.setText(name);
        t_id.setText(id);
        t_xid.setText(tid);
        t_flag.setText(tflag);
        t_js.setText(tjs);
        t_zs.setText(tzs);
        for (int i = 0; i < names.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("id",ids[i]);
            map.put("name",nam[i]);
            map.put("tzs",tzs);//key,value
            map.put("tjs",tjs);
            map.put("tid",tid);
            map.put("tflag",tflag);
            listItems.add(map);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems,
                R.layout.datiqingkuang, new String[]{"tjs", "tzs", "name","tflag","id","tid"},
                new int[]{R.id.xuehao, R.id.name, R.id.tzs, R.id.tjs,R.id.tid,R.id.tflag});
        listView.setAdapter(simpleAdapter);
    }
}
