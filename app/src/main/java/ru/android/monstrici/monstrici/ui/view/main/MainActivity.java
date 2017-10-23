package ru.android.monstrici.monstrici.ui.view.main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.ViewStub;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import butterknife.BindView;
import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.ui.view.base.BaseActivity;
import ru.android.monstrici.monstrici.ui.view.base.BaseFragmentWithToolbar;
import ru.android.monstrici.monstrici.ui.view.main.fragments.MonsterFragment;
import ru.android.monstrici.monstrici.ui.view.main.fragments.PrizesFragment;
import ru.android.monstrici.monstrici.ui.view.main.fragments.SettingsFragment;
import ru.android.monstrici.monstrici.ui.view.main.fragments.SweetsFragment;

/**
 * Created by yasina on 16.10.17.
 */

public class MainActivity extends BaseActivity {

    @BindView(R.id.bottom_navigation)
    AHBottomNavigation mBottomNavigationView;

    @BindView(R.id.view_stub)
    ViewStub mViewStub;
    @BindView(R.id.ll)
    LinearLayout mLinearLayout;

    //@BindView(R.id.iv_fragment_logo)
    ImageView mIvFragmentLogo;
    //@BindView(R.id.tv_fragment_title)
    TextView mTvFragmentTitle;

    private int mMainToolbar = R.layout.view_toolbar_main;
    private int mUsualToolbar = R.layout.view_toolbar;
    private int mCurrentToolbar;

    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start();
    }

    @Override
    public void init() {

        AHBottomNavigationItem item1 = new AHBottomNavigationItem("",
                R.drawable.main_icon, R.color.color_selected_item);
        AHBottomNavigationItem item2 = new AHBottomNavigationItem("",
                R.drawable.cup_icon, R.color.color_selected_item);
        AHBottomNavigationItem item3 = new AHBottomNavigationItem("",
                R.drawable.candy_icon,R.color.color_selected_item);
        AHBottomNavigationItem item4 = new AHBottomNavigationItem("",
                R.drawable.settings_icon,R.color.color_selected_item);

        mBottomNavigationView.addItem(item1);
        mBottomNavigationView.addItem(item2);
        mBottomNavigationView.addItem(item3);
        mBottomNavigationView.addItem(item4);
        mBottomNavigationView.setDefaultBackgroundColor(getApplicationContext().getResources().getColor(R.color.color_toolbar));
        mBottomNavigationView.setAccentColor(getApplicationContext().getResources().getColor(R.color.color_selected_item));
        mBottomNavigationView.setInactiveColor(getApplicationContext().getResources().getColor(R.color.color_unselected_item));
        mBottomNavigationView.setTitleState(AHBottomNavigation.TitleState.ALWAYS_HIDE);

        mFragmentManager = getSupportFragmentManager();
        mFragmentManager.beginTransaction().replace(R.id.fl_main, new MonsterFragment()).commit();

        mViewStub.setLayoutResource(R.layout.view_toolbar_main);
        mViewStub.inflate();

        mBottomNavigationView.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {

                switch (position){
                    case 0:
                        mFragmentManager.beginTransaction().replace(R.id.fl_main, new MonsterFragment()).commit();
                        setToolbar(mMainToolbar);
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
            @Override public void onPositionChange(int y) {

            }
        });
    }

    @Override
    public void setTag() {
        TAG = MainActivity.class.getCanonicalName();
    }

    private void setToolbar(int id){
        setToolbar(id, null);
    }

    private void setToolbar(int id, BaseFragmentWithToolbar baseFragmentWithToolbar){
        if (id != mCurrentToolbar) {
            mLinearLayout.removeAllViews();
            mLinearLayout.addView(LayoutInflater.from(getApplicationContext()).inflate(id,
                    mLinearLayout, false));
            mCurrentToolbar = id;
        }
        if (id == mUsualToolbar && baseFragmentWithToolbar != null){
            mIvFragmentLogo = (ImageView) findViewById(R.id.iv_fragment_logo);
            mTvFragmentTitle = (TextView) findViewById(R.id.tv_fragment_title);
            mIvFragmentLogo.setBackgroundResource(baseFragmentWithToolbar.getToolbarImage());
            mTvFragmentTitle.setText(getString(baseFragmentWithToolbar.getToolbarTitle()));
        }
    }

}

