package com.example.teacher;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
public class Main3Activitymanfen extends AppCompatActivity {
    private Button btnmanfenfanhui;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3_activitymanfen);
        btnmanfenfanhui = (Button) findViewById(R.id.manfenfanhui);
        btnmanfenfanhui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intenta = new Intent(Main3Activitymanfen.this,APPMainActivity.class);
                startActivity(intenta);
            }
        });
    }
}
