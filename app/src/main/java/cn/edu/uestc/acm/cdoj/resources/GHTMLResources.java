package cn.edu.uestc.acm.cdoj.resources;

import android.content.res.Resources;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by leigu on 2017/4/15.
 */

public class GHTMLResources implements HTMLResources {
    public static HTMLResources instance;

    private String articleTemplate;
    private String problemTemplate;
    private String contestTemplate;

    private GHTMLResources(Resources resources) {
        try {
            InputStream input;
            byte[] in;

            input = resources.getAssets().open("articleRender.html");
            in = new byte[input.available()];
            input.read(in);
            articleTemplate = new String(in);

            input = resources.getAssets().open("problemRender.html");
            in = new byte[input.available()];
            input.read(in);
            problemTemplate = new String(in);

            input = resources.getAssets().open("contestOverviewRender.html");
            in = new byte[input.available()];
            input.read(in);
            contestTemplate = new String(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static HTMLResources createInstance(Resources resources) {
        if (instance == null) {
            instance = new GHTMLResources(resources);
        }
        return (HTMLResources) instance;
    }
    public static HTMLResources getInstance() {
        if (instance == null) {
            throw new IllegalStateException("need create");
        }
        return (HTMLResources) instance;
    }

    @Override
    public String getArticleTemplate() {
        return articleTemplate;
    }

    @Override
    public String getProblemTemplate() {
        return problemTemplate;
    }

    @Override
    public String getContestTemplate() {
        return contestTemplate;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return null;
    }
}
