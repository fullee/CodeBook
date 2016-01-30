package com.icngor.codebook;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import DataBeans.NewVersionBean;

/**
 * Created by root on 2015/12/11.
 */
public class OldActivity extends Activity {
    //msg.what标识
    public int UPDATE_APPVIRSION = 1;
    NotificationManager notificationManager;
    Handler han = new Handler(){

        @Override
        public void handleMessage(Message msg) {
            NewVersionBean versionBean = (NewVersionBean) msg.obj;
            if (msg.what == UPDATE_APPVIRSION) {
                    int localVersion = getVerCode(OldActivity.this);
                    if(versionBean.getVersionCode() > localVersion){
                       UpdateView(versionBean);
                    }else {
                        Toast.makeText(OldActivity.this,"暂无新版本，可通过反馈要求改进",Toast.LENGTH_SHORT).show();
                    }
            }else if(msg.what == -1) {
                Toast.makeText(OldActivity.this, "msg.what无标识",Toast.LENGTH_SHORT).show();
            }
        }
    };
    public int getVerCode(Context context){
        int verCode = -1;
        try {
            verCode = context.getPackageManager().getPackageInfo("com.icngor.codebook", 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("版本号获取异常", e.getMessage());
        }
        return verCode;
    }
    public String getVerName(Context context){
        String verName = "";
        try {
            verName = context.getPackageManager().getPackageInfo("com.icngor.codebook", 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e("版本名称获取异常", e.getMessage());
        }
        return verName;
    }
    public void UpdateView(final NewVersionBean versionBean){
        AlertDialog.Builder dialog = new AlertDialog.Builder(OldActivity.this);
        dialog.setTitle("升级提醒"+versionBean.getVersionName());
        dialog.setMessage(versionBean.getVersionInfo());
        dialog.setNegativeButton("下次提醒", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(OldActivity.this, DownApkService.class);
                intent.putExtra("url", versionBean.getApkUrl());
                startService(intent);
                //在状态栏显示信息
//                notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//                Intent intent = new Intent(OldActivity.this,MainActivity.class);
//                PendingIntent pi = PendingIntent.getActivity(OldActivity.this, 0, intent, 0);
//                Notification notification = new Notification.Builder(OldActivity.this)
//                        .setSmallIcon(R.mipmap.icon)
//                        .setContentIntent(pi)
//                        .build();
//                //        .setTicker("nihao")
//                //
//                //                .setContentText("内容")
//                //
//                //                .setContentTitle("标题")
//                //                .setOngoing(true)
//
//                notificationManager.notify(1, notification);

                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
