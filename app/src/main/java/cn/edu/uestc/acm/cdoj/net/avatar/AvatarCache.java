package cn.edu.uestc.acm.cdoj.net.avatar;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by leigu on 2017/4/20.
 */

public class AvatarCache {
    private static AvatarCache instance;

    private final long OPTIMIZE_INTERVAL = 60 * 60 * 24 * 7 * 1000;

    private String avatarCacheDir;
    private String TAG = "AvatarCache";

    AvatarCache(String avatarCacheDir) {
        this.avatarCacheDir = avatarCacheDir;
    }

    public static AvatarCache createInstance(String avatarCacheDir) {
        instance = new AvatarCache(avatarCacheDir);
        return instance;
    }

    public static AvatarCache getInstance() {
        if (instance == null) {
            return new NullAvatarCache();
        }
        return instance;
    }

    public Bitmap get(String email) {
        return BitmapFactory.decodeFile(avatarCacheDir + File.separator + email);
    }

    public void save(byte[] data, String email) {
        File file = new File(avatarCacheDir + File.separator + email);
        if (file.exists()) {
            if (!file.delete()) {
                Log.e(TAG, "error in saving image");
                return;
            }
        }
        try {
            FileOutputStream output = new FileOutputStream(file);
            output.write(data);
            output.close();
        }  catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void clearAllCache() {
        File[] files = new File(avatarCacheDir).listFiles();
        for (File file : files) {
            if (!file.delete()) {
                Log.e(TAG, "error in saving image");
            }
        }
    }

    public void optimizeCache() {
        long currentTime = System.currentTimeMillis();
        File[] files = new File(avatarCacheDir).listFiles();
        if (files.length > 50) {
            for (File file : files) {
                if (currentTime - file.lastModified() > OPTIMIZE_INTERVAL) {
                    if (!file.delete()) {
                        Log.e(TAG, "error in saving image");
                    }
                }
            }
        }
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return null;
    }
}

class NullAvatarCache extends AvatarCache {

    NullAvatarCache() {
        super(null);
    }

    @Override
    public void save(byte[] data, String email) {

    }

    @Override
    public void clearAllCache() {

    }

    @Override
    public void optimizeCache() {

    }
}
