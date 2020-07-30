package com.example.teacher;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    private TextView register;
    private TextView forgetPassword;
    private EditText school;
    private EditText tch_ID;
    private EditText login_psd;
    private EditText edt_name;
    private CheckBox remeber;
    private Button button1;
    public String schools;
    public String tchid;//用户名
    public String loginpsd;//密码
    public String name;
    SharedPreferences psdPreferences;
    SharedPreferences userPreferences;
    SharedPreferences.Editor psdEditor;
    SharedPreferences.Editor tchidEditor;
    String command;
    String Attribute;
    Handler handler;
    private boolean bindService;
    private MyService.Work work;
    private MyService myService;
    ClientThread clientThread;
    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        register = findViewById(R.id.register);
        forgetPassword = findViewById(R.id.forget_password);
        school = findViewById(R.id.edt_school);
        tch_ID = findViewById(R.id.edt_tch_id);
        login_psd = findViewById(R.id.edt_login_psd);
        remeber = findViewById(R.id.remeber);
        edt_name = findViewById(R.id.edt_tch_l_name);
        button1 = (Button) findViewById(R.id.button1);
        clientThread = new ClientThread(handler);
        new Thread(clientThread).start();//
        Intent i = new Intent(this,APPMainActivity.class);
        startActivity(i);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        schools = school.getText().toString();
                        tchid = tch_ID.getText().toString();
                        name = edt_name.getText().toString();
                        loginpsd = login_psd.getText().toString();
                        schools = school.getText().toString();
                        command = "登录";
                        Attribute = "教师";
                        Message msg = new Message();
                        msg.what = 0x345;
                        msg.obj = name + " " + tchid + " " + loginpsd + " " + command + " " + Attribute;
                        clientThread.revHandler.sendMessage(msg);
                    }


        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(i);
            }
        });
        forgetPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ForgetPasswordActivity.class);
                startActivity(i);
            }
        });
        remeber.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                psdPreferences = getSharedPreferences("login_psd", Context.MODE_PRIVATE);
                userPreferences = getSharedPreferences("tch_ID", Context.MODE_PRIVATE);
                psdEditor = psdPreferences.edit();
                tchidEditor = userPreferences.edit();
                tchid = psdPreferences.getString("login_psd", null);
                loginpsd = userPreferences.getString("tch_ID", null);
                if (isChecked) {
                    login_psd.setText(loginpsd);
                    tch_ID.setText(tchid);
                }
                psdEditor.putString("login_psd", loginpsd);
                tchidEditor.putString("tch_ID", tchid);
            }
        });
    }
}
