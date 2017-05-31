package cn.edu.uestc.acm.cdoj.net.problem;

/**
 * Created by Grea on 2016/10/25.
 */

public class Problem {
    private String title;
    private String source;
    private String sampleOutput;
    private String sampleInput;
    private String output;
    private String input;
    private String hint;
    private String description;
    private int tried;
    private int timeLimit;
    private int solved;
    private int problemId;
    private int outputLimit;
    private int memoryLimit;
    private int javaTimeLimit;
    private int javaMemoryLimit;
    private int difficulty;
    private int dataCount;
    private boolean isVisible;
    private boolean isSpj;

    public String jsonString;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSampleOutput() {
        return sampleOutput;
    }

    public void setSampleOutput(String sampleOutput) {
        this.sampleOutput = sampleOutput;
    }

    public String getSampleInput() {
        return sampleInput;
    }

    public void setSampleInput(String sampleInput) {
        this.sampleInput = sampleInput;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getTried() {
        return tried;
    }

    public void setTried(int tried) {
        this.tried = tried;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public int getSolved() {
        return solved;
    }

    public void setSolved(int solved) {
        this.solved = solved;
    }

    public int getProblemId() {
        return problemId;
    }

    public void setProblemId(int problemId) {
        this.problemId = problemId;
    }

    public int getOutputLimit() {
        return outputLimit;
    }

    public void setOutputLimit(int outputLimit) {
        this.outputLimit = outputLimit;
    }

    public int getMemoryLimit() {
        return memoryLimit;
    }

    public void setMemoryLimit(int memoryLimit) {
        this.memoryLimit = memoryLimit;
    }

    public int getJavaTimeLimit() {
        return javaTimeLimit;
    }

    public void setJavaTimeLimit(int javaTimeLimit) {
        this.javaTimeLimit = javaTimeLimit;
    }

    public int getJavaMemoryLimit() {
        return javaMemoryLimit;
    }

    public void setJavaMemoryLimit(int javaMemoryLimit) {
        this.javaMemoryLimit = javaMemoryLimit;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public int getDataCount() {
        return dataCount;
    }

    public void setDataCount(int dataCount) {
        this.dataCount = dataCount;
    }

    public boolean getSpj() {
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
}
