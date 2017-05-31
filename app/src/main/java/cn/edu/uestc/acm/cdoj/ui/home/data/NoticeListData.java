package cn.edu.uestc.acm.cdoj.ui.home.data;

import android.content.Context;

import cn.edu.uestc.acm.cdoj.net.Connection;
import cn.edu.uestc.acm.cdoj.net.article.ArticleListItem;
import cn.edu.uestc.acm.cdoj.ui.AbsListData;
import cn.edu.uestc.acm.cdoj.ui.home.adapter.NoticeListAdapter;
import cn.edu.uestc.acm.cdoj.ui.widget.GestureList.Status;

/**
 * Created by leigu on 2017/4/19.
 */

public class NoticeListData extends AbsListData<ArticleListItem> {

    public NoticeListData(Context context) {
        super(context);
        Connection.getInstance().getNoticeList(mPageInfo.currentPage + 1, this);
    }


    @Override
    protected void createAdapter() {
        super.adapter = new NoticeListAdapter(super.context, super.getData());
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
        Connection.getInstance().getNoticeList(mPageInfo.currentPage + 1, this);
    }

    @Override
    public void refresh() {
        super.isRefreshing = true;
        Connection.getInstance().getNoticeList(1, this);
    }
}

