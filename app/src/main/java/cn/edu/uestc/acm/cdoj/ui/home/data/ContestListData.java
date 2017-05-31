package cn.edu.uestc.acm.cdoj.ui.home.data;

import android.content.Context;

import cn.edu.uestc.acm.cdoj.net.Connection;
import cn.edu.uestc.acm.cdoj.net.contest.ContestListItem;
import cn.edu.uestc.acm.cdoj.ui.AbsListData;
import cn.edu.uestc.acm.cdoj.ui.home.adapter.ContestListAdapter;
import cn.edu.uestc.acm.cdoj.ui.widget.GestureList.Status;

/**
 * Created by leigu on 2017/4/19.
 */

public class ContestListData extends AbsListData<ContestListItem> {

    public ContestListData(Context context) {
        super(context);
        Connection.getInstance().searchContest(mPageInfo.currentPage + 1, this);
    }

    @Override
    protected void createAdapter() {
        super.adapter = new ContestListAdapter(super.context, getData());
    }

    @Override
    public void onUpLoad(Status status) {
        super.onUpLoad(status);
        upLoad();
    }

    @Override
    public void onRefresh(Status status) {
        super.onRefresh(status);
        refresh();
    }

    @Override
    public void upLoad() {
        Connection.getInstance().searchContest(mPageInfo.currentPage + 1, this);
    }

    @Override
    public void refresh() {
        super.isRefreshing = true;
        Connection.getInstance().searchContest(1, this);
    }
}
