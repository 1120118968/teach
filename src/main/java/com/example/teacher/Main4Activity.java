package com.example.teacher;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
public class Main4Activity extends AppCompatActivity {
    private Button btnzongchengji;
    private EditText editText;
    private EditText editText2;
    private EditText editText3;
    private EditText editText4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        btnzongchengji = (Button) findViewById(R.id.btnzongchengjifanhui);
        btnzongchengji.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(Main4Activity.this,APPMainActivity.class);
                startActivity(intent5);
            }
        });
        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4);
        editText.setText("第一名： Tom");
        editText2.setText("第二名： Tim");
        editText3.setText("第三名： jack");
        editText4.setText("第四名： Tony");
    }
}
