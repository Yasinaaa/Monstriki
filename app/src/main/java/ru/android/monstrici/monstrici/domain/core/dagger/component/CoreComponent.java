package ru.android.monstrici.monstrici.domain.core.dagger.component;

import ru.android.monstrici.monstrici.ui.view.main_pupil.fragments.MonsterFragment;
import ru.android.monstrici.monstrici.ui.view.main_pupil.fragments.SettingsFragment;
import ru.android.monstrici.monstrici.ui.view.main_pupil.fragments.StarFragment;
import ru.android.monstrici.monstrici.ui.view.main_pupil.fragments.SweetsFragment;
import ru.android.monstrici.monstrici.ui.view.main_teacher.fragments.JournalFragment;

/**
 * Created by elisiumGusev
 *
 * @Date 21/10/2017
 * @Author Andrei Gusev
 */

public interface CoreComponent {

    //MAIN TEACHER'S FRAGMENTS
    void inject(JournalFragment fragment);

    //MAIN TEACHER'S FRAGMENTS

    //MAIN PUPIL'S FRAGMENTS
    void inject(SweetsFragment fragment);

    void inject(StarFragment fragment);

    void inject(SettingsFragment fragment);

    void inject(MonsterFragment fragment);
    //MAIN PUPIL'S FRAGMENTS


}
