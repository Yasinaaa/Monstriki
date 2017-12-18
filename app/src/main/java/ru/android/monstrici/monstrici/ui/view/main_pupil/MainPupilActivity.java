package ru.android.monstrici.monstrici.ui.view.main_pupil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import butterknife.BindView;
import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.data.model.User;
import ru.android.monstrici.monstrici.presentation.presenter.main.PupilMenuPresenter;
import ru.android.monstrici.monstrici.presentation.view.menu.IPupilMenu;
import ru.android.monstrici.monstrici.ui.view.base.BaseActivity;
import ru.android.monstrici.monstrici.ui.view.base.BaseFragment;
import ru.android.monstrici.monstrici.ui.view.base.BaseFragmentUsualToolbar;
import ru.android.monstrici.monstrici.ui.view.main_pupil.fragments.MonsterFragment;
import ru.android.monstrici.monstrici.ui.view.main_pupil.fragments.PrizesFragment;
import ru.android.monstrici.monstrici.ui.view.main_pupil.fragments.SettingsFragment;
import ru.android.monstrici.monstrici.ui.view.main_pupil.fragments.StarFragment;
import ru.android.monstrici.monstrici.ui.view.main_pupil.fragments.SweetsFragment;
import ru.android.monstrici.monstrici.utils.Message;
import ru.android.monstrici.monstrici.utils.Resources;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by yasina on 16.10.17.
 */

public class MainPupilActivity extends BaseActivity
        implements IPupilMenu, MonsterFragment.IActivityCallback,
        SwipeRefreshLayout.OnRefreshListener {

    private static final String USER_ID = "user_id";
    private static final String USER_POSITION = "user_position";
    private String mUserId;
    @BindView(R.id.bottom_navigation)
    AHBottomNavigation mBottomNavigationView;
    @InjectPresenter
    public PupilMenuPresenter mPresenter;
    @BindView(R.id.view_stub)
    ViewStub mViewStub;
    @BindView(R.id.ll)
    LinearLayout mLinearLayout;
    @BindView(R.id.toolbar)
    Toolbar mViewToolbar;
    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    TextView mTvMonsterName;
    TextView mTvDonutNum;
    ImageView mIvDonut;
    ImageView mIvFragmentLogo;
    TextView mTvFragmentTitle;

    private int mMainToolbar = R.layout.view_toolbar_main;
    private int mUsualToolbar = R.layout.view_toolbar;
    private int mCurrentToolbar = mMainToolbar;
    private int mCurrentPosition = 0;
    private BaseFragmentUsualToolbar mCurrentFragment = null;

    private FragmentManager mFragmentManager;

    public static Intent newIntent(Context packageContext, String id) {
        Intent intent = new Intent(packageContext, MainPupilActivity.class);
        intent.putExtra(USER_ID, id);
        return intent;
    }

    public static Intent newIntent(Context packageContext, String id, int pos) {
        Intent intent = newIntent(packageContext, id);
        intent.putExtra(USER_POSITION, pos);
        return intent;
    }

    @ProvidePresenter
    public PupilMenuPresenter providePresenter() {
        PupilMenuPresenter presenter = new PupilMenuPresenter();
        getApplicationComponent().inject(presenter);
        return presenter;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUserId = getIntent().getStringExtra(USER_ID);
        mCurrentPosition = getIntent().getIntExtra(USER_POSITION, 0);
        mPresenter.getUser(mUserId);
        start();
    }

    @Override
    public void init() {
        for (int i = 0; i < Resources.mMainPupilDrawables.length; i++) {
            AHBottomNavigationItem item = new AHBottomNavigationItem("",
                    Resources.mMainPupilDrawables[i], R.color.color_selected_item);
            mBottomNavigationView.addItem(item);
        }

        mBottomNavigationView.setDefaultBackgroundColor(getApplicationContext().getResources().getColor(R.color.color_toolbar));
        mBottomNavigationView.setAccentColor(getApplicationContext().getResources().getColor(R.color.color_selected_item));
        mBottomNavigationView.setInactiveColor(getApplicationContext().getResources().getColor(R.color.color_unselected_item));
        mBottomNavigationView.setTitleState(AHBottomNavigation.TitleState.ALWAYS_HIDE);

        mSwipeRefreshLayout.setOnRefreshListener(this);
        mFragmentManager = getSupportFragmentManager();

        mViewStub.setLayoutResource(R.layout.view_toolbar_main);
        mViewStub.inflate();

        setFragmentByPosition(mCurrentPosition);

        mBottomNavigationView.setOnTabSelectedListener((position, wasSelected) -> {
            setFragmentByPosition(position);
            return true;
        });
        mBottomNavigationView.setOnNavigationPositionListener(y -> {

        });
    }

    private void setFragmentByPosition(int position){

        mCurrentPosition = position;
        switch (position) {
            case 0:
                setMonsterFragment();
                break;
            case 1:
                mCurrentFragment = PrizesFragment.newInstance(true);
                break;
            case 2:
                mCurrentFragment = new SweetsFragment();
                break;
            case 3:
                mCurrentFragment = new StarFragment();
                break;
            case 4:
                mCurrentFragment = new SettingsFragment();
                break;
        }
        if (mCurrentFragment != null){
            mFragmentManager.beginTransaction().replace(R.id.fl_main,
                    mCurrentFragment).commit();
            setToolbar(mUsualToolbar, mCurrentFragment);
        }
    }

    private void setFragment(BaseFragmentUsualToolbar mCurrentFragment) {
        mFragmentManager.beginTransaction().
                replace(R.id.fl_main, mCurrentFragment).commit();
        setToolbar(mUsualToolbar, mCurrentFragment);
    }

    private void setMonsterFragment() {
        mPresenter.getStars();
    }

    @Override
    public void setTag() {
        TAG = MainPupilActivity.class.getCanonicalName();
    }

    private void setToolbar(int id, BaseFragmentUsualToolbar baseFragmentWithToolbar) {
        setToolbar(id, baseFragmentWithToolbar, null);
    }

    private void setToolbar(int id, BaseFragmentUsualToolbar baseFragmentWithToolbar, MonsterFragment monsterFragment) {
        if (id != mCurrentToolbar) {
            mLinearLayout.removeAllViews();
            mLinearLayout.addView(LayoutInflater.from(getApplicationContext()).inflate(id,
                    mLinearLayout, false));
            mCurrentToolbar = id;

            if (id == mUsualToolbar) {
                mViewToolbar.setBackgroundColor(getResources().getColor(R.color.color_toolbar_brown));
            }

        }
        if (id == mUsualToolbar && baseFragmentWithToolbar != null) {
            mIvFragmentLogo = (ImageView) findViewById(R.id.iv_fragment_logo);
            mTvFragmentTitle = (TextView) findViewById(R.id.tv_fragment_title);

            mIvFragmentLogo.setImageResource(baseFragmentWithToolbar.getToolbarImage());
            mTvFragmentTitle.setText(getString(baseFragmentWithToolbar.getToolbarTitle()));

        }

        if (id == mMainToolbar) {
            mViewToolbar.setBackground(getResources().getDrawable(R.drawable.toolbar_full));
        }

    }

    @Override
    public void onUsersGet(User user) {
        mTvDonutNum = (TextView) findViewById(R.id.tv_donut_num);
        mIvDonut = (ImageView) findViewById(R.id.iv_donut);
        mCurrentFragment = new SweetsFragment();
        mIvDonut.setOnClickListener(v -> setFragment(mCurrentFragment));
        //mPresenter.getStars();
        mTvDonutNum.setText(String.valueOf(user.getStarStorage().getStarsCount()));

    }

    @Override
    public void setStars(User user) {
        mTvDonutNum.setText(String.valueOf(user.getStarStorage().getStarsCount()));
    }

    @Override
    public void setLastDonutsReceiveDate(long date) {
        MonsterFragment monsterFragment = MonsterFragment.newInstance(date);
        mFragmentManager.beginTransaction().replace(R.id.fl_main, monsterFragment).commit();
        setToolbar(mMainToolbar, null, monsterFragment);
    }

    @Override
    public void showLoading(boolean flag) {

    }

    @Override
    public void showError(Message message) {

    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    public void monsterNameUpdate(String name) {
        mTvMonsterName = (TextView) findViewById(R.id.tv_name);
        mTvMonsterName.setText(name);
        mPresenter.getUser(mUserId);
    }

    @Override
    public void onRefresh() {
        setFragmentByPosition(mCurrentPosition);
        mSwipeRefreshLayout.setRefreshing(false);
    }
}

