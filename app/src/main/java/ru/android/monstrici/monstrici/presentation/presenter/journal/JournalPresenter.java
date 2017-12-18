package ru.android.monstrici.monstrici.presentation.presenter.journal;

import com.arellomobile.mvp.InjectViewState;

import java.util.List;

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
                List<User> userList = response.getBodyList();

                for (int i=0; i<userList.size();i++){
                    User user = userList.get(i);
                    if(i==0){
                        getViewState().onFormPrepare(user.getSchoolClass().getNumber()
                                + user.getSchoolClass().getLetter());
                    }
                    getStars(user);
                }
            }
            @Override
            public void onReceiveDataFailure(Message message) {
                getViewState().showError(message);
                getViewState().showLoading(false);
            }
        });
    }

   private void getStars(User user) {
        mRepository.getStar(user.getStarId(), user.getId(), new IDataCallback<Star>() {
            @Override
            public void onReceiveDataSuccess(Response<Star> response) {
                user.setStars(response.getStarStorage());
                getViewState().onUsersPrepare(user);
            }
            @Override
            public void onReceiveDataFailure(Message message) {
                getViewState().showError(message);
            }
        });
    }

    public void saveStars(Star star, String userId) {
        mRepository.updateStar(star, userId);
    }

    public void removeStars(Star star, String userId) {
        mRepository.removeStar(star, userId);
    }
}
