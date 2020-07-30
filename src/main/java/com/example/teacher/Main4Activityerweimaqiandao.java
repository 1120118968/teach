package com.example.teacher;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
public class Main4Activityerweimaqiandao extends Activity {
    private Button bt;
    String content="你好";//想返回的内容
    private Button scan;
    private ImageView img_scan;
    private TextView yqdrs;
    private TextView wqdrs;
    @Override
    protected void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5_activityerweimaqiandao);
        scan = findViewById(R.id.scan);
        img_scan = findViewById(R.id.img_scan);
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        bt=(Button) findViewById(R.id.erweimafanhui1);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent data4 = new Intent(Main4Activityerweimaqiandao.this,APPMainActivity.class);
                startActivity(data4);
                data4.putExtra("data",content);
                setResult(2,data4);
                finish();
            }
        });
        yqdrs = findViewById(R.id.yidaorenshu4);
        wqdrs = findViewById(R.id.weidaorenshu4);
        yqdrs.setText("1人");
        wqdrs.setText("2人");
    }
}
