package com.icngor.codebook;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;


public class FeedBack extends AppCompatActivity {
    private EditText ContentET = null;
    private EditText UserinfoET = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        ContentET = (EditText) findViewById(R.id.editText2);
        UserinfoET = (EditText) findViewById(R.id.editText3);
    }
    public void UpdateFeedBack(View view) {
        final String content = ContentET.getText().toString();
        final String userinfo = UserinfoET.getText().toString();
        boolean IsSendSuccess = false;
        new Thread(){
            @Override
            public void run() {
                Email email = new SimpleEmail();
                email.setHostName("smtp.mxhichina.com");
                email.setSmtpPort(465);
                email.setAuthentication("codebook@icngor.com", "abc!@#123");
                email.setSSLOnConnect(true);
                try {
                    email.setFrom("codebook@icngor.com");
                    email.setSubject("[重要]来自CodeBook使用的反馈");
                    email.setMsg("我的邮箱：" + userinfo +
                            "\n使用中的问题：" + content);
                    email.addTo("icngor@163.com");
                    email.send();
                } catch (EmailException e) {
                    //Toast.makeText(FeedBack.this,"提交失败",Toast.LENGTH_SHORT).show();
                    e.printStackTrace();
                }
            }
        }.start();
        Toast.makeText(FeedBack.this,"反馈成功",Toast.LENGTH_SHORT).show();
        this.finish();


    }
    public void back(View view) {
        this.finish();
    }
}
