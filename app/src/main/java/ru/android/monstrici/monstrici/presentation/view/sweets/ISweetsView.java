package ru.android.monstrici.monstrici.presentation.view.sweets;

import java.util.ArrayList;
import java.util.List;

import ru.android.monstrici.monstrici.data.model.Star;
import ru.android.monstrici.monstrici.presentation.view.base.IBaseView;

/**
 * Created by yasina on 07.12.17.
 */

public interface ISweetsView extends IBaseView {

    void setDonutsCount(ArrayList<Star> starsList);
}
