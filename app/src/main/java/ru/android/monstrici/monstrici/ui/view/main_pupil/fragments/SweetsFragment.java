package ru.android.monstrici.monstrici.ui.view.main_pupil.fragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ru.android.monstrici.monstrici.presentation.model.DayOfWeek;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.presentation.adapter.DaysOfWeekAdapter;
import ru.android.monstrici.monstrici.ui.view.base.BaseFragmentUsualToolbar;
import ru.android.monstrici.monstrici.utils.Resources;

/**
 * Created by yasina on 17.10.17.
 */

public class SweetsFragment extends BaseFragmentUsualToolbar implements DaysOfWeekAdapter.OnItemClicked{

    public static int TOOLBAR_IMAGE = R.drawable.candy_icon_transparent;
    public static int TOOLBAR_TITLE = R.string.sweets;

    @BindView(R.id.rv_days_of_week)
    RecyclerView mRvDaysOfWeek;

    private DaysOfWeekAdapter mDaysOfWeekAdapter;
    private ArrayList<DayOfWeek> mDaysList;
    private String[] mDayTitles;

    public SweetsFragment() {
        super(TOOLBAR_IMAGE, TOOLBAR_TITLE);
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
        createLayout(inflater, container, R.layout.fragment_sweets);
        return mView;
    }

    @Override
    public void setTag() {

    }

    @Override
    public void init() {
        mDaysList = new ArrayList<DayOfWeek>();
        mDayTitles = getResources().getStringArray(R.array.days_of_week);

        //TODO: temporary method, remove when will be a real values
        setTempValue();

        mDaysOfWeekAdapter = new DaysOfWeekAdapter(mDaysList, this);
        mRvDaysOfWeek.setHasFixedSize(true);
        mRvDaysOfWeek.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvDaysOfWeek.setAdapter(mDaysOfWeekAdapter);
    }


    public void setTempValue(){
        //todo: temp values, remove this
        int[] tempDonutValues = new int[Resources.mStudyDaysOfWeek];
        tempDonutValues[0] = 5;
        tempDonutValues[1] = 2;
        Calendar currentDate = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd MMMM \nyyyy");

        for(int i=0; i<Resources.mStudyDaysOfWeek; i++){
            DayOfWeek dayOfWeek = new DayOfWeek(mDayTitles[i], format.format(currentDate.getTime()),
                    tempDonutValues[i]);
            mDaysList.add(dayOfWeek);
            currentDate.add(Calendar.DAY_OF_WEEK, 1);
        }
    }

    @Override
    public void onItemClick(int image) {

    }
}

