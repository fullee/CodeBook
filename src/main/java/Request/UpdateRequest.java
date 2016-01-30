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
        super("http://icngor.github.io/json/codebookUpdate.json", handler);
    }

    @Override
    public NewVersionBean JsonParsing(String oldJson) {
        NewVersionBean versionBean = new NewVersionBean();
        try {
            JSONObject update = new JSONObject(oldJson);
            JSONObject updatex = update.getJSONObject("info");
            String apkurl = updatex.getString("url");
            String verName = updatex.getString("versionName");
            String verInfo= updatex.getString("versionInfo");
            int verCode = updatex.getInt("versionCode");
            versionBean.setApkUrl(apkurl);
            versionBean.setVersionInfo(verInfo);
            versionBean.setVersionCode(verCode);
            versionBean.setVersionName(verName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return versionBean;
    }

}
