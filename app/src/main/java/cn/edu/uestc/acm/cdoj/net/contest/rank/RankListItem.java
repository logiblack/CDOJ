package cn.edu.uestc.acm.cdoj.net.contest.rank;

import java.util.List;

/**
 * Created by leigu on 2017/4/20.
 */

public class RankListItem {
    private String email;
    private String name;
    private String nickName;
    private int penalty;
    private int rank;
    private String reallyName;
    private int solved;
    private int tried;
    private List<CompactorProblemListItem> itemList;
    private List<TeamItem> teamUsers;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getPenalty() {
        return penalty;
    }

    public void setPenalty(int penalty) {
        this.penalty = penalty;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getReallyName() {
        return reallyName;
    }

    public void setReallyName(String reallyName) {
        this.reallyName = reallyName;
    }

    public int getSolved() {
        return solved;
    }

    public void setSolved(int solved) {
        this.solved = solved;
    }

    public int getTried() {
        return tried;
    }

    public void setTried(int tried) {
        this.tried = tried;
    }
}
