package com.example.teacher;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class RegisterActivity extends Activity {
    private Button enter_register;
    private EditText edt_tch_id;
    private EditText edt_psd;
    private EditText edt_enter_psd;
    private EditText edt_course;
    private EditText edt_name;
    private String tch_id;
    private String psd;
    private String enter_psd;//确认密码
    private String course;//课程名
    private String name;
    Handler handler;
    ClientThread clientThread;
    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        enter_register = (Button)this.findViewById(R.id.enter_register);
        edt_tch_id = (EditText)this.findViewById(R.id.edt_tchr_id);
        edt_psd = (EditText)this.findViewById(R.id.edt_r_psd);
        edt_enter_psd = (EditText)this.findViewById(R.id.edt_enter_psd);
        edt_course = (EditText)this.findViewById(R.id.edt_course);
        edt_name = findViewById(R.id.edt_tchr_name);
        clientThread = new ClientThread(handler);
        new Thread(clientThread).start();//①
        handler = new Handler()//①
        {

            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 0x1234) {
                    Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                    Toast.makeText(RegisterActivity.this, "注册成功，请登录", Toast.LENGTH_SHORT).show();
                    startActivity(i);//start LoginActivity
                } else if(msg.what == 0x000){
                    Toast.makeText(RegisterActivity.this, "注册失败,密码或用户名错误", Toast.LENGTH_SHORT).show();
                }
            }


        };
        enter_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = edt_name.getText().toString();
                System.out.print(name);
                tch_id = edt_tch_id.getText().toString();
                System.out.print(tch_id);
                course = edt_course.getText().toString();
                psd = edt_psd.getText().toString();
                System.out.print(psd);
                enter_psd = edt_enter_psd.getText().toString();
                System.out.print(enter_psd);
                String command = "注册";
                String Attribute = "教师";
                Message msg = new Message();
                msg.what = 0x345;
                msg.obj = tch_id + " " + name + " " + psd + " " + command + " " + Attribute;
                clientThread.revHandler.sendMessage(msg);
            }

        });
    }
}
