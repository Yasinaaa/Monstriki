package ru.android.monstrici.monstrici.presentation.view.parameters;

import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import ru.android.monstrici.monstrici.presentation.view.base.IBaseView;

/**
 * Created by yasina on 11.11.17.
 */
@StateStrategyType(AddToEndStrategy.class)
public interface IParametersView extends IBaseView {

    void onSetParametersSuccess();
    void onSetParametersFail();
}
