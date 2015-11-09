package me.loody.multirecyclerview;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.loody.multirecyclerview.adapter.BaseFragmentPagerAdapter;
import me.loody.multirecyclerview.fragment.ListFragment;

public class MainActivity extends AppCompatActivity {
    @Bind(R.id.viewpager)
    ViewPager viewpager;
    @Bind(R.id.tabs)
    TabLayout tabs;

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
        BaseFragmentPagerAdapter baseFragmentPagerAdapter = new BaseFragmentPagerAdapter(getSupportFragmentManager());
        baseFragmentPagerAdapter.addFragment(ListFragment.newInstance(ListFragment.LAYOUT_MANAGER_TYPE.LINEAR.ordinal()), ListFragment.LAYOUT_MANAGER_TYPE.LINEAR.name());
        baseFragmentPagerAdapter.addFragment(ListFragment.newInstance(ListFragment.LAYOUT_MANAGER_TYPE.GRID.ordinal()), ListFragment.LAYOUT_MANAGER_TYPE.GRID.name());
        baseFragmentPagerAdapter.addFragment(ListFragment.newInstance(ListFragment.LAYOUT_MANAGER_TYPE.STAGGERED_GRID.ordinal()), ListFragment.LAYOUT_MANAGER_TYPE.STAGGERED_GRID.name());
        viewpager.setAdapter(baseFragmentPagerAdapter);
        viewpager.setOffscreenPageLimit(baseFragmentPagerAdapter.getCount());
        tabs.setupWithViewPager(viewpager);
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
