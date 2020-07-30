package com.example.teacher;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
public class ClassCommunicationActivity extends AppCompatActivity {
    ImageButton answerQuestion;
    String name;
    String tch_id;
    String psd;
    String Command;
    String Attribute;
    ClientThread clientThread;
    Message msg = new Message();
    @SuppressLint("HandlerLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_communication);
        Intent i = getIntent();
        name = i.getStringExtra("name");
        tch_id = i.getStringExtra("tchid");
        psd = i.getStringExtra("loginpsd");
        answerQuestion = findViewById(R.id.img_ketangceshi);
        answerQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Command = "开始答题";//写入题目信息
                Attribute = "教师";
                msg.what = 0x345;
                String content ;
                content = tch_id + " " + name + " " + psd + " "+ Command +" " + Attribute;
                msg.obj=content;
                Intent i = new Intent(ClassCommunicationActivity.this, AnswerQuestionActivity.class);
                i.putExtra("name",name);
                i.putExtra("tchid",tch_id);
                i.putExtra("loginpsd",psd);
                startActivity(i);
            }
        });

    }
}
