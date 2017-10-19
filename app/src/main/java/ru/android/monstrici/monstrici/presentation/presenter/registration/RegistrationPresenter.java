package ru.android.monstrici.monstrici.presentation.presenter.registration;

import android.app.Activity;

import ru.android.monstrici.monstrici.presentation.presenter.BasePresenter;
import ru.android.monstrici.monstrici.ui.view.parameters.ParametersActivity;
import ru.android.monstrici.monstrici.utils.ActivityUtils;

/**
 * Created by yasina on 16.10.17.
 */

public class RegistrationPresenter extends BasePresenter {

    public RegistrationPresenter(Activity activity) {
        super(activity);
    }

    public void goNext(){
        ActivityUtils.startActivityAndFinishCurrent(mActivity, ParametersActivity.class);
    }


}
