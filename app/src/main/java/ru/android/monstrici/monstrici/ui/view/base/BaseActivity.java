package ru.android.monstrici.monstrici.ui.view.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.arellomobile.mvp.MvpAppCompatActivity;

import butterknife.ButterKnife;

import ru.android.monstrici.monstrici.domain.core.dagger.component.AppComponent;
import ru.android.monstrici.monstrici.domain.core.dagger.component.ComponentUtils;
import ru.android.monstrici.monstrici.domain.core.dagger.component.IHasComponent;
import ru.android.monstrici.monstrici.presentation.BaseModule;
import ru.android.monstrici.monstrici.ui.view.application.ApplicationCore;

/**
 * Created by elisiumGusev
 *
 * @Date 14/10/2017
 * @Author Andrei Gusev
 */

public abstract class BaseActivity extends MvpAppCompatActivity implements BaseModule.BaseView {

    public String TAG = BaseActivity.class.getSimpleName();

    @Override
    public void start() {
        ButterKnife.bind(this);
        init();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        getApplicationComponent().inject(this);
    }

    protected <C> C getComponent(Class<C> componentType) {
        return ComponentUtils.getComponent(this, componentType);
    }

    protected AppComponent getApplicationComponent() {
        return (AppComponent) ((IHasComponent<ApplicationCore>) getApplication()).getComponent();
    }

    public void init(){};

    public void setTag(){};

}
