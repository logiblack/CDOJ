package cn.edu.uestc.acm.cdoj.net.contest;

import java.util.List;

import cn.edu.uestc.acm.cdoj.net.ReceivedCallback;

/**
 * Created by leigu on 2017/4/20.
 */

public interface ObtainContest {
    void getContestContent(int id, ReceivedCallback<Contest> callback);

    void searchContest(int page, ReceivedCallback<List<ContestListItem>> callback);

    void searchContest(int page, String keyword, ReceivedCallback<List<ContestListItem>> callback);

    void searchContest(int page, String keyword, int startId, ReceivedCallback<List<ContestListItem>> callback);

    void searchContest(int page, String keyword, int startId, boolean orderAsc, ReceivedCallback<List<ContestListItem>> callback);

    void searchContest(int page, String keyword, int startId, boolean orderAsc, String orderFields, ReceivedCallback<List<ContestListItem>> callback);
}
