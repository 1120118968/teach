package com.example.teacher;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TabHost;
public class APPMainActivity extends AppCompatActivity{
    String name;
    String tchid;
    String loginpsd;
    private TabHost tab;//选项卡
    private ImageButton disciplineMaintenance;  //纪律维持
    private ImageButton btn_teacher_title;//btn_teacher_title 发起签到
    private ImageButton ib_chengji;       //ib_cengji 成绩
    private ImageButton ib_task;//任务
    private ImageButton ib_class; //课堂互动
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_main);
        Intent i = getIntent();
        name = i.getStringExtra("name");
        tchid = i.getStringExtra("tchid");
        loginpsd = i.getStringExtra("loginpsd");
        disciplineMaintenance = findViewById(R.id.ib_commonandcommunication);
        btn_teacher_title = findViewById(R.id.btn_teacher_title);
        ib_chengji = findViewById(R.id.ib_cengji);
        ib_task = findViewById(R.id.ib_task);
        ib_class = findViewById(R.id.ib_chengji);
        ib_class.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(APPMainActivity.this, ClassCommunicationActivity.class);
                i.putExtra("name", name);
                i.putExtra("tchid", tchid);
                i.putExtra("loginpsd", loginpsd);
                startActivity(i);
            }
        });
        ib_task.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(APPMainActivity.this, TaskActivity.class);
                startActivity(i);
            }
        });
        ib_chengji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(APPMainActivity.this, ScoreActivity.class);
                startActivity(i);
            }
        });
        btn_teacher_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(APPMainActivity.this, StartRegisterActivity.class);
                i.putExtra("name", name);
                i.putExtra("tchid", tchid);
                i.putExtra("loginpsd", loginpsd);
                startActivity(i);
            }
        });
        tab = (TabHost) this.findViewById(android.R.id.tabhost);
        tab.setup();
        tab.addTab(tab.newTabSpec("tab1").setIndicator("首页动态", null).setContent(R.id.tab1));
        tab.addTab(tab.newTabSpec("tab2").setIndicator("学生列表", null).setContent(R.id.tab2));
        tab.addTab(tab.newTabSpec("tab3").setIndicator("个人信息", null).setContent(R.id.tab3));
    }
}







