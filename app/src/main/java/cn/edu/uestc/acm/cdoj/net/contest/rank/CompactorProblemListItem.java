package cn.edu.uestc.acm.cdoj.net.contest.rank;

/**
 * Created by leigu on 2017/4/20.
 */

public class CompactorProblemListItem {
    private boolean firstBlood;
    private int penalty;
    private boolean solved;
    private int solvedTime;
    private int tried;
    private boolean triedAfterFrozen;

    public boolean isFirstBlood() {
        return firstBlood;
    }

    public void setFirstBlood(boolean firstBlood) {
        this.firstBlood = firstBlood;
    }

    public int getPenalty() {
        return penalty;
    }

    public void setPenalty(int penalty) {
        this.penalty = penalty;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    public int getSolvedTime() {
        return solvedTime;
    }

    public void setSolvedTime(int solvedTime) {
        this.solvedTime = solvedTime;
    }

    public int getTried() {
        return tried;
    }

    public void setTried(int tried) {
        this.tried = tried;
    }

    public boolean isTriedAfterFrozen() {
        return triedAfterFrozen;
    }

    public void setTriedAfterFrozen(boolean triedAfterFrozen) {
        this.triedAfterFrozen = triedAfterFrozen;
    }
}
