package com.example.teacher;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
public class ForgetPasswordActivity extends AppCompatActivity {
    private EditText get_pn;
    private EditText get_yzm;
    private Button btn_get_yzm;
    private Button next;
    private ImageView yzm;
    private String realCode;//生成的验证码
    private String edt_dode;//输入的验证码
    IdentifyCode IdentifyingCode = new IdentifyCode();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);
        get_pn = (EditText)this.findViewById(R.id.get_pn);
        get_yzm = (EditText)this.findViewById(R.id.get_yzm);
        btn_get_yzm = (Button) this.findViewById(R.id.get_pw);
        next = (Button)this.findViewById(R.id.fg_next);
        yzm = (ImageView)this.findViewById(R.id.img_yzm);
        btn_get_yzm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yzm.setImageBitmap(IdentifyingCode.getInstance().createBitmap());
                realCode=IdentifyingCode.getInstance().getCode().toLowerCase();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_dode = get_yzm.getText().toString();
               Intent i = new Intent(ForgetPasswordActivity.this,FindPasswordActivity.class);
                if(edt_dode.equals(realCode) && get_pn.getText().toString() != ""){
                    Toast.makeText(ForgetPasswordActivity.this,  "验证码正确,将跳转至下一页面", Toast.LENGTH_SHORT).show();
                    startActivity(i);
                }
            }
        });
        yzm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             if(edt_dode == realCode){
                 Toast.makeText(ForgetPasswordActivity.this,  "验证码正确", Toast.LENGTH_SHORT).show();
             }else{
                 Toast.makeText(ForgetPasswordActivity.this,  "验证码错误，请重新输入", Toast.LENGTH_SHORT).show();
                 get_yzm.setText("");
            }
            }
        });

    }
}
