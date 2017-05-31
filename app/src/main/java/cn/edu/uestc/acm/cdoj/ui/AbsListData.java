package cn.edu.uestc.acm.cdoj.ui;

import android.content.Context;
import android.widget.BaseAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.edu.uestc.acm.cdoj.net.ReceivedCallback;
import cn.edu.uestc.acm.cdoj.net.generalData.PageInfo;
import cn.edu.uestc.acm.cdoj.net.generalData.Result;
import cn.edu.uestc.acm.cdoj.ui.widget.GestureList.GestureList;
import cn.edu.uestc.acm.cdoj.ui.widget.GestureList.OnRefreshListener;
import cn.edu.uestc.acm.cdoj.ui.widget.GestureList.OnUpLoadListener;
import cn.edu.uestc.acm.cdoj.ui.widget.GestureList.Status;

/**
 * Created by leigu on 2017/4/19.
 */

public abstract class AbsListData<T> implements OnRefreshListener, OnUpLoadListener, ReceivedCallback<List<T>> {
    private List<T> data = new ArrayList<>();
    protected Context context;
    protected PageInfo mPageInfo;
    protected boolean isRefreshing;
    protected Status status;
    private boolean firstLoad = true;
    protected BaseAdapter adapter;
    protected GestureList list;

    public AbsListData(Context context) {
        this.context = context;
        mPageInfo = new PageInfo();
        mPageInfo.currentPage = 0;
    }

    public List<T> getData() {
        return data;
    }

    @Override
    public void onUpLoad(Status status) {
        this.status = status;
    }

    public void setupList(GestureList list) {
        this.list = list;
        list.setOnRefreshListener(this);
        list.setOnUpLoadListener(this);
        createAdapter();
    }

    protected abstract void createAdapter();

    @Override
    public void onRefresh(Status status) {
        this.status = status;
    }

    public abstract void upLoad();

    public abstract void refresh();

    @Override
    public void onDataReceived(Result<List<T>> result) {
        if (result.status == Result.FALSE) {
            Toast.makeText(context, "加载失败", Toast.LENGTH_LONG).show();
            if (status != null) {
                status.updateStatus(Result.FALSE);
            }
            return;
        }
        mPageInfo = (PageInfo) result.extra;
        if (isRefreshing) {
            data.clear();
            isRefreshing = false;
        }
        data.addAll(result.content);
        if (firstLoad && adapter != null && list != null) {
            list.setListAdapter(adapter);
            firstLoad = false;
        }
        if (status != null) {
            status.updateStatus(result.status);
        }
    }
}
