package cn.edu.uestc.acm.cdoj.ui.widget.GestureList;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import cn.edu.uestc.acm.cdoj.R;


public class StatusFooter extends LinearLayout {

    private ImageView statusImageView;
    private ProgressBar loadingProgressBar;
    private FrameLayout statusContainer;
    private TextView infoTextView;
    private Icons resource;

    public StatusFooter(Context context) {
        this(context, null);
    }

    public StatusFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        resource = Icons.createInstance(getResources());

        setGravity(Gravity.CENTER);
        setLayoutParams(new AbsListView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, dip2px(60)));
        setOrientation(HORIZONTAL);

        statusContainer = new FrameLayout(getContext());
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        layoutParams.setMargins(0, 0, dip2px(45), 0);
        statusContainer.setLayoutParams(layoutParams);

        loadingProgressBar = new ProgressBar(getContext());

        statusImageView = new ImageView(getContext());
        statusImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        statusImageView.setLayoutParams(new LayoutParams(dip2px(60), ViewGroup.LayoutParams.MATCH_PARENT));

        statusContainer.addView(loadingProgressBar);
        statusContainer.addView(statusImageView);

        infoTextView = new TextView(getContext());

        addView(statusContainer);
        addView(infoTextView);
        updateStatus(Status.LOAD_COMPLETE);
    }

    public void updateStatus(int state) {
        switch (state) {
            case Status.BLANK:
                setVisibility(GONE);
                return;
            case Status.LOADING:
                statusImageView.setVisibility(GONE);
                loadingProgressBar.setVisibility(VISIBLE);
                infoTextView.setText(getResources().getString(R.string.loading));
                setVisibility(VISIBLE);
                return;
            case Status.LOAD_COMPLETE:
                statusImageView.setImageDrawable(resource.getLoadCompleteIcon());
                infoTextView.setText(getContext().getString(R.string.loadComplete));
                break;
            case Status.LOAD_PROBLEM:
                statusImageView.setImageDrawable(resource.getLoadProblemIcon());
                infoTextView.setText(getContext().getString(R.string.loadProblem));
                break;
            case Status.NO_DATA:
                statusImageView.setImageDrawable(resource.getNoDataIcon());
                infoTextView.setText(getContext().getString(R.string.noData));
                break;
            case Status.NO_NET:
                statusImageView.setImageDrawable(resource.getNetProblemIcon());
                infoTextView.setText(getContext().getString(R.string.netNotConnect));
                break;
            case Status.CONNECT_OVERTIME:
                statusImageView.setImageDrawable(resource.getNetProblemIcon());
                infoTextView.setText(getContext().getString(R.string.connectOvertime));
                break;
            default:
                return;
        }
        setVisibility(VISIBLE);
        loadingProgressBar.setVisibility(GONE);
        statusImageView.setVisibility(VISIBLE);
    }

    public void updateStatus(Drawable image, String text) {
        statusImageView.setImageDrawable(image);
        infoTextView.setText(text);
        setVisibility(VISIBLE);
        loadingProgressBar.setVisibility(GONE);
        statusImageView.setVisibility(VISIBLE);
    }

    int dip2px(int dip) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dip, getResources().getDisplayMetrics());
    }
}
