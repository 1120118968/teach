package com.example.teacher;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
public class StartRegisterActivity extends Activity {
    private Button btn1;//发起普通签到
    private Button btn2;
    private Button btn3;
    private Button btn4;
    Handler handler;
    ClientThread clientThread;
    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_register);
        new Thread(clientThread).start();//①android studio ctrl+alt+l 规范化文本格式 ECLIPSE CTRL+SHIFT+F
        handler = new Handler()//①
        {
            @Override
            public void
            handleMessage(Message msg) {
                if (msg.what == 0x12) {
                    Toast.makeText(StartRegisterActivity.this,"发起签到成功！",Toast.LENGTH_SHORT).show();
              } else if(msg.what == 0x12345){
                    String content =msg.obj.toString();
                    Toast.makeText(StartRegisterActivity.this,"收到服务消息，即将跳转至详情页！",Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(StartRegisterActivity.this,QdDetailActivity.class);
                    i.putExtra("content",content);
                    startActivity(i);
                }else if (msg.what == 0x000){
                    Toast.makeText(StartRegisterActivity.this,"失败！",Toast.LENGTH_SHORT).show();
                }
            }
        };
        btn1=(Button) findViewById(R.id.btn1);
        btn2=(Button)findViewById(R.id.btn2);
        btn3=(Button)findViewById(R.id.btn3);
        btn4=(Button)findViewById(R.id.btn4);
        clientThread = new ClientThread(handler);
        new Thread(clientThread).start();//启动签到线程
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String command = "签到";
                String Attribute = "教师";
                Message msg = new Message();
                msg.what = 0x345;
                Intent i =getIntent();//接收登录页面的信息
                String name = i.getStringExtra("name");
                String tchid = i.getStringExtra("tchid");
                String loginpsd = i.getStringExtra("psd");
                msg.obj = name + " " + tchid + " " + loginpsd + " " + command + " " + Attribute;
                clientThread.revHandler.sendMessage(msg);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent2 = new Intent(StartRegisterActivity.this,Main3Activityshuziqiandao.class);
                startActivity(intent2);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(StartRegisterActivity.this,Main4ActivityGPSqiandao.class);
                startActivity(intent3);
            }
        });
        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4 = new Intent(StartRegisterActivity.this,Main4Activityerweimaqiandao.class);
                startActivity(intent4);
            }
        });
    }
}
