package cn.edu.uestc.acm.cdoj.ui.widget.GestureList;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import cn.edu.uestc.acm.cdoj.R;


/**
 * Created by leigu on 2017/4/12.
 */

public class GestureListFragment extends Fragment implements GestureList, SwipeRefreshLayout.OnRefreshListener, Status {
    private static final String TAG = "GestureListFragment";
    private SwipeRefreshLayout mRefreshLayout;
    private OnRefreshListener onRefreshListener;
    private OnUpLoadListener onUpLoadListener;
    private StatusFooter mStatusFooter;
    private ImageButton backTopButton;
    private boolean isUpLoading;
    private boolean hasMore = true;
    private BaseAdapter mAdapter;
    private ListView mList;
    private View mEmptyView;
    private TextView mStandardEmptyView;
    private View mProgressContainer;
    private View mListContainer;
    private CharSequence mEmptyText;
    private boolean mListShown;
    private AdapterView.OnItemClickListener mOnItemClickListener;

    final private Handler mHandler = new Handler();

    final private Runnable mRequestFocus = new Runnable() {
        public void run() {
            mList.focusableViewAvailable(mList);
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.gesture_list_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ensureList();
        if (mAdapter != null && onRefreshListener != null && mAdapter.getCount() == 0) {
            mRefreshLayout.setRefreshing(true);
            onRefreshListener.onRefresh(this);
        }
    }

    /**
     * Detach from list view.
     */
    @Override
    public void onDestroyView() {
        mHandler.removeCallbacks(mRequestFocus);
        mList = null;
        mListShown = false;
        mEmptyView = mProgressContainer = mListContainer = null;
        mStandardEmptyView = null;
        backTopButton = null;
        mRefreshLayout = null;
        mStatusFooter = null;
        super.onDestroyView();
    }

    /**
     * Provide the cursor for the list view.
     */
    @Override
    public void setListAdapter(BaseAdapter adapter) {
        boolean hadAdapter = mAdapter != null;
        mAdapter = adapter;
        if (mList != null) {
            mList.setAdapter(adapter);
            if (!mListShown && !hadAdapter) {
                // The list was hidden, and previously didn't have an
                // mAdapter.  It is now time to show it.
                setListShown(true, getView().getWindowToken() != null);
            }
        }
    }

    @Override
    public void setOnListItemClickListener(AdapterView.OnItemClickListener listener) {
        mOnItemClickListener = listener;
        if (mList != null) {
            mList.setOnItemClickListener(listener);
        }
    }

    /**
     * Set the currently selected list item to the specified
     * position with the mAdapter's data
     *
     * @param position item's position
     */
    public void setSelection(int position) {
        ensureList();
        mList.setSelection(position);
    }

    /**
     * Get the position of the currently selected list item.
     */
    public int getSelectedItemPosition() {
        ensureList();
        return mList.getSelectedItemPosition();
    }

    /**
     * Get the cursor row ID of the currently selected list item.
     */
    public long getSelectedItemId() {
        ensureList();
        return mList.getSelectedItemId();
    }

    /**
     * Get the fragment's list view widget.
     */
    public ListView getListView() {
        ensureList();
        return mList;
    }

    /**
     * The default content for a ListFragment has a TextView that can
     * be shown when the list is empty.  If you would like to have it
     * shown, call this method to supply the text it should use.
     */
    public void setEmptyText(CharSequence text) {
        ensureList();
        if (mStandardEmptyView == null) {
            throw new IllegalStateException("Can't be used with a custom content view");
        }
        mStandardEmptyView.setText(text);
        if (mEmptyText == null) {
            mList.setEmptyView(mStandardEmptyView);
        }
        mEmptyText = text;
    }

    /**
     * Control whether the list is being displayed.  You can make it not
     * displayed if you are waiting for the initial data to show in it.  During
     * this time an indeterminant progress indicator will be shown instead.
     * <p>
     * <p>Applications do not normally need to use this themselves.  The default
     * behavior of ListFragment is to start with the list not being shown, only
     * showing it once an mAdapter is given with {@link #setListAdapter(BaseAdapter)}.
     * If the list at that point had not been shown, when it does get shown
     * it will be do without the user ever seeing the hidden state.
     *
     * @param shown If true, the list view is shown; if false, the progress
     *              indicator.  The initial value is true.
     */
    public void setListShown(boolean shown) {
        setListShown(shown, true);
    }

    /**
     * Like {@link #setListShown(boolean)}, but no animation is used when
     * transitioning from the previous state.
     */
    public void setListShownNoAnimation(boolean shown) {
        setListShown(shown, false);
    }

    /**
     * Control whether the list is being displayed.  You can make it not
     * displayed if you are waiting for the initial data to show in it.  During
     * this time an indeterminant progress indicator will be shown instead.
     *
     * @param shown   If true, the list view is shown; if false, the progress
     *                indicator.  The initial value is true.
     * @param animate If true, an animation will be used to transition to the
     *                new state.
     */
    private void setListShown(boolean shown, boolean animate) {
        ensureList();
        if (mProgressContainer == null) {
            throw new IllegalStateException("Can't be used with a custom content view");
        }
        if (mListShown == shown) {
            return;
        }
        mListShown = shown;
        if (shown) {
            if (animate) {
                mProgressContainer.startAnimation(AnimationUtils.loadAnimation(
                        mList.getContext(), android.R.anim.fade_out));
                mListContainer.startAnimation(AnimationUtils.loadAnimation(
                        mList.getContext(), android.R.anim.fade_in));
            } else {
                mProgressContainer.clearAnimation();
                mListContainer.clearAnimation();
            }
            mProgressContainer.setVisibility(View.GONE);
            mListContainer.setVisibility(View.VISIBLE);
        } else {
            if (animate) {
                mProgressContainer.startAnimation(AnimationUtils.loadAnimation(
                        mList.getContext(), android.R.anim.fade_in));
                mListContainer.startAnimation(AnimationUtils.loadAnimation(
                        mList.getContext(), android.R.anim.fade_out));
            } else {
                mProgressContainer.clearAnimation();
                mListContainer.clearAnimation();
            }
            mProgressContainer.setVisibility(View.VISIBLE);
            mListContainer.setVisibility(View.GONE);
        }
    }

    /**
     * Get the ListAdapter associated with this fragment's ListView.
     */
    public BaseAdapter getListAdapter() {
        return mAdapter;
    }

    private void ensureList() {
        if (mList != null) {
            return;
        }
        View root = getView();
        if (root == null) {
            throw new IllegalStateException("Content view not yet created");
        }
        if (root instanceof ListView) {
            mList = (ListView) root;
        } else {
            mStandardEmptyView = (TextView) root.findViewById(R.id.emptyTextView);
            if (mStandardEmptyView == null) {
                mEmptyView = root.findViewById(android.R.id.empty);
            } else {
                mStandardEmptyView.setVisibility(View.GONE);
            }
            mProgressContainer = root.findViewById(R.id.progressContainer);
            mListContainer = root.findViewById(R.id.listContainer);
            View rawListView = root.findViewById(android.R.id.list);
            if (!(rawListView instanceof ListView)) {
                throw new RuntimeException(
                        "Content has view with id attribute 'android.R.id.list' "
                                + "that is not a ListView class");
            }
            mList = (ListView) rawListView;
            if (mEmptyView != null) {
                mList.setEmptyView(mEmptyView);
            } else if (mEmptyText != null) {
                mStandardEmptyView.setText(mEmptyText);
                mList.setEmptyView(mStandardEmptyView);
            }
        }
        mList.setOnItemClickListener(mOnItemClickListener);
        if (mAdapter != null) {
            BaseAdapter adapter = mAdapter;
            mAdapter = null;
            setListAdapter(adapter);
        } else {
            // We are starting without an mAdapter, so assume we won't
            // have our data right away and start with the progress indicator.
            if (mProgressContainer != null) {
                setListShown(false, false);
            }
        }
        mHandler.post(mRequestFocus);
        setupList();
    }


    private void setupList() {
        if (backTopButton != null) return;
        View rootView = getView();
        if (rootView == null) {
            throw new IllegalStateException("Can't be used with a custom content view");
        }
        mRefreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.refresh);
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.setEnabled(onRefreshListener != null);

        mStatusFooter = new StatusFooter(rootView.getContext());

        mList = (ListView) rootView.findViewById(android.R.id.list);
        backTopButton = (ImageButton) rootView.findViewById(R.id.up);
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

        if (onUpLoadListener != null) mList.addFooterView(mStatusFooter);
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
                            onUpLoadListener.onUpLoad(GestureListFragment.this);
                        }
                        if (backTopButton != null) {
                            if (bottomPosition > 30) {
                                backTopButton.setVisibility(View.VISIBLE);
                            } else {
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
        setEmptyText(getString(R.string.empty));
    }

    @Override
    public void setOnRefreshListener(OnRefreshListener onRefreshListener) {
        this.onRefreshListener = onRefreshListener;
        if (mRefreshLayout != null) {
            if (onRefreshListener != null) {
                mRefreshLayout.setEnabled(true);
            } else {
                mRefreshLayout.setEnabled(false);
            }
        }
    }

    @Override
    public void setOnUpLoadListener(OnUpLoadListener onUpLoadListener) {
        this.onUpLoadListener = onUpLoadListener;
        if (mList != null && mList.getFooterViewsCount() < 1) {
            mList.addFooterView(mStatusFooter, null, false);
        }
    }

    @Override
    public void onRefresh() {
        if (onRefreshListener != null && mAdapter != null) {
            onRefreshListener.onRefresh(this);
        }
    }

    @Override
    public void updateStatus(int status) {
        if (mAdapter != null) {
            mAdapter.notifyDataSetChanged();
        }
        if (mRefreshLayout != null) {
            mRefreshLayout.setRefreshing(false);
        }
        mStatusFooter.updateStatus(status);
        hasMore = status != Status.LOAD_COMPLETE;
        isUpLoading = false;
    }
}
