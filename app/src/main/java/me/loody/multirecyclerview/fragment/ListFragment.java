package me.loody.multirecyclerview.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.loody.multirecyclerview.MultiRecyclerView;
import me.loody.multirecyclerview.R;
import me.loody.multirecyclerview.adapter.RecyclerViewFooterAdapterImpl;

/**
 * A simple {@link Fragment} subclass.
 */
public class ListFragment extends Fragment {

    public static enum LAYOUT_MANAGER_TYPE {
        LINEAR,
        GRID,
        STAGGERED_GRID
    }

    @Bind(R.id.multirecyclerview)
    MultiRecyclerView mMultirecyclerview;

    private RecyclerViewFooterAdapterImpl mAdapter;
    private int type;
    private int mCount_insert = 0;
    private int mCount_more = 0;


    public static ListFragment newInstance(int type) {
        ListFragment fragment = new ListFragment();
        fragment.type = type;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_list, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (type == LAYOUT_MANAGER_TYPE.LINEAR.ordinal()) {
            mMultirecyclerview.setLayoutManager(new LinearLayoutManager(getContext()));
        } else if (type == LAYOUT_MANAGER_TYPE.GRID.ordinal()) {
            mMultirecyclerview.setLayoutManager(new GridLayoutManager(getContext(), 2));
        } else if (type == LAYOUT_MANAGER_TYPE.STAGGERED_GRID.ordinal()) {
            mMultirecyclerview.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        }
        mAdapter = new RecyclerViewFooterAdapterImpl(mMultirecyclerview.getRecyclerView(), getData());
        mMultirecyclerview.setOnLoadListener(new MultiRecyclerView.OnLoadListener() {
            @Override
            public void refresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.insertItem("insert " + (++mCount_insert));
                        mMultirecyclerview.setRefreshComplete();
                    }
                }, 1000);
            }

            @Override
            public void loadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.addItem("more " + (++mCount_more));
                        mMultirecyclerview.setRefreshComplete();
                    }
                }, 2000);
            }
        });
        mMultirecyclerview.setAdapter(mAdapter);
    }

    private List<String> getData() {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            strings.add("test data " + i);
        }
        return strings;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
