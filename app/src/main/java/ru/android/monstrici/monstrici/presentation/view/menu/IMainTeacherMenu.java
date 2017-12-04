package ru.android.monstrici.monstrici.presentation.view.menu;

import java.util.List;

import ru.android.monstrici.monstrici.data.model.Star;
import ru.android.monstrici.monstrici.data.model.User;
import ru.android.monstrici.monstrici.presentation.view.base.IBaseView;

/**
 * Created by yasina on 04.12.17.
 */

public interface IMainTeacherMenu extends IMainMenu {
    void onFormGet(List<User> users);
}
