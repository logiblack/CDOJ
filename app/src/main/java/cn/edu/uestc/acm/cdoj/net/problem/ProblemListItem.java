package cn.edu.uestc.acm.cdoj.net.problem;

/**
 * Created by leigu on 2017/4/20.
 */

public class ProblemListItem {
    private int difficulty;
    private boolean isSpj;
    private boolean isVisible;
    private int problemId;
    private int solved;
    private String source;
    private String title;
    private int tried;

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public boolean isSpj() {
        return isSpj;
    }

    public void setSpj(boolean spj) {
        isSpj = spj;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public int getProblemId() {
        return problemId;
    }

    public void setProblemId(int problemId) {
        this.problemId = problemId;
    }

    public int getSolved() {
        return solved;
    }

    public void setSolved(int solved) {
        this.solved = solved;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getTried() {
        return tried;
    }

    public void setTried(int tried) {
        this.tried = tried;
    }
}
