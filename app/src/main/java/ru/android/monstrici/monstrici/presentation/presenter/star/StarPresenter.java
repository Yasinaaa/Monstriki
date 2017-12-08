package ru.android.monstrici.monstrici.presentation.presenter.star;

import com.arellomobile.mvp.InjectViewState;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import ru.android.monstrici.monstrici.data.model.Monster;
import ru.android.monstrici.monstrici.data.model.Response;
import ru.android.monstrici.monstrici.data.model.User;
import ru.android.monstrici.monstrici.data.repository.UserRepositoryImpl;
import ru.android.monstrici.monstrici.domain.base.IDataCallback;
import ru.android.monstrici.monstrici.presentation.presenter.base.BasePresenter;
import ru.android.monstrici.monstrici.presentation.view.star.IStarView;
import ru.android.monstrici.monstrici.utils.Message;

/**
 * Created by elisium
 *
 * @Date 05/12/2017
 * @Author Andrei Gusev
 */
@InjectViewState
public class StarPresenter extends BasePresenter<IStarView> implements Serializable {

    @Inject
    UserRepositoryImpl mRepository;
    private int reqId;

    public void getAllUserInformation(User user) {
        mRepository.getMonster(user.getMonster().getId(), user.getId(), new IDataCallback<Monster>() {
            @Override
            public void onReceiveDataSuccess(Response<Monster> response) {
                getViewState().getChoosedUser(user, response.getBody());
            }

            @Override
            public void onReceiveDataFailure(Message message) {

            }
        });
    }

    private void getUsers() {
        mRepository.getUsersByClass(new IDataCallback<User>() {
            @Override
            public void onReceiveDataSuccess(Response<User> response) {
                getViewState().getUsersRateList(sortUsersByStarsCount(response.getBodyList()));
            }

            @Override
            public void onReceiveDataFailure(Message message) {

            }
        });
    }

    public void getAllUsers() {
        mRepository.getUsers(new IDataCallback<User>() {
            @Override
            public void onReceiveDataSuccess(Response<User> response) {
                getUsers();
            }

            @Override
            public void onReceiveDataFailure(Message message) {

            }
        });
    }

    public void getAllMonsters(IDataCallback<Monster> callback) {
        mRepository.getMonsters(reqId + "", callback);
        reqId++;
    }

    private List<User> sortUsersByStarsCount(List<User> users) {

        Collections.sort(users, new Comparator<User>() {
            @Override
            public int compare(User one, User two) {
                int userOneStars, userTwoStars;
                userOneStars = one.getStarStorage().getStarsCount();
                userTwoStars = two.getStarStorage().getStarsCount();

                if (userOneStars > userTwoStars)
                    return 1;
                if (userOneStars < userTwoStars)
                    return -1;
                return 0;
            }
        });
        return users;
    }


}
