package ru.android.monstrici.monstrici.domain.core.dagger.component;

import ru.android.monstrici.monstrici.ui.view.main_pupil.fragments.MonsterFragment;
import ru.android.monstrici.monstrici.ui.view.main_pupil.fragments.SettingsFragment;

/**
 * Created by elisiumGusev
 *
 * @Date 21/10/2017
 * @Author Andrei Gusev
 */

public interface CoreComponent {
    void inject(SettingsFragment fragment);
    void inject(MonsterFragment fragment);
}
