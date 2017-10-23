package ru.android.monstrici.monstrici.ui.view.registation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;
import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.presentation.presenter.registration.RegistrationPresenter;
import ru.android.monstrici.monstrici.ui.view.base.BaseActivity;


public class RegistrationActivity extends BaseActivity {

    @BindView(R.id.btn_registration)
    Button mBtnRegistration;
    @BindView(R.id.btn_login)
    Button mBtnLogin;
    @BindView(R.id.et_name)
    EditText mEtName;
    @BindView(R.id.et_surname)
    EditText mEtSurname;
    @BindView(R.id.et_form)
    EditText mEtForm;
    @BindView(R.id.et_letter)
    EditText mEtLetter;

    private RegistrationPresenter mRegistrationPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        start();
    }

    @Override
    public void init() {
        mRegistrationPresenter = new RegistrationPresenter(this);
    }

    @OnClick(R.id.btn_registration)
    public void setOnRegistrationBtnClickListener(){
        mRegistrationPresenter.goNext();
    }

    @Override
    public void setTag() {
        TAG = RegistrationActivity.class.getCanonicalName();
    }


}
