package com.example.teacher;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
public class Main2Activityjige extends AppCompatActivity {
    private Button btnjigefanhui;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2_activityjige);
        btnjigefanhui = (Button) findViewById(R.id.btnjigefanhui);
        btnjigefanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentb = new Intent(Main2Activityjige.this,APPMainActivity.class);
                startActivity(intentb);
            }
        });
        tv1 = findViewById(R.id.Text);
        tv2 = findViewById(R.id.Text2);
        tv3 = findViewById(R.id.Text3);
        tv4 = findViewById(R.id.Text4);
        tv1.setText("name:      tony");
        tv2.setText("name:      black");
        tv3.setText("name:      tim");
        tv4.setText("name:      green");

    }
}
