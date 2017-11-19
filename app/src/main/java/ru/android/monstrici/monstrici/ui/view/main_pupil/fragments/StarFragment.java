package ru.android.monstrici.monstrici.ui.view.main_pupil.fragments;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;
import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.presentation.adapter.RateAdapter;
import ru.android.monstrici.monstrici.presentation.model.Rate;
import ru.android.monstrici.monstrici.ui.view.base.BaseFragment;
import ru.android.monstrici.monstrici.ui.view.base.BaseFragmentUsualToolbar;
import ru.android.monstrici.monstrici.utils.Resources;

/**
 * Created by yasina on 19.11.17.
 */

public class StarFragment extends BaseFragmentUsualToolbar {

    public static int TOOLBAR_IMAGE = R.drawable.star_icon_transparent;
    public static int TOOLBAR_TITLE = R.string.star;
    @BindView(R.id.tv_rate)
    TextView mTvRate;
    @BindView(R.id.tv_hall_of_fame)
    TextView mTvHallOfFame;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    private int mMonsterImageId = 0;
    private TabsPagerAdapter mTabsPagerAdapter;

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
        if(getArguments() != null){
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        createLayout(inflater, container, R.layout.fragment_star);
        return mView;
    }

    @Override
    public void init() {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        mTabsPagerAdapter = new TabsPagerAdapter(fragmentManager);
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

    public class TabsPagerAdapter extends FragmentPagerAdapter {

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
        private ArrayList<Rate> mRateList;

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
            mRateList = new ArrayList<Rate>();

            //TODO: remove temp values
            for (int i=0; i<10; i++){
                mRateList.add(new Rate("Брозябр" + i,
                        R.drawable.m1, 10));
            }

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

