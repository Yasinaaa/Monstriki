package ru.android.monstrici.monstrici.presentation.view.star;

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

public interface IStarView extends IBaseView {

    void getUsersRateList(List<User> users);

    void getChoosedUser(User user, Monster monster);
}
