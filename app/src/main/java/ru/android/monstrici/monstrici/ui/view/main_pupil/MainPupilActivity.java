package ru.android.monstrici.monstrici.ui.view.main_pupil;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;
import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.data.model.User;
import ru.android.monstrici.monstrici.presentation.presenter.main_pupil.MainMenuPresenter;
import ru.android.monstrici.monstrici.presentation.view.menu.IMainMenu;
import ru.android.monstrici.monstrici.ui.view.base.BaseActivity;
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

public class MainPupilActivity extends BaseActivity implements IMainMenu {
    private static final String USER_ID = "user_id";
    private String mUserId;
    @BindView(R.id.bottom_navigation)
    AHBottomNavigation mBottomNavigationView;
    @InjectPresenter
    public MainMenuPresenter mPresenter;
    @BindView(R.id.view_stub)
    ViewStub mViewStub;
    @BindView(R.id.ll)
    LinearLayout mLinearLayout;
    @BindView(R.id.view_toolbar)
    View mViewToolbar;

    //@BindView(R.id.tv_name)
    TextView mTvMonsterName;
    //@BindView(R.id.tv_donut_num)
    TextView mTvDonutNum;
    //@BindView(R.id.iv_donut)
    ImageView mIvDonut;
    //@BindView(R.id.iv_fragment_logo)
    ImageView mIvFragmentLogo;
    //@BindView(R.id.tv_fragment_title)
    TextView mTvFragmentTitle;

    private int mMainToolbar = R.layout.view_toolbar_main;
    private int mUsualToolbar = R.layout.view_toolbar;
    private int mCurrentToolbar = mMainToolbar;

    private FragmentManager mFragmentManager;

    public static Intent newIntent(Context packageContext, String id) {
        Intent intent = new Intent(packageContext, MainPupilActivity.class);
        intent.putExtra(USER_ID, id);
        return intent;
    }

    @ProvidePresenter
    public MainMenuPresenter providePresenter() {
        MainMenuPresenter presenter = new MainMenuPresenter();
        getApplicationComponent().inject(presenter);
        return presenter;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUserId=getIntent().getStringExtra(USER_ID);
        start();
    }

    private void getIntentValues() {
        mTvMonsterName = (TextView) findViewById(R.id.tv_name);
        mTvDonutNum = (TextView) findViewById(R.id.tv_donut_num);
        mIvDonut = (ImageView) findViewById(R.id.iv_donut);
        mIvDonut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSweetsFragment();
            }
        });
        String monsterName = getIntent().getStringExtra(Resources.MONSTER_NAME);
        if (monsterName == null) {
            mTvMonsterName.setText("Брозябр");
        } else
            mTvMonsterName.setText(monsterName);
        //TODO: change to real data values
        mTvDonutNum.setText("10");
    }

    @Override
    public void init() {

        for (int i=0; i<Resources.mMainPupilDrawables.length; i++){
            AHBottomNavigationItem item = new AHBottomNavigationItem("",
                    Resources.mMainPupilDrawables[i], R.color.color_selected_item);
            mBottomNavigationView.addItem(item);
        }

        mBottomNavigationView.setDefaultBackgroundColor(getApplicationContext().getResources().getColor(R.color.color_toolbar));
        mBottomNavigationView.setAccentColor(getApplicationContext().getResources().getColor(R.color.color_selected_item));
        mBottomNavigationView.setInactiveColor(getApplicationContext().getResources().getColor(R.color.color_unselected_item));
        mBottomNavigationView.setTitleState(AHBottomNavigation.TitleState.ALWAYS_HIDE);

        mFragmentManager = getSupportFragmentManager();

        mViewStub.setLayoutResource(R.layout.view_toolbar_main);
        mViewStub.inflate();
        setMonsterFragment();

        mBottomNavigationView.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {

                switch (position) {
                    case 0:
                        setMonsterFragment();
                        break;
                    case 1:
                        PrizesFragment prizesFragment = new PrizesFragment();
                        mFragmentManager.beginTransaction().replace(R.id.fl_main, prizesFragment).commit();
                        setToolbar(mUsualToolbar, prizesFragment);
                        break;
                    case 2:
                        setSweetsFragment();
                        break;
                    case 3:
                        SettingsFragment settingsFragment = new SettingsFragment();
                        mFragmentManager.beginTransaction().replace(R.id.fl_main, settingsFragment).commit();
                        setToolbar(mUsualToolbar, settingsFragment);
                        break;
                    case 4:
                        StarFragment starFragment = new StarFragment();
                        mFragmentManager.beginTransaction().replace(R.id.fl_main,
                                starFragment).commit();
                        setToolbar(mUsualToolbar, starFragment);
                        break;
                }
                return true;
            }
        });
        mBottomNavigationView.setOnNavigationPositionListener(new AHBottomNavigation.OnNavigationPositionListener() {
            @Override
            public void onPositionChange(int y) {

            }
        });
    }

    private void setSweetsFragment(){
        SweetsFragment sweetsFragment = new SweetsFragment();
        mFragmentManager.beginTransaction().replace(R.id.fl_main, sweetsFragment).commit();
        setToolbar(mUsualToolbar, sweetsFragment);
    }

    private void setMonsterFragment() {
        MonsterFragment monsterFragment = MonsterFragment.newInstance(getIntent().getIntExtra(Resources.MONSTER_IMAGE, 0));
        mFragmentManager.beginTransaction().replace(R.id.fl_main, monsterFragment).commit();
        setToolbar(mMainToolbar, null, monsterFragment);
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
            /*Glide.with(this).load(baseFragmentWithToolbar.getToolbarImage()).
                    into(mIvFragmentLogo);*/
            mIvFragmentLogo.setImageResource(baseFragmentWithToolbar.getToolbarImage());
            mTvFragmentTitle.setText(getString(baseFragmentWithToolbar.getToolbarTitle()));

        }

        if (id == mMainToolbar) {
            mViewToolbar.setBackground(getResources().getDrawable(R.drawable.toolbar_full));

            //TODO: change to real data values
            getIntentValues();
        }

    }


    @Override
    public void onUsersGet(List<User> users) {

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
}

