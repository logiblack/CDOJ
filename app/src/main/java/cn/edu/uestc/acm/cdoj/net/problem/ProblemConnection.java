package cn.edu.uestc.acm.cdoj.net.problem;

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

public class ProblemConnection {
    private static ProblemConnection instance = new ProblemConnection();
    private String baseUrl = "http://acm.uestc.edu.cn";
    private String contentPath = "/problem/data/";
    private String searchPath = "/problem/search";
    private String[] parameters = new String[]{"currentPage", "keyword", "startId", "orderAsc", "orderFields"};

    private ProblemConnection() {

    }

    public static ProblemConnection getInstance() {
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

    public Result<Problem> getContent(int id) {
        return handleContentJson(getContentJson(id));
    }

    public Result<List<ProblemListItem>> search(int page, String keyword, int startId, boolean orderAsc, String orderFields) {
        return handleSearchJson(searchForJson(page, keyword, startId, orderAsc, orderFields));
    }

    private Result<Problem> handleContentJson(String jsonString) {
        Result<Problem> result = new Result<>();
        ProblemReceived problemReceived = JSON.parseObject(jsonString, ProblemReceived.class);
        if (!problemReceived.getResult().equals("success")) {
            result.status = Result.FALSE;
            return result;
        }
        result.status = Result.SUCCESS;
        Problem problem = problemReceived.getProblem();
        problem.jsonString = jsonString;
        result.content = problem;
        return result;
    }

    private Result<List<ProblemListItem>> handleSearchJson(String jsonString) {
        Result<List<ProblemListItem>> result = new Result<>();

        ListReceived<ProblemListItem> listReceive = JSON.parseObject(jsonString, new TypeReference<ListReceived<ProblemListItem>>() {
        });
        if (!listReceive.getResult().equals("success")) {
            result.status = Result.FALSE;
            return result;
        }
        PageInfo mPageInfo = listReceive.getPageInfo();

        if (mPageInfo.totalItems == 0) {
            result.status = Result.NO_DATA;
        } else if (mPageInfo.currentPage == mPageInfo.totalPages) {
            result.status = Result.FINISH;
        } else {
            result.status = Result.SUCCESS;
        }
        result.content = listReceive.getList();
        result.extra = mPageInfo;
        return result;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return null;
    }
}
