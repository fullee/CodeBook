package Request;

import android.os.Handler;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by root on 2015/12/11.
 */
public class UpdateRequest extends BaseRequest {

    public UpdateRequest(Handler handler) {
        super("http://git.icngor.com/new.json", handler);
    }

    String apkUrl = null;
    public String getApkUrl() {
        return apkUrl;
    }
    @Override
    public int JsonParsing(String oldJson) {
        int newJson = 0;
        try {
            JSONObject update = new JSONObject(oldJson);
            JSONObject updatex = update.getJSONObject("info");
            apkUrl = updatex.getString("url");
            newJson = updatex.getInt("version");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return newJson;
    }

}
