package ru.android.monstrici.monstrici.presentation.presenter.base;

import android.app.Activity;
import android.content.Context;

import ru.android.monstrici.monstrici.presentation.BaseModule;

/**
 * Created by yasina on 16.10.17.
 */

public abstract class BasePresenter implements BaseModule.BasePresenter {

    public Activity mActivity;
    public Context mContext;

    public BasePresenter(Activity activity) {
        this.mActivity = activity;
        this.mContext = mActivity.getApplicationContext();
    }
}
