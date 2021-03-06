package ru.android.monstrici.monstrici.presentation.presenter.pupil;

import android.app.Activity;

import com.arellomobile.mvp.InjectViewState;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.data.model.Response;
import ru.android.monstrici.monstrici.data.model.Star;
import ru.android.monstrici.monstrici.data.model.User;
import ru.android.monstrici.monstrici.data.repository.UserRepositoryImpl;
import ru.android.monstrici.monstrici.domain.base.IDataCallback;
import ru.android.monstrici.monstrici.presentation.model.DayDesition;
import ru.android.monstrici.monstrici.presentation.model.DayOfWeek;
import ru.android.monstrici.monstrici.presentation.presenter.base.BasePresenter;
import ru.android.monstrici.monstrici.presentation.view.pupil.IPupilView;
import ru.android.monstrici.monstrici.presentation.view.sweets.ISweetsView;
import ru.android.monstrici.monstrici.utils.DateFunctions;
import ru.android.monstrici.monstrici.utils.Message;
import ru.android.monstrici.monstrici.utils.Resources;

/**
 * Created by yasina on 07.12.17.
 */

@InjectViewState
public class PupilPresenter extends BasePresenter<IPupilView> {

    @Inject
    UserRepositoryImpl mRepository;

    private ArrayList<String> mWeekDates;

    public void getUser(String userId){

        mRepository.getUser(userId, new IDataCallback<User>() {
            @Override
            public void onReceiveDataSuccess(Response<User> response) {
                getViewState().setUser(response.getBody());
            }

            @Override
            public void onReceiveDataFailure(Message message) {

            }
        });
    }

    public void getStars(User user){

        mRepository.getStar(user.getStarId(),user.getId(), new IDataCallback<Star>() {
            @Override
            public void onReceiveDataSuccess(Response<Star> response) {
                getViewState().setDonutsCount(new ArrayList(
                        response.getBodyMap().values()));
            }

            @Override
            public void onReceiveDataFailure(Message message) {

            }
        });
    }

    public DayDesition[] getDonutsCount(ArrayList<Star> starsList, Activity activity)
    {
        Calendar currentDate = Calendar.getInstance();
        return getDonutsCount(currentDate, starsList, activity);
    }

    public DayDesition[] getDonutsCount(Calendar calendar, ArrayList<Star> starsList, Activity activity)
    {
        //DayDesition[] mDayDesitions = Resources.mDesitionsOfWeek;

        DayDesition[] mDayDesitions = new DayDesition[]{
                new DayDesition(R.string.monday),
                new DayDesition(R.string.tuesday),
                new DayDesition(R.string.wednesday),
                new DayDesition(R.string.thursday),
                new DayDesition(R.string.friday),
                new DayDesition(R.string.saturday)
        };


        mWeekDates = DateFunctions.createWeekDates(calendar);
        for (int i=0; i<mDayDesitions.length; i++){
            Star star = findStarByDate(starsList, mWeekDates.get(i));
            mDayDesitions[i].setDate(mWeekDates.get(i));

            if (star != null){
                mDayDesitions[i].setGoals(Integer.parseInt(star.getGoals()));
                mDayDesitions[i].setTag(star.getTag().
                        substring(0, star.getTag().length() - 2));
            }else {
                mDayDesitions[i].setGoals(0);
            }

        }
        return mDayDesitions;
    }

    private Star findStarByDate(ArrayList<Star> starsList, String date){

        Star searchedStar = null;
        int count = 0;
        String tag = "";

        for (Star star: starsList){
            Date d2 = new Date(Long.parseLong(star.getDate()));
            if (date.equals(Resources.DATE_FORMAT.format(d2))){
                searchedStar = star;
                count += Integer.parseInt(star.getGoals());
                tag += star.getTag() + ",";
                searchedStar.setTag(tag);
                searchedStar.setGoals(String.valueOf(count));
            }

        }
        return searchedStar;
    }

    public String getWeek(){
        return mWeekDates.get(0) + "-" + mWeekDates.get(mWeekDates.size() - 1);
    }
}
