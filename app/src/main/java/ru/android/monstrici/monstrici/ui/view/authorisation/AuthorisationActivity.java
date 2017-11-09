package ru.android.monstrici.monstrici.ui.view.authorisation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import butterknife.BindView;
import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.presentation.presenter.authorisation.AuthorisationPresenter;
import ru.android.monstrici.monstrici.presentation.view.authorisation.IAuthorisationView;
import ru.android.monstrici.monstrici.ui.view.base.BaseActivity;
import ru.android.monstrici.monstrici.ui.view.main_pupil.MainMenu;
import ru.android.monstrici.monstrici.ui.view.main_teacher.MainTeacherActivity;
import ru.android.monstrici.monstrici.utils.Message;

/**
 * Created by yasina on 14.10.17.
 */

public class AuthorisationActivity extends BaseActivity
        implements IAuthorisationView, View.OnClickListener {

    @BindView(R.id.et_login)
    protected EditText mEtEmail;
    @BindView(R.id.et_password)
    protected EditText mEtPassword;
    @BindView(R.id.btn_login)
    protected Button mBtnLogin;
    @BindView(R.id.pb_login)
    protected ProgressBar mProgressBar;
    @BindView(R.id.rl)
    RelativeLayout mRl;
    // @Inject
    @InjectPresenter
    public AuthorisationPresenter mPresenter;

    @ProvidePresenter
    public AuthorisationPresenter providePresenter() {
        AuthorisationPresenter presenter = new AuthorisationPresenter();
        getApplicationComponent().inject(presenter);
        return presenter;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authorisation);
        // getApplicationComponent().inject(this);
        start();
    }

    @Override
    public void init() {
        getApplicationComponent().inject(this);
        mBtnLogin.setOnClickListener(this);
    }

    @Override
    public void setTag() {
        TAG = AuthorisationActivity.class.getCanonicalName();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login: {
                if (validateForm()) {
                    mBtnLogin.setEnabled(false);
                    mPresenter.login(mEtEmail.getText().toString(),
                            mEtPassword.getText().toString());
                }
                break;
            }
        }
    }

    @Override
    public void onLoginSuccess(boolean isTeacher, String id) {
        Intent menu;
        if (isTeacher)
            menu = MainTeacherActivity.newIntent(this, id);
        else
            menu = MainMenu.newIntent(this, id);
        startActivity(menu);
        finish();
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = mEtEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEtEmail.setError(getString(R.string.required));
            valid = false;
        } else {
            mEtEmail.setError(null);
        }

        String password = mEtPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mEtPassword.setError(getString(R.string.required));
            valid = false;
        } else {
            mEtPassword.setError(null);
        }

        return valid;
    }

    @Override
    public void onLoginFailed(Message message) {
        showError(message);
        mBtnLogin.setEnabled(true);
    }

    @Override
    public void showLoading(boolean flag) {
        if (flag)
            mProgressBar.setVisibility(android.view.View.VISIBLE);
        else mProgressBar.setVisibility(android.view.View.GONE);
    }

    @Override
    public void showError(Message message) {
        Toast.makeText(getBaseContext(), message.getMessage(), Toast.LENGTH_LONG).show();
    }

}


