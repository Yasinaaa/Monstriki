package ru.android.monstrici.monstrici.presentation.view.pupil;

import java.util.List;

import ru.android.monstrici.monstrici.data.model.Monster;
import ru.android.monstrici.monstrici.data.model.User;
import ru.android.monstrici.monstrici.presentation.view.base.IBaseView;

/**
 * Created by elisium
 *
 * @Date 05/12/2017
 * @Author Andrei Gusev
 */

public interface IStarDesc extends IBaseView {
    void showMonsters(List<User> users);
}
