package ru.android.monstrici.monstrici.ui.view.authorisation;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;

import javax.inject.Inject;

import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.presentation.presenter.authorisation.SplashPresenter;
import ru.android.monstrici.monstrici.presentation.view.authorisation.ISplashView;
import ru.android.monstrici.monstrici.ui.view.base.BaseFragment;
import ru.android.monstrici.monstrici.ui.view.main.MainMenu;
import ru.android.monstrici.monstrici.utils.ErrorMessage;

/**
 * Created by elisiumGusev
 *
 * @Date 20/10/2017
 * @Author Andrei Gusev
 */

public class SplashFragment extends BaseFragment implements ISplashView {

    //@Inject
    @InjectPresenter
    SplashPresenter mPresenter;
    private String token;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public android.view.View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        android.view.View v = inflater.inflate(R.layout.fragment_splash, container, false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setReturnTransition(new Fade());
        }
        return v;
    }

    @Override
    public void showLoading(boolean flag) {

    }

    @Override
    public void showError(ErrorMessage message) {
        Toast.makeText(getActivity(), message.getMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loginSuccess(long id) {
        Intent menu = MainMenu.newIntent(getContext(), id);
        startActivity(menu);
        getActivity().finish();
    }

    @Override
    public void loginFail() {
        if (getActivity() != null)
            getActivity().getFragmentManager().popBackStack();
    }

}
