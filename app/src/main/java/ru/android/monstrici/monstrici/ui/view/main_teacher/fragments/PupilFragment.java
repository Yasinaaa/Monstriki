package ru.android.monstrici.monstrici.ui.view.main_teacher.fragments;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.presentation.adapter.WeekDesitionsAdapter;
import ru.android.monstrici.monstrici.presentation.model.DayDesition;
import ru.android.monstrici.monstrici.ui.view.base.BaseFragment;
import ru.android.monstrici.monstrici.utils.Resources;

/**
 * Created by yasina on 29.10.17.
 */

public class PupilFragment extends BaseFragment {

    public static final String PUPIL = "pupil";
    @BindView(R.id.tv_pupil)
    TextView mTvPupil;
    @BindView(R.id.tv_data_bracket)
    TextView mTvDataBracket;
    @BindView(R.id.tv_data_day)
    TextView mTvDataDay;
    @BindView(R.id.rv_desitions_of_week)
    RecyclerView mRvDesitionsOfWeek;

    private String mPupil;
    private Calendar mCurrentWeekCalendar;
    private DayDesition[] mDayDesitions = Resources.mDesitionsOfWeek;
    private WeekDesitionsAdapter mWeekDesitionsAdapter;

    public PupilFragment() {
    }

    public static PupilFragment newInstance(String value){
        Bundle args = new Bundle();
        args.putString(PUPIL, value);
        PupilFragment newFragment = new PupilFragment();
        newFragment.setArguments(args);
        return newFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            mPupil = getArguments().getString(PUPIL);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        createLayout(inflater, container, R.layout.fragment_pupil);
        return mView;
    }

    @Override
    public void init() {
        mCurrentWeekCalendar = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("d MMMM \nyyyy");

        //todo: temp values, remove this
        for (int i=0; i<mDayDesitions.length; i++){
            mDayDesitions[i].setDate(format.format(mCurrentWeekCalendar.getTime()));
            mDayDesitions[i].setForAnswer(i);
            mDayDesitions[i].setForCleaning(i-1);
        }
        mWeekDesitionsAdapter = new WeekDesitionsAdapter(mDayDesitions);
    }

    @Override
    public void setTag() {
        TAG = "PupilFragment";
    }

}
