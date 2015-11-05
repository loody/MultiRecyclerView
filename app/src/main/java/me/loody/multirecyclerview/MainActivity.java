package me.loody.multirecyclerview;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.loody.multirecyclerview.adapter.RecyclerViewFooterAdapterImpl;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.multirecyclerview)
    MultiRecyclerView mMultiRecyclerView;
    RecyclerViewFooterAdapterImpl mAdapter;
    private int mCount_insert = 0;
    private int mCount_more = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mAdapter = new RecyclerViewFooterAdapterImpl(mMultiRecyclerView, getData());
        mMultiRecyclerView.setOnLoadListener(new MultiRecyclerView.OnLoadListener() {
            @Override
            public void refresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.insertItem("insert " + (++mCount_insert));
                        mMultiRecyclerView.setRefreshComplete();
                    }
                }, 1000);
            }

            @Override
            public void loadMore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.addItem("more " + (++mCount_more));
                        mMultiRecyclerView.setRefreshComplete();
                    }
                }, 2000);
            }
        });
        mMultiRecyclerView.setAdapter(mAdapter);
    }

    private List<String> getData() {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            strings.add("test data " + i);
        }
        return strings;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
