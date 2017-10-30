package ru.android.monstrici.monstrici.ui.view.authorisation;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.presentation.presenter.authorisation.AuthorisationPresenter;
import ru.android.monstrici.monstrici.presentation.view.authorisation.IAuthorisationView;
import ru.android.monstrici.monstrici.ui.view.base.BaseActivity;
import ru.android.monstrici.monstrici.ui.view.main_pupil.MainMenu;
import ru.android.monstrici.monstrici.utils.Message;

/**
 * Created by yasina on 14.10.17.
 */

public class AuthorisationActivity extends BaseActivity implements IAuthorisationView, View.OnClickListener {

    @BindView(R.id.input_email)
    protected EditText mEmailEditText;
    @BindView(R.id.input_password)
    protected EditText mPasswordEditText;
    @BindView(R.id.btn_login)
    protected Button mLoginButton;
    @BindView(R.id.login_progress)
    ProgressBar mProgressBar;
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
        getApplicationComponent().inject(this);
        ButterKnife.bind(this);
        mLoginButton.setOnClickListener(this);
    }

    @Override
    public void init() {

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
                    mLoginButton.setEnabled(false);
                    mPresenter.login(mEmailEditText.getText().toString(),
                            mPasswordEditText.getText().toString());
                }
                break;
            }
        }
    }

    @Override
    public void onLoginSuccess(Long id) {
        Intent menu = MainMenu.newIntent(this, id);
        startActivity(menu);
        finish();
    }

    private boolean validateForm() {
        boolean valid = true;

        String email = mEmailEditText.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmailEditText.setError(getString(R.string.required));
            valid = false;
        } else {
            mEmailEditText.setError(null);
        }

        String password = mPasswordEditText.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPasswordEditText.setError(getString(R.string.required));
            valid = false;
        } else {
            mPasswordEditText.setError(null);
        }

        return valid;
    }

    @Override
    public void onLoginFailed(Message message) {
        showError(message);
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


