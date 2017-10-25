package ru.android.monstrici.monstrici.presentation.view.authorisation;

import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import ru.android.monstrici.monstrici.presentation.view.base.IBaseView;

/**
 * Created by elisiumGusev
 *
 * @Date 20/10/2017
 * @Author Andrei Gusev
 */
@StateStrategyType(AddToEndStrategy.class)
public interface ISplashView extends IBaseView {
    void loginSuccess(long id);

    void loginFail();
}
