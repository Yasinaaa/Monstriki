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
                    if (i == userList.size() - 1)
                        getStars(userList, i, true);
                    else
                        getStars(userList, i, false);
                }
            }

            @Override
            public void onReceiveDataFailure(Message message) {
                getViewState().showError(message);
                getViewState().showLoading(false);
            }
        });
    }

   private void getStars(List<User> userList, int i, boolean finish) {
        mRepository.getStar(userList.get(i).getStarId(),userList.get(i).getId(), new IDataCallback<Star>() {
            @Override
            public void onReceiveDataSuccess(Response<Star> response) {
                userList.get(i).setStars(response.getStarStorage());

                if (finish){
                    getViewState().onUsersPrepare(userList);
                }
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
