package cn.edu.uestc.acm.cdoj.net.contest;

import java.util.List;

import cn.edu.uestc.acm.cdoj.net.generalData.ContentReceived;
import cn.edu.uestc.acm.cdoj.net.problem.Problem;

/**
 * Created by Grea on 2016/10/25.
 */

public class ContestReceived extends ContentReceived {
    private Contest contest;
    private List<Problem> problemList;

    public Contest getContest() {
        return contest;
    }

    public void setContest(Contest contestData) {
        this.contest = contestData;
    }

    public List<Problem> getProblemList() {
        return problemList;
    }

    public void setProblemList(List<Problem> problemDataList) {
        this.problemList = problemDataList;
    }
}
