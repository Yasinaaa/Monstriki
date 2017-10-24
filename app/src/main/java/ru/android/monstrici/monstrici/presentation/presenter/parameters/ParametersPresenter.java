package ru.android.monstrici.monstrici.presentation.presenter.parameters;

import android.app.Activity;

import ru.android.monstrici.monstrici.presentation.presenter.base.BasePresenter;
import ru.android.monstrici.monstrici.ui.view.main.MainMenu;
import ru.android.monstrici.monstrici.utils.ActivityUtils;

/**
 * Created by yasina on 16.10.17.
 */

public class ParametersPresenter extends BasePresenter {

    public ParametersPresenter(Activity activity) {
        super(activity);
    }


    public void goNext(){
        ActivityUtils.startActivityAndFinishCurrent(mActivity, MainMenu.class);
    }

}
