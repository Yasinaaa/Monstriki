package ru.android.monstrici.monstrici.presentation.presenter.teacher;

import com.arellomobile.mvp.InjectViewState;

import javax.inject.Inject;

import ru.android.monstrici.monstrici.data.model.Response;
import ru.android.monstrici.monstrici.data.model.User;
import ru.android.monstrici.monstrici.data.repository.UserRepositoryImpl;
import ru.android.monstrici.monstrici.domain.base.IDataCallback;
import ru.android.monstrici.monstrici.presentation.presenter.base.BasePresenter;
import ru.android.monstrici.monstrici.presentation.view.menu.ITeacherView;
import ru.android.monstrici.monstrici.utils.Message;

/**
 * Created by elisium
 *
 * @Date 02/12/2017
 * @Author Andrei Gusev
 */
@InjectViewState
public class TeacherPresenter extends BasePresenter<ITeacherView> {
    @Inject
    UserRepositoryImpl mRepository;
    public void getUsers() {
        getViewState().showLoading(true);
        mRepository.getUsers(new IDataCallback<User>() {
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
}
