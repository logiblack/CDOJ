package cn.edu.uestc.acm.cdoj.ui.home.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import cn.edu.uestc.acm.cdoj.R;
import cn.edu.uestc.acm.cdoj.net.contest.ContestListItem;
import cn.edu.uestc.acm.cdoj.utils.TimeFormat;

/**
 * Created by Grea on 2016/10/27.
 */

public class ContestListAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<ContestListItem> contestList;
    private SparseArray<FormattedData> formattedDataArray;

    public ContestListAdapter(Context context, List<ContestListItem> contestList) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.contestList = contestList;
        formattedDataArray = new SparseArray<>();
    }

    @Override
    public int getCount() {
        return contestList.size();
    }

    @Override
    public Object getItem(int position) {
        return contestList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ContestListItem contest = contestList.get(position);
        FormattedData formattedData = formattedDataArray.get(position);
        if (formattedData == null) {
            formattedData = formatData(contest);
            formattedDataArray.put(position, formattedData);
        }
        View v = convertView;
        if (convertView == null) {
            v = mInflater.inflate(R.layout.contest_list_item, parent, false);
        }
        ((TextView) v.findViewById(R.id.contest_list_item_id))
                .setText(formattedData.id);
        ((TextView) v.findViewById(R.id.contest_list_item_date))
                .setText(formattedData.date);
        ((TextView) v.findViewById(R.id.contest_list_item_permission))
                .setText(contest.getTypeName());
        ((TextView) v.findViewById(R.id.contest_list_item_status))
                .setText(contest.getStatus());
        ((TextView) v.findViewById(R.id.contest_list_item_timeLimit))
                .setText(formattedData.duration);
        ((TextView) v.findViewById(R.id.contest_list_item_title))
                .setText(contest.getTitle());
        return v;
    }

    private FormattedData formatData(ContestListItem contest) {
        FormattedData formattedData = new FormattedData();
        formattedData.id = "ID:" + contest.getContestId();
        formattedData.date = TimeFormat.getFormatDate(contest.getTime());
        formattedData.duration = TimeFormat.getFormatTime(contest.getLength());
        return formattedData;
    }

    private class FormattedData {
        String id;
        String date;
        String duration;
    }
}
