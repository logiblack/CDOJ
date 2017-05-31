package cn.edu.uestc.acm.cdoj.net.article;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import java.util.List;

import cn.edu.uestc.acm.cdoj.net.utils.JsonUtil;
import cn.edu.uestc.acm.cdoj.net.Request;
import cn.edu.uestc.acm.cdoj.net.generalData.ListReceived;
import cn.edu.uestc.acm.cdoj.net.generalData.PageInfo;
import cn.edu.uestc.acm.cdoj.net.generalData.Result;

/**
 * Created by leigu on 2017/4/20.
 */

public class ArticleConnection {
    private static ArticleConnection instance = new ArticleConnection();

    private String baseUrl = "http://acm.uestc.edu.cn";
    private String contentPath = "/article/data/";
    private String searchPath = "/article/search";
    private String[] parametersN = new String[]{"currentPage", "orderAsc", "orderFields", "type"};
    private String[] parameters = new String[]{"currentPage", "username", "orderAsc", "orderFields", "type"};
    private String TAG = "ArticleConnection";

    private ArticleConnection() {}

    public static ArticleConnection getInstance() {
        return instance;
    }

    public String getContentJson(int id) {
        byte[] receiveData = Request.get(baseUrl, contentPath + id);
        if (receiveData != null) {
            return new String(receiveData);
        }
        return "";
    }

    public String searchForJson(int page, String orderFields, int type, String username, boolean orderAsc) {
        String request;
        if (username.equals("")) {
            Object[] values = new Object[]{page,  orderAsc, orderFields, type};
            request = JsonUtil.getJsonString(parametersN, values);
        }else {
            Object[] values = new Object[]{page, username, orderAsc, orderFields, type};
            request = JsonUtil.getJsonString(parameters, values);
        }
        byte[] receiveData = Request.post(baseUrl, searchPath, request);
        if (receiveData != null) {
            return new String(receiveData);
        }
        return "";
    }

    public Result<Article> getContent(int id) {
        return handleContentJson(getContentJson(id));
    }

    public Result<List<ArticleListItem>> search(int page, String orderFields, int type, String username, boolean orderAsc) {
        return handleSearchJson(searchForJson(page, orderFields, type, username, orderAsc));
    }

    private Result<Article> handleContentJson(String jsonString) {
        Result<Article> result = new Result<>();
        ArticleReceived articleReceived = JSON.parseObject(jsonString, ArticleReceived.class);
        if (!articleReceived.getResult().equals("success")) {
            result.status = Result.FALSE;
            return result;
        }
        result.content = articleReceived.getArticle();
        result.status = Result.SUCCESS;
        return result;
    }

    private Result<List<ArticleListItem>> handleSearchJson(String jsonString) {
        Result<List<ArticleListItem>> result = new Result<>();

        ListReceived<ArticleListItem> listReceived = JSON.parseObject(jsonString, new TypeReference<ListReceived<ArticleListItem>>() {
        });
        if (listReceived == null || !listReceived.getResult().equals("success")) {
            result.status = Result.FALSE;
            return result;
        }
        for (ArticleListItem article : listReceived.getList()) {
            article.jsonString = jsonString;
        }
        PageInfo mPageInfo = listReceived.getPageInfo();
        if (mPageInfo.totalItems == 0) {
            result.status = Result.NO_DATA;
        } else if (mPageInfo.currentPage == mPageInfo.totalPages) {
            result.status = Result.FINISH;
        } else {
            result.status = Result.SUCCESS;
        }

        result.content = listReceived.getList();
        result.extra = mPageInfo;
        return result;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return null;
    }
}
