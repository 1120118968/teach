package com.example.teacher;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
public class Main4ActivityGPSqiandao extends Activity {
    private Button bt;
    String content="你好";//想返回的内容
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4_gpsqiandao
        );
        bt=(Button) findViewById(R.id.gpsfanhui1);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data3 = new Intent(Main4ActivityGPSqiandao.this,APPMainActivity.class);
                startActivity(data3);
                data3.putExtra("data",content);
                setResult(2,data3);
                finish();
            }
        });
    }
}
