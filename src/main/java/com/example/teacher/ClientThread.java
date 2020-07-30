package com.example.teacher;
import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketTimeoutException;
public class ClientThread implements Runnable {
    private Socket s;
    public String content = null;
    String id;
    private Handler handler;
    public Handler revHandler;
    BufferedReader br = null;
    OutputStream os = null;
    public ClientThread(Handler handler) {
        this.handler = handler;
    }
    @SuppressLint("HandlerLeak")
    public void run() {
        try {
            s = new Socket("192.168.43.217", 8888);
            br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            os = s.getOutputStream();
            new Thread() {
                @Override
                public void run() {
                    try {
                        while ((content = br.readLine()) != null) {
                            Message msg = new Message();
                            msg.obj = content;
                            String[] strarray = content.split(" ");
                            for (int i = 0; i < strarray.length; i++)
                                System.out.println(strarray[i] + " ");
                            System.out.println(content);
                            String command = strarray[2];
                            if (command.equals("Y")) {
                                msg.what = 0x123;
                                msg.obj = content;
                                if(handler != null) {
                                    handler.sendMessage(msg);
                                }
                            } else if (command.equals("YR")){//注册成功
                                msg.what = 0x1234;
                                msg.obj = content;
                                if(handler != null) {
                                    handler.sendMessage(msg);
                                }
                            }else if (command.equals("QS")){//签到成功
                                msg.what = 0x12;
                                msg.obj = content;
                                if(handler != null) {
                                    handler.sendMessage(msg);
                                }
                            }else if (command.equals("SQ")){//签到反馈成功
                                msg.what = 0x12345;
                                msg.obj = content;
                                System.out.print(content);
                                if(handler != null) {
                                    handler.sendMessage(msg);
                                }
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
            }.start();
            Looper.prepare();
            revHandler = new Handler() {
                @Override
                public void handleMessage(Message msg) {
                    if (msg.what == 0x345) {
                        String ip = s.getInetAddress().getHostAddress();
                        try {
                            os.write((msg.obj.toString()+" "+ip+ "\r\n").getBytes("utf-8"));
                        } catch (Exception
                                e) {
                            e.printStackTrace();
                        }
                    }
                }
            };
            Looper.loop();
        } catch (SocketTimeoutException e1) {
            System.out.println("网络连接超时！！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

