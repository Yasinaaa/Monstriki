package ru.android.monstrici.monstrici.presentation.presenter.authorisation;

import com.arellomobile.mvp.InjectViewState;

import java.util.regex.Pattern;

import javax.inject.Inject;

import ru.android.monstrici.monstrici.data.model.Response;
import ru.android.monstrici.monstrici.data.model.User;
import ru.android.monstrici.monstrici.data.repository.UserRepositoryImpl;
import ru.android.monstrici.monstrici.domain.base.IDataCallback;
import ru.android.monstrici.monstrici.presentation.presenter.base.BasePresenter;
import ru.android.monstrici.monstrici.presentation.view.authorisation.IAuthorisationView;
import ru.android.monstrici.monstrici.utils.ErrorMessage;

/**
 * Created by elisiumGusev
 *
 * @Date 23/10/2017
 * @Author Andrei Gusev
 */
@InjectViewState
public class AuthorisationPresenter extends BasePresenter<IAuthorisationView> {
    @Inject
    UserRepositoryImpl mRepository;

    public void login(final String login, final String password) {
        if (isLoginValid(login, password)) {
            getViewState().showLoading(true);
            mRepository.checkLogin(login, password, new IDataCallback<User>() {
                @Override
                public void onReceiveDataSuccess(Response<User> response) {
                    getViewState().showLoading(false);
                    getViewState().onLoginSuccess(response.getBody().getId());
                }

                @Override
                public void onReceiveDataFailure(ErrorMessage message) {
                    getViewState().showLoading(false);
                    getViewState().onLoginFailed(message);
                }
            });
        } else {
            getViewState().onLoginFailed(new ErrorMessage());//TODO
        }
    }

    public boolean isLoginValid(final String login, final String password) {
        return true;
        //// TODO: 24/10/2017 remove
        //return isEmailValid(login) && isPasswordValid(password);
    }

    private boolean isEmailValid(String email) {
        return email.contains("@") && email.contains(".");
    }

    private boolean isPasswordValid(String password) {
        Pattern pattern = Pattern.compile("((?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20})");
        return password != null && pattern.matcher(password).matches();
    }
}
