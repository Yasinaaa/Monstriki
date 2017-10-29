package ru.android.monstrici.monstrici.ui.view.main_teacher.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.ImageView;

import java.util.Calendar;

import butterknife.BindView;
import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.ui.view.base.BaseFragment;

/**
 * Created by yasina on 29.10.17.
 */

public class DataFragment extends BaseFragment {

    @BindView(R.id.cv_calendar)
    CalendarView mCvCalendar;

    private Calendar mSelectedCalendar;

    public DataFragment() {
    }

    public static DataFragment newInstance(){
        Bundle args = new Bundle();
        DataFragment newFragment = new DataFragment();
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
        createLayout(inflater, container, R.layout.fragment_data);
        return mView;
    }

    @Override
    public void init() {

        mSelectedCalendar = Calendar.getInstance();
        mCvCalendar.setDate(mSelectedCalendar.getTimeInMillis(),false,true);
        mCvCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                mSelectedCalendar.set(Calendar.YEAR, year);
                mSelectedCalendar.set(Calendar.MONTH, month);
                mSelectedCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            }
        });

    }

    @Override
    public void setTag() {
        TAG = "DataFragment";
    }

}
