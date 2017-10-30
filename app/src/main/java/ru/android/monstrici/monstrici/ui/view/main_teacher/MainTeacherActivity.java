package ru.android.monstrici.monstrici.ui.view.main_teacher;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.ui.view.base.BaseActivity;
import ru.android.monstrici.monstrici.ui.view.base.BaseFragment;
import ru.android.monstrici.monstrici.ui.view.main_teacher.fragments.JournalFragment;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainTeacherActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    //@BindView(R.id.include_toolbar)
    //View mToolbar;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;
    ImageView mIvMenuItem;
    TextView mTvTeacherName;

    private ActionBarDrawerToggle mToggle;
    private FragmentManager mFragmentManager;

    static class Toolbar{
        @BindView(R.id.iv_menu_item)
        ImageButton mIbMenuItem;
    }

    static class ToolbarItems{

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_teacher);
        start();
    }

    @Override
    public void onBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_teacher, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

        /*if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }*/

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void init() {

        mIvMenuItem = (ImageView) findViewById(R.id.iv_menu_item);
        mFragmentManager = getSupportFragmentManager();
        setFragment(new JournalFragment());
        mIvMenuItem.setVisibility(View.VISIBLE);
        mToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(mToggle);
        mToggle.syncState();
        mNavigationView.setNavigationItemSelectedListener(this);
    }

    @OnClick(R.id.iv_menu_item)
    public void onMenuToggleClick(){
        mDrawerLayout.openDrawer(GravityCompat.START);
    }

    private void setFragment(BaseFragment baseFragment){
        mFragmentManager.beginTransaction().replace(R.id.fl_main, baseFragment).commit();
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void setTag() {
        TAG = "MainTeacherActivity";
    }
}
