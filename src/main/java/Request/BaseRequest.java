package Request;

import android.os.Handler;
import android.os.Message;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by root on 2015/12/8.
 */
public class BaseRequest extends Thread {
    private String urlstring = null;
    private Handler handler = null;


    int HANDLERMSG = -1;
    public BaseRequest(String url,Handler handler){
        this.urlstring = url;
        this.handler = handler;
    }
    public void setHANDLERMSG(int handlermsg) {
        this.HANDLERMSG = handlermsg;
    }

    @Override
    public void run() {
        try {
            URL url = new URL(urlstring);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setRequestMethod("GET");
            //conn.connect();
            if(conn.getResponseCode() == 200){
                //客户端接受字符串
                InputStream is = conn.getInputStream();
                BufferedReader br = new BufferedReader(new InputStreamReader(is));
                String str = "";
                StringBuffer sb = new StringBuffer();
                while((str = br.readLine()) != null){
                    sb.append(str+"\n");
                }
                Message msg = new Message();
                msg.what = HANDLERMSG;
                msg.obj = JsonParsing(sb.toString());
                handler.sendMessage(msg);
            }
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
    public int JsonParsing(String oldJson){
       return 0;
    }
}
