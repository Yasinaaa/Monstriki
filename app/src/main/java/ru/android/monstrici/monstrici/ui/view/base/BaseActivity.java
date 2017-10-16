package ru.android.monstrici.monstrici.ui.view.base;

import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import butterknife.ButterKnife;

/**
 * Created by elisiumGusev
 *
 * @Date 14/10/2017
 * @Author Andrei Gusev
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseModule.BaseView{

    public String TAG = BaseActivity.class.getSimpleName();

    @Override
    public void start(){
        ButterKnife.bind(this);
        init();
    }

    public abstract void setTag();

}
