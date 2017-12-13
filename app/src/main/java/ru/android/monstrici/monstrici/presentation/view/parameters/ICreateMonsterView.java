package ru.android.monstrici.monstrici.presentation.view.parameters;

import android.graphics.drawable.Drawable;

import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import ru.android.monstrici.monstrici.presentation.view.base.IBaseView;

/**
 * Created by elisium
 *
 * @Date 11/12/2017
 * @Author Andrei Gusev
 */
@StateStrategyType(AddToEndStrategy.class)
public interface ICreateMonsterView extends IBaseView {
    /**
     * @param res
     * @param bodyPart 1 -body ,2 -mouth, 3- eye
     */
    void updateMonster(Drawable res, int bodyPart);
}
