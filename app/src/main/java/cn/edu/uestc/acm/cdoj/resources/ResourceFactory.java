package cn.edu.uestc.acm.cdoj.resources;

import android.content.Context;

/**
 * Created by leigu on 2017/4/19.
 */

public class ResourceFactory {

    private static boolean hasInit;

    public static void init(Context context) {
        GIconResources.createInstance(context.getResources());
        GHTMLResources.createInstance(context.getResources());
        GPathResources.createInstance(context);
        hasInit = true;
    }

    public static HTMLResources getHTML() {
        if (!hasInit) {
            throw new IllegalStateException("ResourceFactory need init");
        }
        return GHTMLResources.getInstance();
    }

    public static IconResources getIcon() {
        if (!hasInit) {
            throw new IllegalStateException("ResourceFactory need init");
        }
        return GIconResources.getInstance();
    }

    public static PathResources getPath() {
        if (!hasInit) {
            throw new IllegalStateException("ResourceFactory need init");
        }
        return GPathResources.getInstance();
    }
}
