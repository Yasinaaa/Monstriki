package ru.android.monstrici.monstrici.presentation.view.monster;

import android.graphics.drawable.Drawable;

import ru.android.monstrici.monstrici.data.model.Monster;
import ru.android.monstrici.monstrici.presentation.view.base.IBaseView;

/**
 * Created by elisium
 *
 * @Date 28/11/2017
 * @Author Andrei Gusev
 */

public interface MonsterView extends IBaseView {
    /**
     * @param res
     * @param bodyPart 1 -body ,2 -mouth, 3- eye
     */
    void updateMonster(Drawable res, int bodyPart);

    void onMonsterGet(Monster monster);
}
