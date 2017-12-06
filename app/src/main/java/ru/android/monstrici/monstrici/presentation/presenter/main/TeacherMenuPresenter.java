package ru.android.monstrici.monstrici.presentation.presenter.main;

import com.arellomobile.mvp.InjectViewState;

import javax.inject.Inject;

import ru.android.monstrici.monstrici.data.model.Monster;
import ru.android.monstrici.monstrici.data.model.Response;
import ru.android.monstrici.monstrici.data.model.User;
import ru.android.monstrici.monstrici.data.repository.UserRepositoryImpl;
import ru.android.monstrici.monstrici.domain.base.IDataCallback;
import ru.android.monstrici.monstrici.presentation.presenter.base.BasePresenter;
import ru.android.monstrici.monstrici.presentation.view.menu.ITeacherMenu;
import ru.android.monstrici.monstrici.utils.Message;

/**
 * Created by elisium
 *
 * @Date 09/11/2017
 * @Author Andrei Gusev
 */
@InjectViewState
public class TeacherMenuPresenter extends BasePresenter<ITeacherMenu>{

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

}
