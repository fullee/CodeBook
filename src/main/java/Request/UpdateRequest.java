package Request;

import android.os.Handler;

import org.json.JSONException;
import org.json.JSONObject;

import DataBeans.NewVersionBean;

/**
 * Created by root on 2015/12/11.
 */
public class UpdateRequest extends BaseRequest {

    public UpdateRequest(Handler handler) {
        super("http://git.icngor.com/new.json", handler);
    }

    @Override
    public NewVersionBean JsonParsing(String oldJson) {
        NewVersionBean versionBean = new NewVersionBean();
        try {
            JSONObject update = new JSONObject(oldJson);
            JSONObject updatex = update.getJSONObject("info");
            String apkurl = updatex.getString("url");
            String verInfo= updatex.getString("versionInfo");
            int verCode = updatex.getInt("version");
            versionBean.setApkUrl(apkurl);
            versionBean.setVersionInfo(verInfo);
            versionBean.setVersionCode(verCode);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return versionBean;
    }

}
