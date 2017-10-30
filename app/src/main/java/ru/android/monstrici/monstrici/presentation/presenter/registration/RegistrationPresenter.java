package ru.android.monstrici.monstrici.presentation.presenter.registration;

import android.app.Activity;
import android.content.Intent;

import ru.android.monstrici.monstrici.presentation.presenter.base.BasePresenter;
import ru.android.monstrici.monstrici.ui.view.main_teacher.MainTeacherActivity;
import ru.android.monstrici.monstrici.ui.view.parameters.ParametersActivity;

/**
 * Created by yasina on 16.10.17.
 */

public class RegistrationPresenter extends BasePresenter {

    public RegistrationPresenter(Activity activity) {
        super(activity);
    }


    public void goNext(){
        Intent intent = new Intent(mActivity, MainTeacherActivity.class);
        mActivity.startActivity(intent);
        mActivity.finish();
    }


}
