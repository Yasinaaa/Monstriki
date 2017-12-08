package ru.android.monstrici.monstrici.presentation.presenter.prize;

import android.app.Activity;

import com.arellomobile.mvp.InjectViewState;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.data.model.Response;
import ru.android.monstrici.monstrici.data.model.Star;
import ru.android.monstrici.monstrici.data.model.User;
import ru.android.monstrici.monstrici.data.repository.UserRepositoryImpl;
import ru.android.monstrici.monstrici.domain.base.IDataCallback;
import ru.android.monstrici.monstrici.presentation.presenter.base.BasePresenter;
import ru.android.monstrici.monstrici.presentation.view.prize.IPrizeView;
import ru.android.monstrici.monstrici.utils.Message;

/**
 * Created by yasina on 07.12.17.
 */
@InjectViewState
public class PrizePresenter extends BasePresenter<IPrizeView> {

    @Inject
    UserRepositoryImpl mRepository;

    private String[] achievementsArray, tagsArray;
    private int[] maxTagsGoalsArray;
    private User[] maxTagsUserArray;

    public void getUser(String id) {
    }

    public void setPrizes(List<User> userList, Activity activity){

        achievementsArray = activity.getResources().
                getStringArray(R.array.achievements_array);
        tagsArray = activity.getResources().
                getStringArray(R.array.tags_array);

        if (achievementsArray.length == tagsArray.length){
            maxTagsGoalsArray = new int[tagsArray.length];
            maxTagsUserArray = new User[tagsArray.length];

            for (int i=0; i<userList.size();i++){
                User user = userList.get(i);
                Map<String, Star> stars = user.getStarStorage().getStars();
                for (Map.Entry<String, Star> entry : stars.entrySet())
                {
                    Star star = entry.getValue();
                    if (star.getGoals() != null){
                        for (int k = 0; k<10; k++){
                            setMaxTagsGoal(k, star, user);
                        }
                    }
                }

            }
        }

    }

    private void getCurrentUserAchievements(User currentUser){
        for (int i=0; i<maxTagsUserArray.length;i++){
            if (currentUser.equals(maxTagsUserArray[i])){

            }
        }
    }

    private void setMaxTagsGoal(int i, Star star, User user){
        int goals = Integer.parseInt(star.getGoals());
        if(star.getTag().equals(tagsArray[0]) &&
                maxTagsGoalsArray[0] < goals){
            maxTagsGoalsArray[0] = goals;
            maxTagsUserArray[0] = user;
        }
    }
}
