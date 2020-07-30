package com.example.teacher;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
public class FindPasswordActivity extends AppCompatActivity {
    private Button fi;
    private Button scan;
    private Button product_ewm;
    private EditText password1;
    private EditText password2;
    private TextView msg;
    private ImageView img;
    String psd1;
    String psd2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_password);
        password1 = (EditText)this.findViewById(R.id.edt_password1);
        password2 = (EditText)this.findViewById(R.id.edt_password2);
        msg = (TextView)this.findViewById(R.id.msg);
        scan = (Button)this.findViewById(R.id.scan);
        product_ewm =(Button)this.findViewById(R.id.product_ewm);
        img = (ImageView)this.findViewById(R.id.bitmap);
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        product_ewm.setOnClickListener(new View.OnClickListener() {
            String contentEtString = "id:   password:    ";
            @Override
            public void onClick(View v) {
                try {
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        fi = (Button)this.findViewById(R.id.finish);
        fi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(psd1 == psd2){
                    psd1 = password1.getText().toString();
                    psd2 = password2.getText().toString();
                    Intent i = new Intent(FindPasswordActivity.this,MainActivity.class);
                    startActivity(i);
                }
            }
        });
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0 && resultCode == RESULT_OK) {
            if (data != null) {
            }
        }
    }

}
