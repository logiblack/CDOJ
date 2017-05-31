package cn.edu.uestc.acm.cdoj.net.avatar;

import android.graphics.Bitmap;

import cn.edu.uestc.acm.cdoj.net.ReceivedCallback;

/**
 * Created by leigu on 2017/4/20.
 */

public interface ObtainAvatar {
    void getAvartar(String email, ReceivedCallback<Bitmap> callback);
}
