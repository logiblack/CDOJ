package cn.edu.uestc.acm.cdoj.net.avatar;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import cn.edu.uestc.acm.cdoj.net.Request;
import cn.edu.uestc.acm.cdoj.net.generalData.Result;
import cn.edu.uestc.acm.cdoj.net.utils.DigestUtil;

/**
 * Created by leigu on 2017/4/20.
 */

public class AvatarConnection {
    private static AvatarConnection instance = new AvatarConnection();

    private String TAG = "AvatarConnection";
    private String baseUrl = "http://cdn.v2ex.com";
    private String avatarPath = "http://cdn.v2ex.com/gravatar/%@.jpg?s=200&&d=retro";

    private AvatarConnection() {

    }

    public static AvatarConnection getInstance() {
        return instance;
    }

    public Bitmap getBitmap(String email) {
        Bitmap localCache = AvatarCache.getInstance().get(email);
        if (localCache != null) {
            return localCache;
        }
        byte[] response = Request.get(baseUrl, avatarPath.replace("%@", DigestUtil.md5(email)));
        if (response == null) {
            Log.e(TAG, "can't obtain avatar");
            return null;
        }
        AvatarCache.getInstance().save(response, email);
        return BitmapFactory.decodeByteArray(response, 0, response.length);
    }

    public Result<Bitmap> get(String email) {
        Result<Bitmap> result = new Result<>();
        Bitmap avatar = getBitmap(email);
        if (avatar == null) {
            result.status = Result.FALSE;
            return result;
        }
        result.content = avatar;
        result.status = Result.SUCCESS;
        return result;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return null;
    }
}
