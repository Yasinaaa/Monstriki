package ru.android.monstrici.monstrici.presentation.presenter.base;

import android.app.Activity;
import android.content.Context;

import com.arellomobile.mvp.MvpPresenter;
import com.arellomobile.mvp.MvpView;

import ru.android.monstrici.monstrici.presentation.BaseModule;

/**
 * Created by yasina on 16.10.17.
 */

public abstract class BasePresenter<View extends MvpView> extends MvpPresenter<View>
        implements BaseModule.BasePresenter {

    public Activity mActivity;
    public Context mContext;

    @Deprecated
    public BasePresenter(Activity activity) {
        this.mActivity = activity;
        this.mContext = mActivity.getApplicationContext();
    }

    public BasePresenter() {

    }
}
