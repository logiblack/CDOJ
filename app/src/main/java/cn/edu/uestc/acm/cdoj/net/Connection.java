package cn.edu.uestc.acm.cdoj.net;

import android.os.Message;

import java.util.List;

import cn.edu.uestc.acm.cdoj.net.article.Article;
import cn.edu.uestc.acm.cdoj.net.article.ArticleConnection;
import cn.edu.uestc.acm.cdoj.net.article.ArticleListItem;
import cn.edu.uestc.acm.cdoj.net.article.ObtainArticle;
import cn.edu.uestc.acm.cdoj.net.contest.Contest;
import cn.edu.uestc.acm.cdoj.net.contest.ContestConnection;
import cn.edu.uestc.acm.cdoj.net.contest.ContestListItem;
import cn.edu.uestc.acm.cdoj.net.contest.ObtainContest;
import cn.edu.uestc.acm.cdoj.net.generalData.Result;
import cn.edu.uestc.acm.cdoj.net.problem.Problem;
import cn.edu.uestc.acm.cdoj.net.problem.ProblemConnection;
import cn.edu.uestc.acm.cdoj.net.problem.ObtainProblem;
import cn.edu.uestc.acm.cdoj.net.problem.ProblemListItem;
import cn.edu.uestc.acm.cdoj.net.utils.ThreadUtil;

/**
 * Created by leigu on 2017/4/20.
 */

public class Connection implements ObtainArticle, ObtainProblem, ObtainContest {
    private static Connection instance;
    private NetHandler handler;

    private Connection(NetHandler handler) {
        this.handler = handler;
    }

    public static synchronized Connection createInstance(NetHandler handler) {
        if (instance == null) {
            instance = new Connection(handler);
        }
        return instance;
    }

    public static Connection getInstance() {
        if (instance == null) {
            throw new IllegalStateException("connection not init!");
        }
        return instance;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return null;
    }

    @Override
    public void getArticleContent(final int id, final ReceivedCallback<Article> callback) {
        ThreadUtil.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                Result<Article> result = ArticleConnection.getInstance().getContent(id);
                Message message = new Message();
                Object[] obj = new Object[2];
                obj[0] = callback;
                obj[1] = result;
                message.obj = obj;
                message.what = 0x01012013;
                handler.sendMessage(message);
            }
        });
    }

    @Override
    public void getNoticeList(int page, ReceivedCallback<List<ArticleListItem>> callback) {
        searchArticle(page, "time", callback);
    }

    @Override
    public void searchArticle(int page, String orderFields, ReceivedCallback<List<ArticleListItem>> callback) {
        searchArticle(page, orderFields, 0, callback);
    }

    @Override
    public void searchArticle(int page, String orderFields, int type, ReceivedCallback<List<ArticleListItem>> callback) {
        searchArticle(page, orderFields, type, "", callback);
    }

    @Override
    public void searchArticle(int page, String orderFields, int type, String username, ReceivedCallback<List<ArticleListItem>> callback) {
        searchArticle(page, orderFields, type, username, false, callback);
    }

    @Override
    public void searchArticle(final int page, final String orderFields, final int type, final String username, final boolean orderAsc, final ReceivedCallback<List<ArticleListItem>> callback) {
        ThreadUtil.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                Result<List<ArticleListItem>> result = ArticleConnection.getInstance().search(page, orderFields, type, username, orderAsc);
                Message message = new Message();
                Object[] obj = new Object[2];
                obj[0] = callback;
                obj[1] = result;
                message.obj = obj;
                message.what = 0x01012013;
                handler.sendMessage(message);
            }
        });
    }

    @Override
    public void getContestContent(final int id, final ReceivedCallback<Contest> callback) {
        ThreadUtil.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                Result<Contest> result = ContestConnection.getInstance().getContent(id);
                Message message = new Message();
                Object[] obj = new Object[2];
                obj[0] = callback;
                obj[1] = result;
                message.obj = obj;
                message.what = 0x01012013;
                handler.sendMessage(message);
            }
        });
    }

    @Override
    public void searchContest(int page, ReceivedCallback<List<ContestListItem>> callback) {
        searchContest(page, "", callback);
    }

    @Override
    public void searchContest(int page, String keyword, ReceivedCallback<List<ContestListItem>> callback) {
        searchContest(page, keyword, 1, callback);
    }

    @Override
    public void searchContest(int page, String keyword, int startId, ReceivedCallback<List<ContestListItem>> callback) {
        searchContest(page, keyword, startId, false, callback);
    }

    @Override
    public void searchContest(int page, String keyword, int startId, boolean orderAsc, ReceivedCallback<List<ContestListItem>> callback) {
        searchContest(page, keyword, startId, orderAsc, "id", callback);
    }

    @Override
    public void searchContest(final int page, final String keyword, final int startId, final boolean orderAsc, final String orderFields, final ReceivedCallback<List<ContestListItem>> callback) {
        ThreadUtil.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                Result<List<ContestListItem>> result = ContestConnection.getInstance().search(page, keyword, startId, orderAsc, orderFields);
                Message message = new Message();
                Object[] obj = new Object[2];
                obj[0] = callback;
                obj[1] = result;
                message.obj = obj;
                message.what = 0x01012013;
                handler.sendMessage(message);
            }
        });
    }

    @Override
    public void getProblemContent(final int id, final ReceivedCallback<Problem> callback) {
        ThreadUtil.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                Result<Problem> result = ProblemConnection.getInstance().getContent(id);
                Message message = new Message();
                Object[] obj = new Object[2];
                obj[0] = callback;
                obj[1] = result;
                message.obj = obj;
                message.what = 0x01012013;
                handler.sendMessage(message);
            }
        });
    }

    @Override
    public void searchProblem(int page, ReceivedCallback<List<ProblemListItem>> callback) {
        searchProblem(page, "", callback);
    }

    @Override
    public void searchProblem(int page, String keyword, ReceivedCallback<List<ProblemListItem>> callback) {
        searchProblem(page, keyword, 1, callback);
    }

    @Override
    public void searchProblem(int page, String keyword, int startId, ReceivedCallback<List<ProblemListItem>> callback) {
        searchProblem(page, keyword, startId, true, callback);
    }

    @Override
    public void searchProblem(int page, String keyword, int startId, boolean orderAsc, ReceivedCallback<List<ProblemListItem>> callback) {
        searchProblem(page, keyword, startId, orderAsc, "id", callback);
    }

    @Override
    public void searchProblem(final int page, final String keyword, final int startId, final boolean orderAsc, final String orderFields, final ReceivedCallback<List<ProblemListItem>> callback) {
        ThreadUtil.getInstance().execute(new Runnable() {
            @Override
            public void run() {
                Result result = ProblemConnection.getInstance().search(page, keyword, startId, orderAsc, orderFields);
                Message message = new Message();
                Object[] obj = new Object[2];
                obj[0] = callback;
                obj[1] = result;
                message.obj = obj;
                message.what = 0x01012013;
                handler.sendMessage(message);
            }
        });
    }
}