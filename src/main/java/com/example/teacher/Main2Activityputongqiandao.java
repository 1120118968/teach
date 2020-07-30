package com.example.teacher;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class Main2Activityputongqiandao extends Activity {
    private Button bt;
    String content="你好";//想返回的内容
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_activityputongqiandao);
        bt=(Button) findViewById(R.id.putongfanhui1);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data = new Intent(Main2Activityputongqiandao.this,APPMainActivity.class);
                startActivity(data);
                data.putExtra("data",content);
                setResult(2,data);
                finish();
            }
        });
    }
}

