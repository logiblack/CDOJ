package cn.edu.uestc.acm.cdoj.net;

import cn.edu.uestc.acm.cdoj.net.generalData.Result;

/**
 * Created by leigu on 2017/4/20.
 */

public interface ReceivedCallback<T> {
    void onDataReceived(Result<T> result);
}
