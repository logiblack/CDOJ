package cn.edu.uestc.acm.cdoj.ui.widget.GestureList;

import cn.edu.uestc.acm.cdoj.net.generalData.Result;

/**
 * Created by leigu on 2017/4/14.
 */

public interface Status {
    int LOADING = 0x100;
    int LOAD_COMPLETE = Result.FINISH;
    int LOAD_PROBLEM = Result.FALSE;
    int BLANK = 0x103;
    int NO_DATA = Result.NO_DATA;
    int NO_NET = Result.NO_NET;
    int CONNECT_OVERTIME = Result.CONNECT_OVERTIME;

    void updateStatus(int status);
}
