package ru.android.monstrici.monstrici.presentation.presenter.pupil;

import com.arellomobile.mvp.InjectViewState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

import ru.android.monstrici.monstrici.data.model.Monster;
import ru.android.monstrici.monstrici.data.model.Response;
import ru.android.monstrici.monstrici.data.model.Star;
import ru.android.monstrici.monstrici.data.model.User;
import ru.android.monstrici.monstrici.data.repository.IUserRepository;
import ru.android.monstrici.monstrici.data.repository.UserRepositoryImpl;
import ru.android.monstrici.monstrici.domain.base.IDataCallback;
import ru.android.monstrici.monstrici.presentation.presenter.base.BasePresenter;
import ru.android.monstrici.monstrici.presentation.view.pupil.IStarDesc;
import ru.android.monstrici.monstrici.utils.Message;

/**
 * Created by elisium
 *
 * @Date 05/12/2017
 * @Author Andrei Gusev
 */
@InjectViewState
public class StarPresenter extends BasePresenter<IStarDesc> {

    @Inject
    UserRepositoryImpl mRepository;

    private void getUsers() {
        mRepository.getUsersByClass(new IDataCallback<User>() {
            @Override
            public void onReceiveDataSuccess(Response<User> response) {
                getViewState().showMonsters(sortUsersByStarsCount(response.getBodyList()));
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

    private List<User> sortUsersByStarsCount(List<User> users){

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
