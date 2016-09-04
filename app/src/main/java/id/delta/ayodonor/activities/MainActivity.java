package id.delta.ayodonor.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.delta.ayodonor.R;
import id.delta.ayodonor.fragment.PagerAdapter;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    @Bind(R.id.drawer_layout)DrawerLayout mDrawer;
    @Bind(R.id.navigation_view)NavigationView mNaview;
    @Bind(R.id.pager)ViewPager mPager;
    @Bind(R.id.tabs)TabLayout mTabs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.appbar_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_menu);

        mDrawer.setDrawerListener(new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open, R.string.drawer_close));
        mNaview.setNavigationItemSelectedListener(this);
        mPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        mTabs.setupWithViewPager(mPager);
        mTabs.setSelectedTabIndicatorColor(Color.TRANSPARENT);

    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        return false;
    }
}
