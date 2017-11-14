package ru.android.monstrici.monstrici.ui.view.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.MvpAppCompatFragment;

import javax.inject.Inject;

import butterknife.ButterKnife;
import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.domain.core.dagger.component.AppComponent;
import ru.android.monstrici.monstrici.domain.core.dagger.component.ComponentUtils;
import ru.android.monstrici.monstrici.ui.view.application.ApplicationCore;
import ru.android.monstrici.monstrici.utils.Preconditions;
import ru.android.monstrici.monstrici.utils.Resources;

/**
 * Created by yasina on 23.10.17.
 */

public abstract class BaseFragment extends MvpAppCompatFragment {

    @Inject
    ApplicationCore mApplication;

    public String TAG = BaseFragment.class.getSimpleName();

    public View mView;

    public BaseFragment() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getComponent(AppComponent.class).inject(this);
    }

    public void createLayout(LayoutInflater inflater, ViewGroup container, int id) {
        mView = inflater.inflate(id, container, false);
        ButterKnife.bind(this, mView);
        init();
    }

    public void openFragment(BaseFragment fragment) {
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.fl_main, fragment, Resources.TAG_FRAGMENT)
                .addToBackStack(null)
                .commit();
    }
    protected <C> C getComponent(Class<C> componentType) {
        Preconditions.checkNotNull(getActivity());
        return ComponentUtils.getComponent(getActivity(), componentType);
    }


    public abstract void setTag();

    public abstract void init();


}
