package cn.edu.uestc.acm.cdoj.net.article;

import java.util.List;

import cn.edu.uestc.acm.cdoj.net.ReceivedCallback;

/**
 * Created by leigu on 2017/4/20.
 */

public interface ObtainArticle {
    void getArticleContent(int id, ReceivedCallback<Article> callback);

    void getNoticeList(int page, ReceivedCallback<List<ArticleListItem>> callback);

    void searchArticle(int page, String orderFields, ReceivedCallback<List<ArticleListItem>> callback);

    void searchArticle(int page, String orderFields, int type, ReceivedCallback<List<ArticleListItem>> callback);

    void searchArticle(int page, String orderFields, int type, String username, ReceivedCallback<List<ArticleListItem>> callback);

    void searchArticle(int page, String orderFields, int type, String username, boolean orderAsc, ReceivedCallback<List<ArticleListItem>> callback);
}
