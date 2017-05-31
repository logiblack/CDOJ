package cn.edu.uestc.acm.cdoj;

import android.app.Activity;
import android.os.Bundle;
import android.webkit.CookieSyncManager;

import cn.edu.uestc.acm.cdoj.net.Connection;
import cn.edu.uestc.acm.cdoj.net.NetHandler;
import cn.edu.uestc.acm.cdoj.net.avatar.AvatarCache;
import cn.edu.uestc.acm.cdoj.resources.GHTMLResources;
import cn.edu.uestc.acm.cdoj.resources.GIconResources;
import cn.edu.uestc.acm.cdoj.resources.GPathResources;
import cn.edu.uestc.acm.cdoj.resources.ResourceFactory;
import cn.edu.uestc.acm.cdoj.ui.home.HomePage;

public class MainActivity extends Activity {

    String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Connection.createInstance(NetHandler.createInstance());
        AvatarCache.createInstance(getExternalFilesDir("avatar").getPath());
        CookieSyncManager.createInstance(this);
        ResourceFactory.init(this);
        setContentView(HomePage.initHomePage(this));
    }
}
