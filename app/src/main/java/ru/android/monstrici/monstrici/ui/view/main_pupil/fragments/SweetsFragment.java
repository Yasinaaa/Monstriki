package ru.android.monstrici.monstrici.ui.view.main_pupil.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import ru.android.monstrici.monstrici.data.model.Star;
import ru.android.monstrici.monstrici.domain.core.dagger.component.AppComponent;
import ru.android.monstrici.monstrici.domain.core.dagger.component.CoreComponent;
import ru.android.monstrici.monstrici.presentation.model.DayOfWeek;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.presentation.adapter.DaysOfWeekAdapter;
import ru.android.monstrici.monstrici.presentation.presenter.sweets.SweetsPresenter;
import ru.android.monstrici.monstrici.presentation.view.sweets.ISweetsView;
import ru.android.monstrici.monstrici.ui.view.base.BaseFragmentUsualToolbar;
import ru.android.monstrici.monstrici.utils.DateFunctions;
import ru.android.monstrici.monstrici.utils.Message;
import ru.android.monstrici.monstrici.utils.Resources;

/**
 * Created by yasina on 17.10.17.
 */

public class SweetsFragment extends BaseFragmentUsualToolbar
        implements ISweetsView, DaysOfWeekAdapter.OnItemClicked{

    public static int TOOLBAR_IMAGE = R.drawable.candy_icon_transparent;
    public static int TOOLBAR_TITLE = R.string.sweets;

    @BindView(R.id.rv_days_of_week)
    RecyclerView mRvDaysOfWeek;

    private DaysOfWeekAdapter mDaysOfWeekAdapter;
    private ArrayList<DayOfWeek> mDaysList;

    @InjectPresenter
    SweetsPresenter mPresenter;

    public SweetsFragment() {
        super(TOOLBAR_IMAGE, TOOLBAR_TITLE);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent(CoreComponent.class).inject(this);
    }

    @ProvidePresenter
    public SweetsPresenter providePresenter() {
        SweetsPresenter presenter = new SweetsPresenter();
        getComponent(AppComponent.class).inject(presenter);
        return presenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        createLayout(inflater, container, R.layout.fragment_sweets);
        return mView;
    }

    @Override
    public void setTag() {

    }

    @Override
    public void init() {
        mDaysList = new ArrayList<DayOfWeek>();
        mPresenter.getStars();
    }

    @Override
    public void onItemClick(int image) {

    }

    @Override
    public void setDonutsCount(ArrayList<Star> starsList) {
        mDaysList = mPresenter.getDonutsCount(starsList, getActivity());

        //mDaysOfWeekAdapter = new DaysOfWeekAdapter(mDaysList, this);
        mDaysOfWeekAdapter = new DaysOfWeekAdapter(mDaysList);
        mRvDaysOfWeek.setHasFixedSize(true);
        mRvDaysOfWeek.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvDaysOfWeek.setAdapter(mDaysOfWeekAdapter);

    }

    @Override
    public void showLoading(boolean flag) {

    }

    @Override
    public void showError(Message message) {

    }
}

