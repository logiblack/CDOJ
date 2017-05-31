package cn.edu.uestc.acm.cdoj.ui.widget.GestureList;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import cn.edu.uestc.acm.cdoj.R;


/**
 * Created by leigu on 2017/4/14.
 */

public class GestureListView extends FrameLayout implements GestureList, SwipeRefreshLayout.OnRefreshListener, Status{

    private static final String TAG = "GestureListVIew";
    private BaseAdapter mAdapter;
    private SwipeRefreshLayout mRefreshLayout;
    private OnRefreshListener onRefreshListener;
    private OnUpLoadListener onUpLoadListener;
    private ListView mList;
    private StatusFooter mStatusFooter;
    private TextView emptyView;
    private ImageButton backTopButton;
    private boolean isUpLoading;
    private boolean hasMore = true;
    private boolean mListShown;
    private View mProgressContainer;
    private View mListContainer;


    public GestureListView(@NonNull Context context) {
        this(context, null);
    }

    public GestureListView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GestureListView(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    private void initView(){
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.gesture_list_progress, this);
        inflater.inflate(R.layout.gesture_list_content, this);
        setupList();
    }

    private void setupList(){
        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh);
        mRefreshLayout.setEnabled(false);

        mProgressContainer = findViewById(R.id.progressContainer);
        mListContainer = findViewById(R.id.listContainer);

        mStatusFooter = new StatusFooter(getContext());
        mList = (ListView) findViewById(android.R.id.list);

        backTopButton = (ImageButton) findViewById(R.id.up);
        backTopButton.setImageDrawable(Icons.getInstance().getBackTopIcon());
        backTopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mList.getFirstVisiblePosition() > 85) {
                    mList.setSelection(0);
                }else {
                    mList.smoothScrollToPosition(0);
                }
                v.setVisibility(View.GONE);
            }
        });

        mList.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                switch (scrollState) {
                    case SCROLL_STATE_IDLE:
                        int bottomPosition = view.getLastVisiblePosition();
                        boolean isBottom = bottomPosition == view.getCount() - 1;
                        if (isBottom && !isUpLoading && hasMore && onUpLoadListener != null) {
                            updateStatus(Status.LOADING);
                            isUpLoading = true;
                            onUpLoadListener.onUpLoad(GestureListView.this);
                        }
                        if (backTopButton != null) {
                            if (bottomPosition > 30){
                                backTopButton.setVisibility(View.VISIBLE);
                            }else {
                                backTopButton.setVisibility(View.GONE);
                            }
                        }
                        break;
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });

        emptyView = (TextView) findViewById(R.id.emptyTextView);
        setEmptyText(getResources().getString(R.string.empty));
    }


    public void setEmptyText(CharSequence text) {
        if (emptyView != null) {
            emptyView.setText(text);
            mList.setEmptyView(emptyView);
            if (mAdapter == null) {
                setListShown(false, false);
            }
        }
    }

    public void setListAdapter(BaseAdapter adapter) {
        boolean hadAdapter = mAdapter != null;
        mAdapter = adapter;
        if (mList != null) {
            mList.setAdapter(adapter);
            if (!mListShown && !hadAdapter) {
                setListShown(true, getWindowToken() != null);
            }
        }
    }

    @Override
    public void setOnListItemClickListener(AdapterView.OnItemClickListener listener) {
        mList.setOnItemClickListener(listener);
    }

    private void setListShown(boolean shown, boolean animate) {
        if (mListShown == shown) {
            return;
        }
        mListShown = shown;
        if (shown) {
            if (animate) {
                mProgressContainer.startAnimation(AnimationUtils.loadAnimation(
                        getContext(), android.R.anim.fade_out));
                mListContainer.startAnimation(AnimationUtils.loadAnimation(
                        getContext(), android.R.anim.fade_in));
            } else {
                mProgressContainer.clearAnimation();
                mListContainer.clearAnimation();
            }
            mProgressContainer.setVisibility(GONE);
            mListContainer.setVisibility(VISIBLE);
        } else {
            if (animate) {
                mProgressContainer.startAnimation(AnimationUtils.loadAnimation(
                        getContext(), android.R.anim.fade_in));
                mListContainer.startAnimation(AnimationUtils.loadAnimation(
                        getContext(), android.R.anim.fade_out));
            } else {
                mProgressContainer.clearAnimation();
                mListContainer.clearAnimation();
            }
            mProgressContainer.setVisibility(VISIBLE);
            mListContainer.setVisibility(GONE);
        }
    }

    public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
        this.onRefreshListener = onRefreshListener;
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setEnabled(onRefreshListener != null);
    }

    public void setOnUpLoadListener(OnUpLoadListener onUpLoadListener) {
        this.onUpLoadListener = onUpLoadListener;
        if (mList.getFooterViewsCount() < 1) {
            mList.addFooterView(mStatusFooter, null, false);
        }
    }

    @Override
    public void onRefresh() {
        onRefreshListener.onRefresh(this);
    }

    @Override
    public void updateStatus(int status) {
        mStatusFooter.updateStatus(status);
        hasMore = status != Status.LOAD_COMPLETE;
        isUpLoading = false;
    }
}
