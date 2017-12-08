package ru.android.monstrici.monstrici.presentation.presenter.teacher;

import com.arellomobile.mvp.InjectViewState;

import javax.inject.Inject;

import ru.android.monstrici.monstrici.data.model.Response;
import ru.android.monstrici.monstrici.data.model.Star;
import ru.android.monstrici.monstrici.data.model.User;
import ru.android.monstrici.monstrici.data.repository.UserRepositoryImpl;
import ru.android.monstrici.monstrici.domain.base.IDataCallback;
import ru.android.monstrici.monstrici.presentation.presenter.base.BasePresenter;
import ru.android.monstrici.monstrici.presentation.view.journal.IJournalView;
import ru.android.monstrici.monstrici.utils.Message;

/**
 * Created by elisium
 *
 * @Date 02/12/2017
 * @Author Andrei Gusev
 */
@InjectViewState
public class JournalPresenter extends BasePresenter<IJournalView> {

    @Inject
    UserRepositoryImpl mRepository;

    public void getCurrentUser() {
        mRepository.getUser(mRepository.getCurrentUser().getId(), new IDataCallback<User>() {
            @Override
            public void onReceiveDataSuccess(Response<User> response) {
                getViewState().onTeacherPrepare(response.getBody());
            }

            @Override
            public void onReceiveDataFailure(Message message) {

            }
        });
    }

    public void getUsers() {

        getViewState().showLoading(true);
        mRepository.getUsers(new IDataCallback<User>() {
            @Override
            public void onReceiveDataSuccess(Response<User> response) {
                getUsersByClass();
            }

            @Override
            public void onReceiveDataFailure(Message message) {
                getViewState().showError(message);
                getViewState().showLoading(false);
            }
        });
    }

    private void getUsersByClass() {
        mRepository.getUsersByClass(new IDataCallback<User>() {
            @Override
            public void onReceiveDataSuccess(Response<User> response) {
                getViewState().onUsersPrepare(response.getBodyList());
                getViewState().showLoading(false);
            }

            @Override
            public void onReceiveDataFailure(Message message) {
                getViewState().showError(message);
                getViewState().showLoading(false);
            }
        });
    }

    public void getStars(User user) {
        mRepository.getStar(user.getStarId(), user.getId(), new IDataCallback<Star>() {
            @Override
            public void onReceiveDataSuccess(Response<Star> response) {
                getViewState().onStarsGet(user, response.getBodyList());
            }

            @Override
            public void onReceiveDataFailure(Message message) {

                getViewState().showError(message);
            }
        });
    }

    public void saveStars(Star star, String userId) {
        mRepository.addStar(star, userId);
    }
}
