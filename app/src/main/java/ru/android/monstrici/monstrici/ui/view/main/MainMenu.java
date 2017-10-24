package ru.android.monstrici.monstrici.ui.view.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;

import com.aurelhubert.ahbottomnavigation.AHBottomNavigation;
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem;

import butterknife.BindView;
import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.ui.view.base.BaseActivity;

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

        mBottomNavigationView.setOnTabSelectedListener(new AHBottomNavigation.OnTabSelectedListener() {
            @Override
            public boolean onTabSelected(int position, boolean wasSelected) {

                return true;
            }
        });
        mBottomNavigationView.setOnNavigationPositionListener(new AHBottomNavigation.OnNavigationPositionListener() {
            @Override
            public void onPositionChange(int y) {

            }
        });
    }

    @Override
    public void setTag() {
        TAG = MainMenu.class.getCanonicalName();
    }


}

