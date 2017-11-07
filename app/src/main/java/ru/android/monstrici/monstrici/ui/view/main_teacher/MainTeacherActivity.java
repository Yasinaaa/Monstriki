package ru.android.monstrici.monstrici.ui.view.main_teacher;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.ui.view.authorisation.AuthorisationActivity;
import ru.android.monstrici.monstrici.ui.view.base.BaseActivity;
import ru.android.monstrici.monstrici.ui.view.base.BaseFragment;
import ru.android.monstrici.monstrici.ui.view.main_teacher.fragments.FormParametersFragment;
import ru.android.monstrici.monstrici.ui.view.main_teacher.fragments.JournalFragment;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainTeacherActivity extends BaseActivity {

    //@BindView(R.id.include_toolbar)
    //View mToolbar;

    public class NavigationViewsItems{
        @BindView(R.id.tv_teacher_name)
        TextView mTvTeacherName;
        @BindView(R.id.tv_fill_today)
        TextView mTvFillToday;
        @BindView(R.id.tv_look_form)
        TextView mTvLookForm;
        @BindView(R.id.tv_settings)
        TextView mSettings;
        @BindView(R.id.tv_exit)
        TextView mExit;

        public NavigationViewsItems(View view) {
            ButterKnife.bind(this,view);
        }

        @OnClick(R.id.tv_fill_today)
        public void onFillTodayClick(){
            mDrawerLayout.closeDrawer(GravityCompat.START);
            setFragment(new JournalFragment());
        }

        @OnClick(R.id.tv_look_form)
        public void onLookForm(){
            mDrawerLayout.closeDrawer(GravityCompat.START);
            setFragment(new FormParametersFragment());
        }
        @OnClick(R.id.tv_settings)
        public void onSettings(){
            mDrawerLayout.closeDrawer(GravityCompat.START);

        }
        @OnClick(R.id.tv_exit)
        public void onExit(){
            mDrawerLayout.closeDrawer(GravityCompat.START);
            Intent intent = new Intent(MainTeacherActivity.this,
                    AuthorisationActivity.class);
            startActivity(intent);
        }
    }

    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_view)
    NavigationView mNavigationView;
    ImageView mIvMenuItem;

    private ActionBarDrawerToggle mToggle;
    private FragmentManager mFragmentManager;
    private NavigationViewsItems mNavigationViewsItems;

    static class Toolbar{
        @BindView(R.id.iv_menu_item)
        ImageButton mIbMenuItem;
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
    public void init() {
        mNavigationViewsItems = new NavigationViewsItems( mNavigationView.getHeaderView(0));
        //TODO: change to real values
        mNavigationViewsItems.mTvTeacherName.setText("Мария Ивановна");

        mIvMenuItem = (ImageView) findViewById(R.id.iv_menu_item);
        mFragmentManager = getSupportFragmentManager();
        setFragment(new JournalFragment());
        mIvMenuItem.setVisibility(View.VISIBLE);
        mToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(mToggle);
        mToggle.syncState();
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
