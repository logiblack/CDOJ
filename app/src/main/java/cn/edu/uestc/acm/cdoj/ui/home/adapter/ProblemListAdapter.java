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
import cn.edu.uestc.acm.cdoj.net.problem.ProblemListItem;

/**
 * Created by Grea on 2016/10/27.
 */

public class ProblemListAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<ProblemListItem> problemList;
    private SparseArray<FormattedData> formattedDataArray;

    public ProblemListAdapter(Context context, List<ProblemListItem> problemList) {
        this.problemList = problemList;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        formattedDataArray = new SparseArray<>();
    }

    @Override
    public int getCount() {
        return problemList.size();
    }

    @Override
    public Object getItem(int position) {
        return problemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ProblemListItem problem = problemList.get(position);
        FormattedData formattedData = formattedDataArray.get(position);
        if (formattedData == null) {
            formattedData = formatData(problem);
            formattedDataArray.put(position, formattedData);
        }
        View v = convertView;
        if (convertView == null) {
            v = mInflater.inflate(R.layout.problem_list_item, parent, false);
        }
        ((TextView) v.findViewById(R.id.problem_list_item_solved))
                .setText(formattedData.solved);
        ((TextView) v.findViewById(R.id.problem_list_item_id))
                .setText(formattedData.id);
        ((TextView) v.findViewById(R.id.problem_list_item_tried))
                .setText(formattedData.tried);
        ((TextView) v.findViewById(R.id.problem_list_item_source))
                .setText(problem.getSource());
        ((TextView) v.findViewById(R.id.problem_list_item_title))
                .setText(problem.getTitle());
        return v;
    }

    private FormattedData formatData(ProblemListItem problem) {
        FormattedData formattedData = new FormattedData();
        formattedData.id = "ID:" + problem.getProblemId();
        formattedData.solved = "Solved:" + problem.getSolved();
        formattedData.tried = "Tried:" + problem.getTried();
        return formattedData;
    }

    private class FormattedData {
        String id;
        String solved;
        String tried;
    }
}
