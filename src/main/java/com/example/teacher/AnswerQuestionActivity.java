package com.example.teacher;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
public class AnswerQuestionActivity extends AppCompatActivity {
    EditText titleId;//id
    EditText title;//content
    EditText answer;//answer
    EditText zs;
    EditText js;
    Button submit;
    String name;
    String tch_id;
    String psd;
    String str_id;
    String str_title;
    String str_answer;
    String str_zs;
    String str_js;
    String Command;
    String Attribute;
    String style;
    ClientThread clientThread;
    Handler handler;
    Message msg = new Message();
    final String[] spinnerItems = {"单选题","多选题","填空题"};
    Spinner mSpinner1;
    Button chakandatixiangqing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer_question);
        Intent i = getIntent();
        name = i.getStringExtra("name");
        tch_id = i.getStringExtra("tchid");
        psd = i.getStringExtra("loginpsd");
        titleId = findViewById(R.id.edt_id);
        title = findViewById(R.id.edt_content);
        answer = findViewById(R.id.edt_answer);
        mSpinner1 = findViewById(R.id.spinner_1);
        submit = findViewById(R.id.submit_answer);
        chakandatixiangqing = findViewById(R.id.chakanxiangqing);
        zs = findViewById(R.id.edt_zs);
        js = findViewById(R.id.edt_js);
        final ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(AnswerQuestionActivity.this,
                android.R.layout.simple_spinner_item, spinnerItems);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_gallery_item);
        mSpinner1.setAdapter(spinnerAdapter);
        mSpinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int pos, long id) {
                Toast.makeText(AnswerQuestionActivity.this,"输入题目内容时，输入选项时请用:分隔开",Toast.LENGTH_LONG).show();
                ((TextView)view).setGravity(Gravity.CENTER);
                if ( pos == 0){
                    style = "single";
                } else  if ( pos == 1 ){
                    style = "double";
                } else if (pos == 2 ){
                    style = "fillBlank";
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        clientThread = new ClientThread(handler);
        new Thread(clientThread).start();//

        submit.setOnClickListener(new View.OnClickListener() {
            //启动答题详情页面
            @Override
            public void onClick(View v) {
                str_id = titleId.getText().toString();
                str_title = title.getText().toString();
                str_answer = answer.getText().toString();
                str_zs = zs.getText().toString();
                str_js = js.getText().toString();
                String context= str_id + "," + str_title + "," + str_answer + "," + style + "," + str_zs + "," + str_js;
                Command = "开始答题";//写入题目信息
                Attribute = "教师";
                msg.what = 0x345;
                String content ;
                content = tch_id + " " + name + " "+context +" "+ Command +" " + Attribute ;
                msg.obj=content;
                clientThread.revHandler.sendMessage(msg);
                Toast.makeText(AnswerQuestionActivity.this,"备课成功",Toast.LENGTH_SHORT).show();
            }
        });
        chakandatixiangqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AnswerQuestionActivity.this,AnswerDetailActivity.class);
                i.putExtra("name", name);
                i.putExtra("tchid", tch_id);
                i.putExtra("loginpsd",psd);
                startActivity(i);
            }
        });
    }
}
