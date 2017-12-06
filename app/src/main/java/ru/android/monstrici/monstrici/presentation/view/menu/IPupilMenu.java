package ru.android.monstrici.monstrici.presentation.view.menu;

import com.arellomobile.mvp.MvpView;

import ru.android.monstrici.monstrici.data.model.Monster;
import ru.android.monstrici.monstrici.data.model.User;
import ru.android.monstrici.monstrici.presentation.view.base.IBaseView;

/**
 * Created by elisium
 *
 * @Date 06/12/2017
 * @Author Andrei Gusev
 */

public interface IPupilMenu extends IBaseView {
    void onUsersGet(User user);
}
