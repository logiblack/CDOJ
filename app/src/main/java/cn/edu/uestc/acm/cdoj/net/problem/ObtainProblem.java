package cn.edu.uestc.acm.cdoj.net.problem;

import java.util.List;

import cn.edu.uestc.acm.cdoj.net.ReceivedCallback;

/**
 * Created by leigu on 2017/4/20.
 */

public interface ObtainProblem {
    void getProblemContent(int id, ReceivedCallback<Problem> callback);

    void searchProblem(int page, ReceivedCallback<List<ProblemListItem>> callback);

    void searchProblem(int page, String keyword, ReceivedCallback<List<ProblemListItem>> callback);

    void searchProblem(int page, String keyword, int startId, ReceivedCallback<List<ProblemListItem>> callback);

    void searchProblem(int page, String keyword, int startId, boolean orderAsc, ReceivedCallback<List<ProblemListItem>> callback);

    void searchProblem(int page, String keyword, int startId, boolean orderAsc, String orderFields, ReceivedCallback<List<ProblemListItem>> callback);

}
