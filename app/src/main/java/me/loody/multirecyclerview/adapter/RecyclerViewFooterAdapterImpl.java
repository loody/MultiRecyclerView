package me.loody.multirecyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import me.loody.multirecyclerview.MultiRecyclerView;
import me.loody.multirecyclerview.R;

/**
 * Created by mSobhy on 7/22/15.
 */
public class RecyclerViewFooterAdapterImpl<T> extends AbstractRecyclerViewFooterAdapter<T> {
    public RecyclerViewFooterAdapterImpl(MultiRecyclerView my_recyclerView, List<T> dataset) {
        super(my_recyclerView.getRecyclerView(), dataset);
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
