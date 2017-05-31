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
import cn.edu.uestc.acm.cdoj.net.article.ArticleListItem;
import cn.edu.uestc.acm.cdoj.utils.TimeFormat;

/**
 * Created by Grea on 2016/10/27.
 */

public class NoticeListAdapter extends BaseAdapter {
    private LayoutInflater mInflater;
    private List<ArticleListItem> noticeList;
    private SparseArray<FormattedData> formattedDataArray;

    public NoticeListAdapter(Context context, List<ArticleListItem> noticeList) {
        this.noticeList = noticeList;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        formattedDataArray = new SparseArray<>();
    }

    @Override
    public int getCount() {
        return noticeList.size();
    }

    @Override
    public Object getItem(int position) {
        return noticeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ArticleListItem article = noticeList.get(position);
        FormattedData formattedData = formattedDataArray.get(position);
        if (formattedData == null) {
            formattedData = formatData(article);
            formattedDataArray.put(position, formattedData);
        }
        View v = convertView;
        if (convertView == null) {
            v = mInflater.inflate(R.layout.notice_list_item, parent, false);
        }
        ((TextView) v.findViewById(R.id.notice_list_item_author))
                .setText(article.getOwnerName());
        ((TextView) v.findViewById(R.id.notice_list_item_content))
                .setText(formattedData.summary);
        ((TextView) v.findViewById(R.id.notice_list_item_date))
                .setText(formattedData.date);
        ((TextView) v.findViewById(R.id.notice_list_item_title))
                .setText(article.getTitle());
        return v;
    }

    private FormattedData formatData(ArticleListItem article) {
        FormattedData formattedData = new FormattedData();
        if (article.getContent().equals("")) {
            formattedData.summary = article.getTitle();
        } else {
            int summaryLength = article.getContent().length() > 40 ? 40 : article.getContent().length();
            formattedData.summary = article.getContent().substring(0, summaryLength);
            formattedData.summary = formattedData.summary.replaceAll("!\\[title].*\\)", "[图片]");
            formattedData.summary = formattedData.summary.replaceAll("\\n", " ");
            formattedData.summary = formattedData.summary.replaceAll("\\s{2,}", " ");
        }
        formattedData.date = TimeFormat.getFormatDate(article.getTime());
        return formattedData;
    }

    private class FormattedData{
        String summary;
        String date;
    }
}
