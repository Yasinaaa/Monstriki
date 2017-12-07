package ru.android.monstrici.monstrici.presentation.presenter.sweets;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.data.model.Response;
import ru.android.monstrici.monstrici.data.model.Star;
import ru.android.monstrici.monstrici.data.model.User;
import ru.android.monstrici.monstrici.data.repository.UserRepositoryImpl;
import ru.android.monstrici.monstrici.domain.base.IDataCallback;
import ru.android.monstrici.monstrici.presentation.model.DayOfWeek;
import ru.android.monstrici.monstrici.presentation.presenter.base.BasePresenter;
import ru.android.monstrici.monstrici.presentation.view.sweets.ISweetsView;
import ru.android.monstrici.monstrici.utils.DateFunctions;
import ru.android.monstrici.monstrici.utils.Message;
import ru.android.monstrici.monstrici.utils.Resources;

/**
 * Created by yasina on 07.12.17.
 */
@InjectViewState
public class SweetsPresenter extends BasePresenter<ISweetsView> {

    @Inject
    UserRepositoryImpl mRepository;

    private String[] mDayTitles;

    public void getStars() {
        Map<String, Star> starMap = mRepository.getCashedUser().
                getStarStorage().getStars();

        if(starMap != null)
            getViewState().setDonutsCount(new ArrayList(starMap.values()));
        else
            getViewState().showError(new Message("Empty stars list"));
    }

    public ArrayList<DayOfWeek> getDonutsCount(ArrayList<Star> starsList, Activity activity)
    {
        mDayTitles = activity.getResources().getStringArray(R.array.days_of_week);
        ArrayList<DayOfWeek> mDaysList = new ArrayList<DayOfWeek>();
        Calendar currentDate = Calendar.getInstance();
        SimpleDateFormat format = new SimpleDateFormat("dd MMMM \nyyyy");
        ArrayList<Date> weekDates = DateFunctions.createWeekDates(currentDate);

        for(int i = 0; i< Resources.mStudyDaysOfWeek; i++){

            Star star = findStarByDate(starsList,
                    String.valueOf(weekDates.get(i).getTime()));
            DayOfWeek dayOfWeek;
            if (star != null){
                dayOfWeek = new DayOfWeek(mDayTitles[i],
                        format.format(weekDates.get(i).getTime()),
                        Integer.parseInt(star.getGoals()));
            }else {
                dayOfWeek = new DayOfWeek(mDayTitles[i],
                        format.format(weekDates.get(i).getTime()),
                        0);
            }
            mDaysList.add(dayOfWeek);
            currentDate.add(Calendar.DAY_OF_WEEK, 1);
        }
        return mDaysList;
    }

    private Star findStarByDate(ArrayList<Star> starsList, String date){
        for (Star star: starsList){
            if (star.getFormatedDate().equals(date))
                return star;
        }
        return null;
    }
}
