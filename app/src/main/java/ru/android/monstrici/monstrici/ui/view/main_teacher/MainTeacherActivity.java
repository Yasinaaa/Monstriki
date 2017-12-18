package ru.android.monstrici.monstrici.ui.view.main_teacher;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.data.model.Star;
import ru.android.monstrici.monstrici.data.model.User;
import ru.android.monstrici.monstrici.presentation.presenter.main.TeacherMenuPresenter;
import ru.android.monstrici.monstrici.presentation.view.menu.ITeacherMenu;
import ru.android.monstrici.monstrici.ui.view.authorisation.AuthorisationActivity;
import ru.android.monstrici.monstrici.ui.view.base.BaseActivity;
import ru.android.monstrici.monstrici.ui.view.base.BaseFragment;
import ru.android.monstrici.monstrici.ui.view.main_teacher.fragments.FormParametersFragment;
import ru.android.monstrici.monstrici.ui.view.main_teacher.fragments.JournalFragment;
import ru.android.monstrici.monstrici.utils.Message;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainTeacherActivity extends BaseActivity implements ITeacherMenu {

    private static final String USER_ID = "user_id";
    private String mUserId;
    //@BindView(R.id.include_toolbar)
    //View mToolbar;
    @InjectPresenter
    public TeacherMenuPresenter mPresenter;


    public static Intent newIntent(Context packageContext, String id) {
        Intent intent = new Intent(packageContext, MainTeacherActivity.class);
        intent.putExtra(USER_ID, id);
        return intent;
    }

    @Override
    public void onUsersGet(User user) {
        mNavigationViewsItems.mTvTeacherName.setText(user.getName());
    }

    @Override
    public void onStarsGet(List<Star> stars) {

    }

    @Override
    public void showLoading(boolean flag) {

    }

    @Override
    public void showError(Message message) {

    }

    @ProvidePresenter
    public TeacherMenuPresenter providePresenter() {
        TeacherMenuPresenter presenter = new TeacherMenuPresenter();
        getApplicationComponent().inject(presenter);
        return presenter;
    }

    class NavigationViewsItems {
        @BindView(R.id.tv_teacher_name)
        TextView mTvTeacherName;
        @BindView(R.id.tv_fill_today)
        TextView mTvFillToday;
        @BindView(R.id.tv_look_form)
        TextView mTvLookForm;
        @BindView(R.id.tv_exit)
        TextView mExit;

        NavigationViewsItems(View view) {
            ButterKnife.bind(this, view);
        }

        @OnClick(R.id.tv_fill_today)
        public void onFillTodayClick() {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            setFragment(JournalFragment.newInstance());
        }

        @OnClick(R.id.tv_look_form)
        public void onLookForm() {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            setFragment(FormParametersFragment.newInstance(true));
        }

        @OnClick(R.id.tv_exit)
        public void onExit() {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(MainTeacherActivity.this,
                    AuthorisationActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
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

    static class Toolbar {
        @BindView(R.id.iv_menu_item)
        ImageButton mIbMenuItem;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_teacher);
        mUserId = getIntent().getStringExtra(USER_ID);
        mPresenter.getUser(mUserId);
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
        mNavigationViewsItems = new NavigationViewsItems(mNavigationView.getHeaderView(0));
        mIvMenuItem = (ImageView) findViewById(R.id.iv_menu_item);
        mFragmentManager = getSupportFragmentManager();
        setFragment(JournalFragment.newInstance());
        mIvMenuItem.setVisibility(View.VISIBLE);
        mToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(mToggle);
        mToggle.syncState();
    }

    @OnClick(R.id.iv_menu_item)
    public void onMenuToggleClick() {
        mDrawerLayout.openDrawer(GravityCompat.START);
    }

    private void setFragment(BaseFragment baseFragment) {
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
