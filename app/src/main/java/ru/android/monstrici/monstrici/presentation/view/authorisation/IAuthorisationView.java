package ru.android.monstrici.monstrici.presentation.view.authorisation;

import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import ru.android.monstrici.monstrici.presentation.view.base.IBaseView;
import ru.android.monstrici.monstrici.utils.Message;

/**
 * Created by elisiumGusev
 *
 * @Date 20/10/2017
 * @Author Andrei Gusev
 */
@StateStrategyType(AddToEndStrategy.class)
public interface IAuthorisationView extends IBaseView {
    void onLoginSuccess(Long id);

    void onLoginFailed(Message message);
}
