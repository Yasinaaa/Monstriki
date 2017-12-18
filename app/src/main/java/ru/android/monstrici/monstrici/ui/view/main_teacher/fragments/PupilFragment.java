package ru.android.monstrici.monstrici.ui.view.main_teacher.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import butterknife.OnClick;
import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.data.model.Star;
import ru.android.monstrici.monstrici.data.model.User;
import ru.android.monstrici.monstrici.domain.core.dagger.component.AppComponent;
import ru.android.monstrici.monstrici.domain.core.dagger.component.CoreComponent;
import ru.android.monstrici.monstrici.presentation.adapter.DaysOfWeekAdapter;
import ru.android.monstrici.monstrici.presentation.adapter.WeekDesitionsAdapter;
import ru.android.monstrici.monstrici.presentation.model.DayDesition;
import ru.android.monstrici.monstrici.presentation.model.DayOfWeek;
import ru.android.monstrici.monstrici.presentation.presenter.pupil.PupilPresenter;
import ru.android.monstrici.monstrici.presentation.presenter.sweets.SweetsPresenter;
import ru.android.monstrici.monstrici.presentation.view.pupil.IPupilView;
import ru.android.monstrici.monstrici.presentation.view.sweets.ISweetsView;
import ru.android.monstrici.monstrici.ui.view.base.BaseFragment;
import ru.android.monstrici.monstrici.utils.DateFunctions;
import ru.android.monstrici.monstrici.utils.Message;
import ru.android.monstrici.monstrici.utils.Resources;

/**
 * Created by yasina on 29.10.17.
 */

public class PupilFragment extends BaseFragment
        implements IPupilView, SwipeRefreshLayout.OnRefreshListener{

    public static final String PUPIL = "pupil";
    public static final String DATE_BEGIN = "pupil_begin_date";
    public static final String DATE_FINISH = "pupil_finish_date";

    @BindView(R.id.tv_pupil)
    TextView mTvPupil;
    @BindView(R.id.tv_data_bracket)
    TextView mTvDataBracket;
    @BindView(R.id.tv_data_day)
    TextView mTvDataDay;
    @BindView(R.id.rv_desitions_of_week)
    RecyclerView mRvDesitionsOfWeek;
    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout mSwipeRefreshLayout;

    private Date mStartDate, mFinishDate;
    private String mPupil;
    private String mDate;
    private ArrayList<Star> mStarsList;
    private DayDesition[] mDayDesitions;
    private WeekDesitionsAdapter mWeekDesitionsAdapter;

    @InjectPresenter
    PupilPresenter mPresenter;

    @ProvidePresenter
    public PupilPresenter providePresenter() {
        PupilPresenter presenter = new PupilPresenter();
        getComponent(AppComponent.class).inject(presenter);
        return presenter;
    }

    public PupilFragment() {
    }

    public static PupilFragment newInstance(String value){
        Bundle args = new Bundle();
        args.putString(PUPIL, value);
        PupilFragment newFragment = new PupilFragment();
        newFragment.setArguments(args);
        return newFragment;
    }

   /* public static PupilFragment newInstance(String value, String date){
        Bundle args = new Bundle();
        args.putString(PUPIL, value);
        PupilFragment newFragment = new PupilFragment();
        newFragment.setArguments(args);
        return newFragment;
    }*/

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            mPupil = getArguments().getString(PUPIL);
        }
        getComponent(CoreComponent.class).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        createLayout(inflater, container, R.layout.fragment_pupil);
        return mView;
    }

    @Override
    public void init() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mPresenter.getUser(mPupil);
    }

    @OnClick(R.id.tv_data_bracket)
    public void onDataBracketClick(){
        addFragment(DataFragment.newInstance(true), PupilFragment.this);
    }

    @Override
    public void setTag() {
        TAG = "PupilFragment";
    }

    @Override
    public void showLoading(boolean flag) {

    }

    @Override
    public void showError(Message message) {

    }

    @Override
    public void setDonutsCount(ArrayList<Star> starsList) {
        mDayDesitions = mPresenter.getDonutsCount(starsList, getActivity());
        mStarsList = starsList;

        if (mWeekDesitionsAdapter == null) {
            mWeekDesitionsAdapter = new WeekDesitionsAdapter(mDayDesitions);
            mRvDesitionsOfWeek.setLayoutManager(new LinearLayoutManager(getContext()));
            mRvDesitionsOfWeek.setAdapter(mWeekDesitionsAdapter);
        }
        else {
            mWeekDesitionsAdapter.updateAdapter(mDayDesitions);
        }
    }

    @Override
    public void setUser(User user) {
        mPresenter.getStars(user);
        mTvPupil.setText(user.getName() + " " + user.getSchoolClass().getNumber() +
        user.getSchoolClass().getLetter());
        mTvDataDay.setText(mPresenter.getWeek());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        mStartDate = new Date(data.getLongExtra(DATE_BEGIN, 0));
        mFinishDate = new Date(data.getLongExtra(DATE_FINISH, 0));
        mDate = Resources.DATE_FORMAT.format(mStartDate) + "-" +
                Resources.DATE_FORMAT.format(mFinishDate);
        mTvDataDay.setText(mDate);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(mStartDate);
        mDayDesitions = mPresenter.getDonutsCount(calendar, mStarsList, getActivity());

        mWeekDesitionsAdapter.setNewItems(mDayDesitions);
    }

    @Override
    public void onRefresh() {
        mPresenter.getUser(mPupil);
        mSwipeRefreshLayout.setRefreshing(false);
    }
}
