package cn.edu.uestc.acm.cdoj.net.article;

import cn.edu.uestc.acm.cdoj.net.generalData.ContentReceived;

/**
 * Created by Grea on 2016/10/25.
 */

public class ArticleReceived extends ContentReceived {
    private Article article;

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }
}
