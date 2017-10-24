package ru.android.monstrici.monstrici.ui.view.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import butterknife.BindView;
import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.ui.view.base.BaseActivity;
import ru.android.monstrici.monstrici.ui.view.base.BaseFragment;
import ru.android.monstrici.monstrici.ui.view.base.BaseFragmentUsualToolbar;
import ru.android.monstrici.monstrici.ui.view.main.fragments.MonsterFragment;
import ru.android.monstrici.monstrici.ui.view.main.fragments.PrizesFragment;
import ru.android.monstrici.monstrici.ui.view.main.fragments.SettingsFragment;
import ru.android.monstrici.monstrici.ui.view.main.fragments.SweetsFragment;
import ru.android.monstrici.monstrici.ui.view.parameters.ParametersActivity;
import ru.android.monstrici.monstrici.utils.Resources;

/**
 * Created by yasina on 16.10.17.
 */

public class MainMenu extends BaseActivity {

    private static final String USER_ID = "user_id";
    @BindView(R.id.bottom_navigation)
    AHBottomNavigation mBottomNavigationView;

    public static Intent newIntent(Context packageContext, long id) {
        Intent intent = new Intent(packageContext, MainMenu.class);
        intent.putExtra(USER_ID, id);
        return intent;
    }

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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start();
    }

    private void getIntentValues(){
        mTvMonsterName = (TextView) findViewById(R.id.tv_name);
        mTvDonutNum = (TextView) findViewById(R.id.tv_donut_num);
        mIvDonut = (ImageView) findViewById(R.id.iv_donut);
        mTvMonsterName.setText(getIntent().getStringExtra(Resources.MONSTER_NAME));
        //TODO: change to real data values
        mTvDonutNum.setText("10");
    }

    @Override
    public void init() {
        AHBottomNavigationItem item1 = new AHBottomNavigationItem("",
                R.drawable.main_icon, R.color.color_selected_item);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("",
                R.drawable.cup_icon, R.color.color_selected_item);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("",
                R.drawable.candy_icon, R.color.color_selected_item);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem("",
                R.drawable.settings_icon, R.color.color_selected_item);

        mBottomNavigationView.addItem(item1);
        mBottomNavigationView.addItem(item2);
        mBottomNavigationView.addItem(item3);
        mBottomNavigationView.addItem(item4);
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

                switch (position){
                    case 0:
                        setMonsterFragment();
                        break;

                    case 1:
                        PrizesFragment prizesFragment = new PrizesFragment();
                        mFragmentManager.beginTransaction().replace(R.id.fl_main, prizesFragment).commit();
                        setToolbar(mUsualToolbar, prizesFragment);
                        break;
                    case 2:
                        SweetsFragment sweetsFragment = new SweetsFragment();
                        mFragmentManager.beginTransaction().replace(R.id.fl_main, sweetsFragment).commit();
                        setToolbar(mUsualToolbar, sweetsFragment);
                        break;
                    case 3:
                        SettingsFragment settingsFragment = new SettingsFragment();
                        mFragmentManager.beginTransaction().replace(R.id.fl_main, settingsFragment).commit();
                        setToolbar(mUsualToolbar, settingsFragment);
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

    private void setMonsterFragment(){
        MonsterFragment monsterFragment = MonsterFragment.newInstance(getIntent().getIntExtra(Resources.MONSTER_IMAGE, 0));
        mFragmentManager.beginTransaction().replace(R.id.fl_main, monsterFragment).commit();
        setToolbar(mMainToolbar, null, monsterFragment);
    }

    @Override
    public void setTag() {
        TAG = MainMenu.class.getCanonicalName();
    }

    private void setToolbar(int id, BaseFragmentUsualToolbar baseFragmentWithToolbar) {
        setToolbar(id, baseFragmentWithToolbar, null);
    }

    private void setToolbar(int id, BaseFragmentUsualToolbar baseFragmentWithToolbar, MonsterFragment monsterFragment){
        if (id != mCurrentToolbar) {
            mLinearLayout.removeAllViews();
            mLinearLayout.addView(LayoutInflater.from(getApplicationContext()).inflate(id,
                    mLinearLayout, false));
            mCurrentToolbar = id;

            if (id == mUsualToolbar){
                mViewToolbar.setBackgroundColor(getResources().getColor(R.color.color_toolbar_brown));
            }

        }else if (id == mUsualToolbar && baseFragmentWithToolbar != null){
            mIvFragmentLogo = (ImageView) findViewById(R.id.iv_fragment_logo);
            mTvFragmentTitle = (TextView) findViewById(R.id.tv_fragment_title);
            mIvFragmentLogo.setBackgroundResource(baseFragmentWithToolbar.getToolbarImage());
            mTvFragmentTitle.setText(getString(baseFragmentWithToolbar.getToolbarTitle()));

        }

        if(id == mMainToolbar){
            mViewToolbar.setBackground(getResources().getDrawable(R.drawable.toolbar_full));
            //TODO: change to real data values
            getIntentValues();
        }

    }

}

