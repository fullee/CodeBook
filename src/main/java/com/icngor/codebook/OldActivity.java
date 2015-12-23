package com.icngor.codebook;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

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
            int updateVersion = (int) msg.obj;
            if (msg.what == UPDATE_APPVIRSION) {
                    int localVersion = getAppVersion();
                    if(updateVersion > localVersion){
                       UpdateView();
                    }else {
                        Toast.makeText(OldActivity.this,"暂无新版本，可通过反馈要求改进",Toast.LENGTH_SHORT).show();
                    }
            }else if(msg.what == -1) {
                Toast.makeText(OldActivity.this, "msg.what无标识",Toast.LENGTH_SHORT).show();
            }
        }
    };
    public int getAppVersion() {
        PackageManager packageManager = getPackageManager();
        PackageInfo packageInfo = null;
        try {
            packageInfo = packageManager.getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packageInfo.versionCode;
    }

    public void UpdateView(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(OldActivity.this);
        dialog.setTitle("升级提醒");
        dialog.setMessage("新版功能");
        dialog.setNegativeButton("下次提醒", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.setPositiveButton("立即更新", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
                Intent intent = new Intent(OldActivity.this,MainActivity.class);
                PendingIntent pi = PendingIntent.getActivity(OldActivity.this, 0, intent, 0);
                Notification notification = new Notification.Builder(OldActivity.this)
                        .setSmallIcon(R.mipmap.icon)
                        .setContentIntent(pi)
                        .build();
                //        .setTicker("nihao")
                //
                //                .setContentText("内容")
                //
                //                .setContentTitle("标题")
                //                .setOngoing(true)

                notificationManager.notify(1, notification);

                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
