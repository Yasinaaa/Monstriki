package ru.android.monstrici.monstrici.ui.view.main_teacher.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.timessquare.CalendarPickerView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;
import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.ui.view.base.BaseFragment;
import ru.android.monstrici.monstrici.utils.DateFunctions;
import ru.android.monstrici.monstrici.utils.Resources;

/**
 * Created by yasina on 29.10.17.
 */

public class DataFragment extends BaseFragment {

    protected static String FORM_TYPE = "date_type";
    protected static String VIEW_TYPE = "view_type";
    @BindView(R.id.cv_calendar)
    com.squareup.timessquare.CalendarPickerView mCvCalendar;

    private Calendar mSelectedCalendar;
    private boolean mIsPupilView = false;
    private String mForm = "";

    public DataFragment() {
    }

    public static DataFragment newInstance(boolean isPupilView, String form){
        Bundle args = new Bundle();
        args.putBoolean(VIEW_TYPE, isPupilView);
        args.putString(FORM_TYPE, form);
        DataFragment newFragment = new DataFragment();
        newFragment.setArguments(args);
        return newFragment;
    }

    public static DataFragment newInstance(boolean isPupilView){
        Bundle args = new Bundle();
        args.putBoolean(VIEW_TYPE, isPupilView);
        DataFragment newFragment = new DataFragment();
        newFragment.setArguments(args);
        return newFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            mIsPupilView = getArguments().getBoolean(VIEW_TYPE);
            if (getArguments().getString(FORM_TYPE) != null)
                mForm = getArguments().getString(FORM_TYPE);
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
        mSelectedCalendar.set(Calendar.DAY_OF_MONTH,1);
        mSelectedCalendar.set(Calendar.MONTH, Calendar.JANUARY);

        Calendar finishDate = Calendar.getInstance();
        finishDate.set(Calendar.DAY_OF_MONTH, 31);
        finishDate.set(Calendar.MONTH, Calendar.DECEMBER);

        CalendarPickerView.FluentInitializer fluentInitializer =
                mCvCalendar.init(mSelectedCalendar.getTime(), finishDate.getTime());


        initWeekView(fluentInitializer);
    }

    private void initWeekView(CalendarPickerView.FluentInitializer fluentInitializer){
    fluentInitializer
                .inMode(CalendarPickerView.SelectionMode.MULTIPLE)
                .withHighlightedDates(DateFunctions.createWeekDates2(
                        Calendar.getInstance()));
        mCvCalendar.scrollToDate(Calendar.getInstance().getTime());
        mCvCalendar.setOnDateSelectedListener(new CalendarPickerView.OnDateSelectedListener() {
            @Override
            public void onDateSelected(Date date) {

                mCvCalendar.clearHighlightedDates();
                ArrayList<Date> mDates = DateFunctions.createWeekDates(date);
                mCvCalendar.highlightDates(mDates);
                mCvCalendar.selectDate(date);

                /*try {
                    Thread.sleep(500);
                }
                catch (InterruptedException ex) {
                    Log.d(TAG, ex.toString());
                }*/

                if (mIsPupilView){
                    Intent intent = new Intent();
                    intent.putExtra(PupilFragment.DATE_BEGIN, mDates.get(0).getTime());
                    intent.putExtra(PupilFragment.DATE_FINISH, mDates.get(6).getTime());

                    getTargetFragment().onActivityResult(
                            getTargetRequestCode(),
                            Activity.RESULT_OK,
                            intent
                    );
                    removeFragment(DataFragment.this);

                }else {
                    openFragment(
                            JournalFragment.newInstance(mForm,
                                    Resources.DATE_FORMAT.format(mDates.get(0)) + "-" +
                                            Resources.DATE_FORMAT.format(mDates.get(6))));
                }
            }

            @Override
            public void onDateUnselected(Date date) {

            }
        });

    }

    @Override
    public void setTag() {
        TAG = "DataFragment";
    }

}
