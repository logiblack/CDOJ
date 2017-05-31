package cn.edu.uestc.acm.cdoj.resources;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

import cn.edu.uestc.acm.cdoj.R;
import cn.edu.uestc.acm.cdoj.resources.utils.RGBAColor;
import cn.edu.uestc.acm.cdoj.resources.utils.RenderBitmap;

/**
 * Created by leigu on 2017/4/14.
 */

public class GIconResources implements IconResources {
    private static GIconResources instance;
    /**
     * 默认颜色矩阵和默认Logo图标
     * */
    private Drawable defaultLogo;
    /**
     * 参赛者题目完成状态图标
     */
    private Drawable rankIcon_didNothing;
    private Drawable rankIcon_tried;
    private Drawable rankIcon_solved;
    private Drawable rankIcon_theFirstSolved;
    /**
     * 主列表的Tab图标
     */
    private Drawable noticeListIcon_selected;
    private Drawable noticeListIcon_unselect;
    private Drawable problemListIcon_selected;
    private Drawable problemListIcon_unselect;
    private Drawable contestListIcon_selected;
    private Drawable contestListIcon_unselect;
    /**
     * 问题详情页按钮图标
     * */
    private Drawable problemIcon_addCode;
    private Drawable problemIcon_checkResult;

    private final RenderBitmap mRender;
    private Resources resources;

    private GIconResources(Resources resources) {
        this.resources = resources;
        mRender = RenderBitmap.instance(resources);
        refreshIcons();
    }

    static IconResources createInstance(Resources resources) {
        if (instance == null) {
            instance = new GIconResources(resources);
        }
        return (IconResources) instance;
    }

    public static IconResources getInstance() {
        if (instance == null) {
            throw new IllegalStateException("need create");
        }
        return (IconResources) instance;
    }

    private void refreshIcons() {
        refreshRankIcons();
        refreshHomPageTabIcons();
        refreshProblemDetailsIcons();
    }

    private void refreshRankIcons() {
        rankIcon_didNothing = mRender.render(R.drawable.ic_contest_rank_star, RGBAColor.getColorMatrix(resources, R.color.rank_didNothing));
        rankIcon_tried = mRender.render(R.drawable.ic_contest_rank_star, RGBAColor.getColorMatrix(resources, R.color.rank_tried));
        rankIcon_solved = mRender.render(R.drawable.ic_contest_rank_star, RGBAColor.getColorMatrix(resources, R.color.rank_solved));
        rankIcon_theFirstSolved = mRender.render(R.drawable.ic_contest_rank_star, RGBAColor.getColorMatrix(resources, R.color.rank_theFirstSolved));
    }

    private void refreshHomPageTabIcons() {
        noticeListIcon_selected = mRender.render(R.drawable.ic_home_tab_notice_selected);
        noticeListIcon_unselect = mRender.render(R.drawable.ic_home_tab_notice_unselect);
        problemListIcon_selected = mRender.render(R.drawable.ic_home_tab_problem_selected);
        problemListIcon_unselect = mRender.render(R.drawable.ic_home_tab_problem_unselect);
        contestListIcon_selected = mRender.render(R.drawable.ic_home_tab_contest_selected);
        contestListIcon_unselect = mRender.render(R.drawable.ic_home_tab_contest_unselect);
    }

    private void refreshProblemDetailsIcons() {
        problemIcon_addCode = mRender.render(R.drawable.ic_add);
        problemIcon_checkResult = mRender.render(R.drawable.ic_flag);
    }

    public Drawable getRankIcon_didNothing() {
        return rankIcon_didNothing;
    }

    public Drawable getRankIcon_tried() {
        return rankIcon_tried;
    }

    public Drawable getRankIcon_solved() {
        return rankIcon_solved;
    }

    public Drawable getRankIcon_theFirstSolved() {
        return rankIcon_theFirstSolved;
    }

    public Drawable getNoticeListIcon_selected() {
        return noticeListIcon_selected;
    }

    public Drawable getNoticeListIcon_unselect() {
        return noticeListIcon_unselect;
    }

    public Drawable getProblemListIcon_selected() {
        return problemListIcon_selected;
    }

    public Drawable getProblemListIcon_unselect() {
        return problemListIcon_unselect;
    }

    public Drawable getContestListIcon_selected() {
        return contestListIcon_selected;
    }

    public Drawable getContestListIcon_unselect() {
        return contestListIcon_unselect;
    }

    public Drawable getProblemIcon_addCode() {
        return problemIcon_addCode;
    }

    public Drawable getProblemIcon_checkResult() {
        return problemIcon_checkResult;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return null;
    }
}
