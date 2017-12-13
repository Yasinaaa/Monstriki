package ru.android.monstrici.monstrici.presentation.view.journal;

import java.util.List;

import ru.android.monstrici.monstrici.data.model.Star;
import ru.android.monstrici.monstrici.data.model.User;
import ru.android.monstrici.monstrici.presentation.view.base.IBaseView;

/**
 * Created by elisium
 *
 * @Date 02/12/2017
 * @Author Andrei Gusev
 */

public interface IJournalView extends IBaseView {

    void onTeacherPrepare(User user);

    void onUsersPrepare(User user);

    void onStarsGet(User user, List<Star> stars);
}
