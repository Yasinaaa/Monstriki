package ru.android.monstrici.monstrici.presentation.view.base;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import ru.android.monstrici.monstrici.utils.ErrorMessage;

/**
 * Created by elisiumGusev
 *
 * @Date 20/10/2017
 * @Author Andrei Gusev
 */
@StateStrategyType(AddToEndStrategy.class)
public interface IBaseView extends MvpView {
    void showLoading(boolean flag);

    void showError(ErrorMessage message);
}
