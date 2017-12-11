package ru.android.monstrici.monstrici.presentation.presenter.main;

import com.arellomobile.mvp.InjectViewState;

import java.util.List;

import javax.inject.Inject;

import ru.android.monstrici.monstrici.data.model.Monster;
import ru.android.monstrici.monstrici.data.model.Response;
import ru.android.monstrici.monstrici.data.model.Star;
import ru.android.monstrici.monstrici.data.model.User;
import ru.android.monstrici.monstrici.data.repository.UserRepositoryImpl;
import ru.android.monstrici.monstrici.domain.base.IDataCallback;
import ru.android.monstrici.monstrici.presentation.presenter.base.BasePresenter;
import ru.android.monstrici.monstrici.presentation.view.menu.IPupilMenu;
import ru.android.monstrici.monstrici.utils.Message;

/**
 * Created by elisium
 *
 * @Date 06/12/2017
 * @Author Andrei Gusev
 */
@InjectViewState
public class PupilMenuPresenter extends BasePresenter<IPupilMenu> {

    @Inject
    UserRepositoryImpl mRepository;

    public void getUser(String id) {
        getViewState().showLoading(true);
        mRepository.getUser(id, new IDataCallback<User>() {
            @Override
            public void onReceiveDataSuccess(Response<User> response) {
                getViewState().onUsersGet(response.getBody());
                getViewState().showLoading(false);
            }

            @Override
            public void onReceiveDataFailure(Message message) {
                getViewState().showError(message);
                getViewState().showLoading(false);
            }
        });
    }

    public void getStars(User user){
        mRepository.getStar(user.getStarId(), user.getId(), new IDataCallback<Star>() {
            @Override
            public void onReceiveDataSuccess(Response<Star> response) {
                user.setStars(response.getStarStorage());
                getViewState().setStars(user);
            }

            @Override
            public void onReceiveDataFailure(Message message) {

            }
        });
    }

}
