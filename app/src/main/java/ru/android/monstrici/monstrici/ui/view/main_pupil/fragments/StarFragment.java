package ru.android.monstrici.monstrici.ui.view.main_pupil.fragments;

import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.data.model.Monster;
import ru.android.monstrici.monstrici.data.model.User;
import ru.android.monstrici.monstrici.domain.core.dagger.component.AppComponent;
import ru.android.monstrici.monstrici.domain.core.dagger.component.CoreComponent;
import ru.android.monstrici.monstrici.presentation.adapter.RateAdapter;
import ru.android.monstrici.monstrici.presentation.model.Rate;
import ru.android.monstrici.monstrici.presentation.presenter.star.StarPresenter;
import ru.android.monstrici.monstrici.presentation.view.star.IStarView;
import ru.android.monstrici.monstrici.ui.view.base.BaseFragment;
import ru.android.monstrici.monstrici.ui.view.base.BaseFragmentUsualToolbar;
import ru.android.monstrici.monstrici.utils.Message;

/**
 * Created by yasina on 19.11.17.
 */

public class StarFragment extends BaseFragmentUsualToolbar implements IStarView {

    public static int TOOLBAR_IMAGE = R.drawable.star_icon_transparent;
    public static int TOOLBAR_TITLE = R.string.star;
    @BindView(R.id.tv_rate)
    TextView mTvRate;
    @BindView(R.id.tv_hall_of_fame)
    TextView mTvHallOfFame;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    @InjectPresenter
    StarPresenter mPresenter;
    private int mMonsterImageId = 0;
    private TabsPagerAdapter mTabsPagerAdapter;
    private static ArrayList<Rate> mRateList;
    private boolean isFinishLoadingMonsters = false;

    @ProvidePresenter
    public StarPresenter providePresenter() {
        StarPresenter presenter = new StarPresenter();
        getComponent(AppComponent.class).inject(presenter);
        return presenter;
    }

    public StarFragment() {
        super(TOOLBAR_IMAGE, TOOLBAR_TITLE);
    }

    public static StarFragment newInstance(){
        Bundle args = new Bundle();
        StarFragment newFragment = new StarFragment();
        newFragment.setArguments(args);
        return newFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent(CoreComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        createLayout(inflater, container, R.layout.fragment_star);
        return mView;
    }

    @Override
    public void init() {
        mPresenter.getAllUsers();
        mRateList = new ArrayList<Rate>();
    }

    @OnClick(R.id.tv_hall_of_fame)
    protected void onHallOfFameClickListener(){
        mViewPager.setCurrentItem(1);
        setUnderline(mTvHallOfFame, mTvRate);
    }

    @OnClick(R.id.tv_rate)
    protected void onRateClickListener(){
        mViewPager.setCurrentItem(0);
        setUnderline(mTvRate, mTvHallOfFame);
    }

    private void setUnderline(TextView underlineTextView, TextView normalTextView){
        underlineTextView.setPaintFlags(underlineTextView.getPaintFlags()|
                Paint.UNDERLINE_TEXT_FLAG);
        underlineTextView.setText(underlineTextView.getText());
        normalTextView.setPaintFlags(0);
        normalTextView.setText(normalTextView.getText());
    }

    @Override
    public void setTag() {
        TAG = "MonsterFragment";
    }

    private String mLastUserId;
    @Override
    public void getUsersRateList(List<User> users) {
        mLastUserId = users.get(users.size()-1).getId();
        for (User user: users){
            mPresenter.getAllUserInformation(user);
        }
    }

    @Override
    public void getChoosedUser(User user, Monster monster) {
        mRateList.add(new Rate(monster.getName(),
                R.drawable.m1, user.getStarStorage().getStarsCount()));
        if (user.getId().equals(mLastUserId)){
            initViewPager();
        }
    }

    private void initViewPager(){
        mTabsPagerAdapter = new TabsPagerAdapter(getChildFragmentManager());
        mViewPager.setAdapter(mTabsPagerAdapter);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int state) {
                if (state == 0){
                    mViewPager.setCurrentItem(0);
                    setUnderline(mTvRate, mTvHallOfFame);
                }else if (state == 1){
                    mViewPager.setCurrentItem(1);
                    setUnderline(mTvHallOfFame, mTvRate);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mViewPager.setCurrentItem(0);
        setUnderline(mTvRate, mTvHallOfFame);
    }

    @Override
    public void showLoading(boolean flag) {

    }

    @Override
    public void showError(Message message) {

    }

    public class TabsPagerAdapter extends FragmentStatePagerAdapter {

        public RateFragment mRateFragment;
        public HallOfFameFragment mHallOfFameFragment;

        public TabsPagerAdapter(FragmentManager fm) {
            super(fm);
            mRateFragment = new RateFragment();
            mHallOfFameFragment = new HallOfFameFragment();
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Override
        public Fragment getItem(int index) {
            if (index == 0) {
                return mRateFragment;
            }
            else if (index == 1){
                return mHallOfFameFragment;
            }
            else {
                return null;
            }
        }
    }

    public static class RateFragment extends BaseFragment{

        @BindView(R.id.rv_rate)
        RecyclerView mRvRate;
        private RateAdapter mRateAdapter;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            createLayout(inflater, container, R.layout.fragment_rate);
            return mView;
        }

        @Override
        public void setTag() {

        }

        @Override
        public void init() {
            mRateAdapter = new RateAdapter(mRateList);
            mRvRate.setHasFixedSize(true);
            mRvRate.setLayoutManager(new LinearLayoutManager(getContext()));
            mRvRate.setAdapter(mRateAdapter);
        }
    }

    public static class HallOfFameFragment extends BaseFragment{

        //@BindView(R.id.rv_rate)
        //RecyclerView mRvRate;


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            createLayout(inflater, container, R.layout.fragment_hall_of_fame);
            return mView;
        }

        @Override
        public void setTag() {

        }

        @Override
        public void init() {
        }
    }
}

