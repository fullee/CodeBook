package com.icngor.codebook;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import Request.BaseRequest;
import Request.UpdateRequest;
import tools.NetTools;

public class About extends OldActivity {

    TextView localVersion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        localVersion = (TextView) findViewById(R.id.textView8);

        localVersion.append(getAppVersion()+"  ");
    }

    public void update(View view) {
        if (NetTools.isNetworkAvailable(About.this)) {
            UpdateRequest request = new UpdateRequest(han);
            request.setHANDLERMSG(UPDATE_APPVIRSION);
            request.start();
        } else {
            Toast.makeText(About.this,"网络未连接",Toast.LENGTH_SHORT).show();
        }
    }


    //用户反馈
    public void feedback(View view) {
        Intent intent = new Intent(About.this, FeedBack.class);
        startActivity(intent);
    }
    //关于作者
    public void aboutMe(View view) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(About.this);
        dialog.setMessage("联系方式：\n1192577322@qq.com");
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void back(View view) {
        this.finish();
    }
}
