package cn.edu.uestc.acm.cdoj.net.article;

/**
 * Created by Grea on 2016/10/25.
 */

public class Article extends ArticleListItem{
    private String userId;
    private int type;
    private int order;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
}
