package me.loody.multirecyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import me.loody.multirecyclerview.R;

/**
 * Created by mSobhy on 7/22/15.
 */
public class RecyclerViewFooterAdapterImpl<T> extends AbstractRecyclerViewFooterAdapter<T> {
    private RecyclerView mRecyclerView;

    public RecyclerViewFooterAdapterImpl(RecyclerView recyclerView, List<T> dataset) {
        super(recyclerView, dataset);
        mRecyclerView = recyclerView;
    }

    @Override
    public RecyclerView.ViewHolder onCreateBasicItemViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list, parent, false);
        return new MyOwnHolder(v);
    }

    @Override
    public void onBindBasicItemView(RecyclerView.ViewHolder genericHolder, int position) {
        final MyOwnHolder holder = (MyOwnHolder) genericHolder;
        holder.mTextView.setText(getItem(position).toString());
        if (mRecyclerView.getLayoutManager() instanceof StaggeredGridLayoutManager) {
            if (position % 3 == 0) {
                holder.mTextView.setPadding(0, 20, 0, 20);
//            holder.mTextView.setBackgroundColor(Color.DKGRAY);
            } else if (position % 3 == 1) {
                holder.mTextView.setPadding(0, 80, 0, 80);
//            holder.mTextView.setBackgroundColor(Color.CYAN);
            } else if (position % 3 == 2) {
                holder.mTextView.setPadding(0, 130, 0, 130);
//            holder.mTextView.setBackgroundColor(Color.LTGRAY);

            }
        }
        // DO YOU BINDING MAGIC HERE
    }

    public static class MyOwnHolder extends RecyclerView.ViewHolder {

        public final View mView;
        public final TextView mTextView;

        public MyOwnHolder(View view) {
            super(view);
            mView = view;
            mTextView = (TextView) view.findViewById(R.id.txt_title);
        }
    }
}
