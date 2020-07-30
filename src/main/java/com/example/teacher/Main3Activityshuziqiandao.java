package com.example.teacher;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class Main3Activityshuziqiandao extends Activity {
    private Button bt;
    String content="你好";//想返回的内容
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3_activityshuziqiandao);
        bt=(Button) findViewById(R.id.shuzifanhui1);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data2 = new Intent(Main3Activityshuziqiandao.this,APPMainActivity.class);
                startActivity(data2);
                data2.putExtra("data",content);
                setResult(2,data2);
                finish();
            }
        });
    }
}
