package cn.edu.uestc.acm.cdoj.ui.widget.GestureList;

import android.widget.AdapterView;
import android.widget.BaseAdapter;

/**
 * Created by leigu on 2017/4/22.
 */

public interface GestureList {
    void setOnRefreshListener(OnRefreshListener listener);

    void setOnUpLoadListener(OnUpLoadListener listener);

    void setListAdapter(BaseAdapter adapter);

    void setOnListItemClickListener(AdapterView.OnItemClickListener listener);

    void updateStatus(int status);
}
