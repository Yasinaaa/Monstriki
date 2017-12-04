package ru.android.monstrici.monstrici.presentation.view.menu;

import java.util.List;

import ru.android.monstrici.monstrici.data.model.User;
import ru.android.monstrici.monstrici.presentation.view.base.IBaseView;

/**
 * Created by elisium
 *
 * @Date 02/12/2017
 * @Author Andrei Gusev
 */

public interface ITeacherView extends IBaseView {
    void onUsersPrepare(List<User> userList);
}
