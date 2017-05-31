package cn.edu.uestc.acm.cdoj.net.contest.rank;

import cn.edu.uestc.acm.cdoj.net.generalData.ContentReceived;

/**
 * Created by leigu on 2017/4/20.
 */

public class Rank extends ContentReceived {
    private RankListReceived rankList;

    public RankListReceived getRankList() {
        return rankList;
    }

    public void setRankList(RankListReceived rankList) {
        this.rankList = rankList;
    }
}
