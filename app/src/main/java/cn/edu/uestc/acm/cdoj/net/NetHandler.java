package cn.edu.uestc.acm.cdoj.net;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import cn.edu.uestc.acm.cdoj.net.generalData.Result;

/**
 * Created by Grea on 2016/10/23.
 */

public class NetHandler extends Handler {

    String TAG = "NetHandle";

    private static NetHandler instance;


    private NetHandler() {
        super();
    }

    public static NetHandler createInstance() {
        if (Looper.getMainLooper() != Looper.myLooper()) {
            throw new IllegalStateException("current Thread is not original thread");
        }
        instance = new NetHandler();
        return instance;
    }

    public static NetHandler getInstance() {
        if (instance == null) {
            if (Looper.getMainLooper() != Looper.myLooper()) {
                throw new IllegalStateException("hasn't created and current Thread is not original thread");
            }
            instance = new NetHandler();
        }
        return instance;
    }


    @Override
    public void handleMessage(Message msg) {
        if (msg.what == 0x01012013 && msg.obj instanceof Object[]) {
            Object[] data = (Object[]) msg.obj;
            ReceivedCallback callback = (ReceivedCallback) data[0];
            Result result = (Result) data[1];
            callback.onDataReceived(result);
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return null;
    }
}
