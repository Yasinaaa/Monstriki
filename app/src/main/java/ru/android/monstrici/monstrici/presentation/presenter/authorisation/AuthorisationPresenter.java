package ru.android.monstrici.monstrici.presentation.presenter.authorisation;

import com.arellomobile.mvp.InjectViewState;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.reactivestreams.Subscriber;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import ru.android.monstrici.monstrici.data.model.Response;
import ru.android.monstrici.monstrici.data.model.User;
import ru.android.monstrici.monstrici.data.repository.UserRepositoryImpl;
import ru.android.monstrici.monstrici.domain.base.IDataCallback;
import ru.android.monstrici.monstrici.presentation.presenter.base.BasePresenter;
import ru.android.monstrici.monstrici.presentation.view.authorisation.IAuthorisationView;
import ru.android.monstrici.monstrici.utils.Message;

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
                    getViewState().onLoginSuccess(response
                                    .getBody()
                                    .getPosition()
                                    .equals("teacher")
                            , response.getBody().getId());
                }

                @Override
                public void onReceiveDataFailure(Message message) {
                    getViewState().showLoading(false);
                    getViewState().onLoginFailed(message);
                }
            });
        } else {
            getViewState().onLoginFailed(new Message(""));//TODO
        }
    }

    public void checkUserToken() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();
        if (user != null) {
            mRepository.getUser(user.getUid(), new IDataCallback<User>() {
                @Override
                public void onReceiveDataSuccess(Response<User> response) {
                    getViewState().onLoginSuccess(response
                                    .getBody()
                                    .getPosition()
                                    .equals("teacher")
                            , response.getBody().getId());
                }

                @Override
                public void onReceiveDataFailure(Message message) {
                    getViewState().onLoginFailed(message);
                }
            });
        } else {
            getViewState().onLoginFailed(new Message(""));
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
