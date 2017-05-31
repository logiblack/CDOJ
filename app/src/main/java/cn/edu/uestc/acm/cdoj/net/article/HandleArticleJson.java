package cn.edu.uestc.acm.cdoj.net.article;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import cn.edu.uestc.acm.cdoj.net.generalData.ListReceived;
import cn.edu.uestc.acm.cdoj.net.generalData.PageInfo;
import cn.edu.uestc.acm.cdoj.net.generalData.Result;

/**
 * Created by leigu on 2017/4/20.
 */

public class HandleArticleJson {
    public Result handleJson(String jsonString) {
        ListReceived<Article> listReceived = JSON.parseObject(jsonString, new TypeReference<ListReceived<Article>>() {
        });
        Result result = new Result();

        if (!listReceived.getResult().equals("success")) {
            result.status = Result.FALSE;
            return result;
        }
        for (Article article : listReceived.getList()) {
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
}
