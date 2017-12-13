package ru.android.monstrici.monstrici.ui.view.authorisation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import ru.android.monstrici.monstrici.domain.core.dagger.component.AppComponent;
import ru.android.monstrici.monstrici.presentation.presenter.authorisation.AuthorisationPresenter;
import ru.android.monstrici.monstrici.presentation.view.authorisation.IAuthorisationView;
import ru.android.monstrici.monstrici.ui.view.base.BaseActivity;
import ru.android.monstrici.monstrici.ui.view.main_pupil.MainPupilActivity;
import ru.android.monstrici.monstrici.ui.view.main_teacher.MainTeacherActivity;
import ru.android.monstrici.monstrici.ui.view.parameters.CreateMonsterActivity;
import ru.android.monstrici.monstrici.utils.Message;

/**
 * Created by elisium
 *
 * @Date 14/11/2017
 * @Author Andrei Gusev
 */

public class SplashActivity extends BaseActivity implements IAuthorisationView {
    @InjectPresenter
    public AuthorisationPresenter mPresenter;

    @ProvidePresenter
    public AuthorisationPresenter providePresenter() {
        AuthorisationPresenter presenter = new AuthorisationPresenter();
        getComponent(AppComponent.class).inject(presenter);
        return presenter;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent(AppComponent.class).inject(this);
        mPresenter.checkUserToken();
    }

    @Override
    public void init() {

    }

    @Override
    public void setTag() {

    }

    @Override
    public void showLoading(boolean flag) {

    }

    @Override
    public void showError(Message message) {

    }

    @Override
    public void onLoginSuccess(boolean isTeacher, String id) {
        Intent menu;
        if (isTeacher)
            menu = MainTeacherActivity.newIntent(this, id);
        else
            menu = MainPupilActivity.newIntent(this, id);
        startActivity(menu);
        finish();
    }

    @Override
    public void onLoginSuccessCreateMonster(String id) {
        Intent intent = CreateMonsterActivity.newIntent(this);
        startActivity(intent);
        finish();
    }

    @Override
    public void onLoginFailed(Message message) {
        startActivity(new Intent(this, AuthorisationActivity.class));
        finish();
    }
}
