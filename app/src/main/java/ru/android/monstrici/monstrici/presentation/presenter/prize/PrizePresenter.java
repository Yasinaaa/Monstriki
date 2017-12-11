package ru.android.monstrici.monstrici.presentation.presenter.prize;

import android.app.Activity;
import android.util.Log;

import com.arellomobile.mvp.InjectViewState;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.data.model.Monster;
import ru.android.monstrici.monstrici.data.model.Response;
import ru.android.monstrici.monstrici.data.model.Star;
import ru.android.monstrici.monstrici.data.model.StarStorage;
import ru.android.monstrici.monstrici.data.model.User;
import ru.android.monstrici.monstrici.data.repository.UserRepositoryImpl;
import ru.android.monstrici.monstrici.domain.base.IDataCallback;
import ru.android.monstrici.monstrici.presentation.presenter.base.BasePresenter;
import ru.android.monstrici.monstrici.presentation.view.prize.IPrizeView;
import ru.android.monstrici.monstrici.utils.Message;
import ru.android.monstrici.monstrici.utils.Resources;

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

    public void getUsers(Activity activity) {

        getViewState().showLoading(true);
        mRepository.getUsers(new IDataCallback<User>() {
            @Override
            public void onReceiveDataSuccess(Response<User> response) {
                getUsersByClass(activity);
            }

            @Override
            public void onReceiveDataFailure(Message message) {
                getViewState().showError(message);
                getViewState().showLoading(false);
            }
        });
    }

    private void getUsersByClass(Activity activity) {
        mRepository.getUsersByClass(new IDataCallback<User>() {
            @Override
            public void onReceiveDataSuccess(Response<User> response) {
                setPrizes(response.getBodyList(), activity);
                getViewState().showLoading(false);
            }

            @Override
            public void onReceiveDataFailure(Message message) {
                getViewState().showError(message);
                getViewState().showLoading(false);
            }
        });
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
                if (i == userList.size() - 1)
                    getStars(userList, i, true);
                else
                    getStars(userList, i, false);
            }
        }

    }

    private void countStars(List<User> userList){
        for (int i=0; i<userList.size();i++) {
            User user = userList.get(i);
            Map<String, Star> stars = user.getStarStorage().getStars();
            for (Map.Entry<String, Star> entry : stars.entrySet()) {
                Star star = entry.getValue();
                if (star.getGoals() != null) {
                    for (int k = 0; k < 10; k++) {
                        setMaxTagsGoal(k, star, user);
                    }
                }
            }
        }
        getViewState().onPrizesGetListFinish();
    }

    public void getAllUsersAchievements(){
        for (int i=0; i<maxTagsGoalsArray.length;i++){
            checkReward(i);
        }
    }

    public void getCurrentUserAchievements(){
        User currentUser = mRepository.getCurrentUser();
        for (int i=0; i<maxTagsUserArray.length;i++){
            if (currentUser.equals(maxTagsUserArray[i])){
                checkReward(i);
            }
        }
    }

    private void checkReward(int i){
        if (maxTagsUserArray[i] != null && maxTagsGoalsArray[i] != 0){
            getMonsterSetToUser(maxTagsUserArray[i], achievementsArray[i],
                    Resources.mRewardDrawables[i]);
        }
    }

    private void setMaxTagsGoal(int i, Star star, User user){
        int goals = 0;
        try {
            goals = Integer.parseInt(star.getGoals());
            if(star.getTag().equals(tagsArray[i]) &&
                    maxTagsGoalsArray[i] < goals){
                maxTagsGoalsArray[i] = goals;
                maxTagsUserArray[i] = user;
            }
        }catch (NumberFormatException e){

        }
    }

    private void getMonsterSetToUser(User user, String achievement, int pic){
        mRepository.getMonster(user.getMonster().getId(), user.getId(),
                new IDataCallback<Monster>(){
                    @Override
                    public void onReceiveDataSuccess(Response<Monster> response) {
                        getViewState().setReward(achievement,
                                pic, response.getBody().getName());

                    }

                    @Override
                    public void onReceiveDataFailure(Message message) {

                    }
                });
    }

    private void getStars(List<User> userList, int i, boolean finish){
        mRepository.getStar(userList.get(i).getStarId(),userList.get(i).getId(), new IDataCallback<Star>() {
            @Override
            public void onReceiveDataSuccess(Response<Star> response) {
                userList.get(i).setStars(response.getStarStorage());

                if (finish){
                    countStars(userList);
                }
            }

            @Override
            public void onReceiveDataFailure(Message message) {

            }
        });
    }
}
