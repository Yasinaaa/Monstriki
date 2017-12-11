package ru.android.monstrici.monstrici.presentation.view.pupil;

import java.util.ArrayList;

import ru.android.monstrici.monstrici.data.model.Star;
import ru.android.monstrici.monstrici.data.model.User;
import ru.android.monstrici.monstrici.presentation.view.base.IBaseView;

/**
 * Created by yasina on 07.12.17.
 */

public interface IPupilView extends IBaseView {

    void setDonutsCount(ArrayList<Star> starsList);
    void setUser(User user);
}
