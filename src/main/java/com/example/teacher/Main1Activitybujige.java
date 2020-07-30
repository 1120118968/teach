package com.example.teacher;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class Main1Activitybujige extends AppCompatActivity {
    private Button btn1bujige;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1_activitybujige);
        btn1bujige = (Button) findViewById(R.id.btn1bujige);
        btn1bujige.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Main1Activitybujige.this, APPMainActivity.class);
                startActivity(intent2);
            }
        });
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
        tv1.setText("name:      jack");
        tv2.setText("name:      tom");
        tv3.setText("name:      tim");
        tv4.setText("name:      green");
    }
}
