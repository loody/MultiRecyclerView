package me.loody.multirecyclerview;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import me.loody.multirecyclerview.adapter.AbstractRecyclerViewFooterAdapter;
import me.loody.multirecyclerview.adapter.RecyclerViewFooterAdapterImpl;


/**
 * Created by loody on 15/11/2.
 */
public class MultiRecyclerView extends FrameLayout implements AbstractRecyclerViewFooterAdapter.OnLoadMoreListener {

    private RecyclerView mRecyclerView;
    private SwipeRefreshLayout mSwipeLayout;
    private boolean isRefreshing;

    public void setItemAnimator(RecyclerView.ItemAnimator itemAnimator) {
        mItemAnimator = itemAnimator;
        mRecyclerView.setItemAnimator(mItemAnimator == null ? new DefaultItemAnimator() : mItemAnimator);
    }

    private RecyclerView.ItemAnimator mItemAnimator;

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        mLayoutManager = layoutManager;
        mRecyclerView.setLayoutManager(mLayoutManager == null ? new LinearLayoutManager(getContext()) : mLayoutManager);
    }

    private RecyclerView.LayoutManager mLayoutManager;

    public void setAdapter(RecyclerViewFooterAdapterImpl adapter) {
        mAdapter = adapter;
        if (mAdapter != null) {
            mAdapter.setOnLoadMoreListener(this);
            mRecyclerView.setAdapter(mAdapter);
        }
    }

    private RecyclerViewFooterAdapterImpl mAdapter;

    // listener
    private OnLoadListener mListener;

    public void setOnLoadListener(OnLoadListener listener) {
        mListener = listener;
    }

    public RecyclerView getRecyclerView() {
        return mRecyclerView;
    }

    public MultiRecyclerView(Context context) {
        this(context, null);
    }

    public MultiRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_multi_recyclerview, this, true);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_layout);
        addListener();
        mRecyclerView.setLayoutManager(mLayoutManager == null ? new LinearLayoutManager(getContext()) : mLayoutManager);
        mRecyclerView.setItemAnimator(mItemAnimator == null ? new DefaultItemAnimator() : mItemAnimator);
    }


    private void addListener() {
        mSwipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (mListener == null) {
                    mSwipeLayout.setRefreshing(false);
                    return;
                }
                if (isRefreshing) return;
                isRefreshing = true;
                mListener.refresh();
            }
        });
    }

    public void setRefreshComplete() {
        mSwipeLayout.setRefreshing(false);
        isRefreshing = false;
        if (mAdapter != null) {
            mAdapter.removeItem(null);
            mAdapter.setLoadingMore(false);
        }
    }

    @Override
    public void onLoadMore() {
        if (mListener != null)
            mListener.loadMore();
    }

    public interface OnLoadListener {
        /**
         * 下拉刷新加载
         */
        void refresh();

        /**
         * 上拉加载更多
         */
        void loadMore();
    }
}
