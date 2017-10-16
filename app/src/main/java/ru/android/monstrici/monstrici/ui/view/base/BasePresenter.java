package ru.android.monstrici.monstrici.ui.view.base;

import android.app.Activity;
import android.content.Context;

/**
 * Created by yasina on 16.10.17.
 */

public class BasePresenter implements BaseModule.BasePresenter{

    public Activity mActivity;
    public Context mContext;

    public BasePresenter(Activity activity) {
        this.mActivity = activity;
        this.mContext = mActivity.getApplicationContext();
    }
}
