package com.example.teacher;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.SystemClock;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;
public class MyService extends Service {
    private Socket s;
    public String content = null;
    String id;
    public Handler revHandler;
    BufferedReader br = null;
    OutputStream os = null;
    private Work work;
    private WeakReference<MyService> myService = new WeakReference<>(MyService.this);
    private static final String TAG = "MyService";
    public MyService() {
        threadFlag = true;
        workThread.start();
    }
    private Work getWork() {
        if (work == null) {
            work = new Work();
        }
        return work;
    }
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        return getWork();
    }
    @Override
    public void onDestroy() {
        threadFlag = false;
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
    public class Work extends Binder {
        public void startWork(String command) {
            //开始工作
            String ip = s.getInetAddress().getHostAddress();
            try {
                os.write((command+" "+ip+ "\r\n").getBytes("utf-8"));
            } catch (Exception
                    e) {
                e.printStackTrace();
            }
            flag = true;
        }
        public void stopWork() {
            flag = false;
        }
        public MyService getMyService() {
            return myService.get();
        }
    }
    private Handler handler = new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0x123456:
                    String message = msg.obj.toString();
                    //将信息反馈给接口
                    if (callBacks != null && callBacks.size() > 0) {
                        for (CallBack callBack :
                                callBacks) {
                            callBack.postMessage(message);
                        }
                    }
            }
            super.handleMessage(msg);
        }
    };
    private boolean flag = true;
    private boolean threadFlag = true;
    private int i = 0;
    private Thread workThread = new Thread() {
        @Override
        public void run() {
            try {
                    s = new Socket("192.168.43.217", 8888);
                    br = new BufferedReader(new InputStreamReader(s.getInputStream()));
                    os = s.getOutputStream();
                while ((content = br.readLine()) != null) {
                    Message msg = new Message();
                    msg.obj = content;
                    String[] strarray = content.split(" ");
                    for (int i = 0; i < strarray.length; i++)
                        System.out.println(strarray[i] + " ");
                    System.out.println(content);
                    String command = strarray[2];
                    if (command.equals("Y")) {//登录成功
                        //if psd=s psd
                        msg.what = 0x123;
                        msg.obj = content;
                        handler.sendMessage(msg);
                    } else if (command.equals("YR")){//注册成功
                        msg.what = 0x1234;
                        msg.obj = content;
                        handler.sendMessage(msg);
                    }else if (command.equals("QS")){//签到成功
                        msg.what = 0x12;
                        msg.obj = content;
                        handler.sendMessage(msg);
                    }else if (command.equals("SQY")){//签到反馈成功
                        msg.what = 0x12345;
                        msg.obj = content;
                        System.out.print(content);
                        handler.sendMessage(msg);
                    }else if(command.equals("CXDTQK")){
                        msg.what = 0x123456;
                        msg.obj = content;
                        if(handler != null){
                            handler.sendMessage(msg);
                        }
                    }
                    else{
                        msg.what = 0x000;
                        msg.obj = content;
                        handler.sendMessage(msg);
                    }
                }
            } catch (IOException
                    e) {
                e.printStackTrace();
            }
        }
    };
    public interface CallBack {
        void postMessage(String message);
    }
    private List<CallBack> callBacks = new LinkedList<>();
    public void registerCallBack(CallBack callBack) {
        if (callBacks != null) {
            callBacks.add(callBack);
        }
    }
    public boolean unRegisterCallBack(CallBack callBack) {
        if (callBacks != null && callBacks.contains(callBack)) {
            return callBacks.remove(callBack);
        }
        return false;
    }
}
