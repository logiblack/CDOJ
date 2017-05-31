package cn.edu.uestc.acm.cdoj.net.contest;

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

public class ContestConnection {
    public static ContestConnection instance = new ContestConnection();

    private String baseUrl = "http://acm.uestc.edu.cn";
    private String contentPath = "/contest/data/";
    private String searchPath = "/contest/search";
    private String[] parameters = new String[]{"currentPage", "keyword", "startId", "orderAsc", "orderFields"};

    private ContestConnection() {

    }

    public static ContestConnection getInstance() {
        return instance;
    }

    public String getContentJson(int id) {
        byte[] receiveData = Request.get(baseUrl, contentPath + id);
        if (receiveData != null) {
            return new String(receiveData);
        }
        return "";
    }

    public String searchForJson(int page, String keyword, int startId, boolean orderAsc, String orderFields) {
        Object[] values = new Object[]{page, keyword, startId, orderAsc, orderFields};
        byte[] receiveData = Request.post(baseUrl, searchPath, JsonUtil.getJsonString(parameters, values));
        if (receiveData != null) {
            return new String(receiveData);
        }
        return "";
    }

    public Result<Contest> getContent(int id) {
        return handleContentJson(getContentJson(id));
    }

    public Result<List<ContestListItem>> search(int page, String keyword, int startId, boolean orderAsc, String orderFields) {
        return handleSearchJson(searchForJson(page, keyword, startId, orderAsc, orderFields));
    }

    private Result<Contest> handleContentJson(String jsonString) {
        Result<Contest> result = new Result<>();
        ContestReceived contestReceived = JSON.parseObject(jsonString, ContestReceived.class);
        if (!contestReceived.getResult().equals("success")) {
            result.status = Result.FALSE;
            return result;
        }
        result.status = Result.SUCCESS;
        result.content = contestReceived.getContest();
        result.extra = contestReceived.getProblemList();
        return result;
    }

    private Result<List<ContestListItem>> handleSearchJson(String jsonString) {
        Result<List<ContestListItem>> result = new Result<>();

        ListReceived<ContestListItem> listReceived = JSON.parseObject(jsonString, new TypeReference<ListReceived<ContestListItem>>() {
        });
        if (!listReceived.getResult().equals("success")) {
            result.status = Result.FALSE;
            return result;
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
