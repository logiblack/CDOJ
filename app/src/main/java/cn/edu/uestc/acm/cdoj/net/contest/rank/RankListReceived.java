package cn.edu.uestc.acm.cdoj.net.contest.rank;

import java.util.List;

/**
 * Created by leigu on 2017/4/20.
 */

public class RankListReceived {
    private long lastFetched;
    private List<RankProblemListItem> problemList;
    private List<RankListItem> rankList;

    public long getLastFetched() {
        return lastFetched;
    }

    public void setLastFetched(long lastFetched) {
        this.lastFetched = lastFetched;
    }

    public List<RankProblemListItem> getProblemList() {
        return problemList;
    }

    public void setProblemList(List<RankProblemListItem> problemList) {
        this.problemList = problemList;
    }

    public List<RankListItem> getRankList() {
        return rankList;
    }

    public void setRankList(List<RankListItem> rankList) {
        this.rankList = rankList;
    }
}
