package ru.android.monstrici.monstrici.presentation.view.settings;

import android.graphics.drawable.Drawable;

import ru.android.monstrici.monstrici.data.model.User;
import ru.android.monstrici.monstrici.presentation.view.base.IBaseView;

/**
 * Created by elisium
 *
 * @Date 28/11/2017
 * @Author Andrei Gusev
 */

public interface SettingsView extends IBaseView {

    /**
     * @param res
     * @param bodyPart 1 -body ,2 -mouth, 3- eye
     */
    void updateMonster(Drawable res, int bodyPart);

    void updateUser(User user);


    void setMonsterName(String name);
}
