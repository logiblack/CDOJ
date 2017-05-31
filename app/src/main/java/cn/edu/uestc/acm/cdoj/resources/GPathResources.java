package cn.edu.uestc.acm.cdoj.resources;

import android.content.Context;

import java.io.File;

/**
 * Created by leigu on 2017/4/15.
 */

public class GPathResources implements PathResources {

    private static GPathResources instance;
    private File avatarDir;
    private File cacheDir;
    private File filesDir;

    private GPathResources(Context context) {
        avatarDir = context.getExternalFilesDir("avatar");
        cacheDir = context.getExternalCacheDir();
        filesDir = context.getExternalFilesDir("");
    }

    static PathResources createInstance(Context context) {
        if (instance == null) {
            instance = new GPathResources(context);
        }
        return (PathResources) instance;
    }

    public static PathResources getInstance() {
        if (instance == null) {
            throw new IllegalStateException("need create");
        }
        return (PathResources) instance;
    }

    @Override
    public File getAvatarDir() {
        return avatarDir;
    }

    @Override
    public File getCacheDir() {
        return cacheDir;
    }

    @Override
    public File getFilesDir() {
        return filesDir;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return null;
    }
}
