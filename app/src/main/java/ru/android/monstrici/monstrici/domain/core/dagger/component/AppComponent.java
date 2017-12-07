package ru.android.monstrici.monstrici.domain.core.dagger.component;

import javax.inject.Singleton;

import dagger.Component;
import ru.android.monstrici.monstrici.data.repository.UserRepositoryImpl;
import ru.android.monstrici.monstrici.domain.core.dagger.module.RepositoryModule;
import ru.android.monstrici.monstrici.domain.core.dagger.module.AppModule;
import ru.android.monstrici.monstrici.domain.core.dagger.module.CoreModule;
import ru.android.monstrici.monstrici.domain.core.dagger.module.NetModule;
import ru.android.monstrici.monstrici.presentation.presenter.authorisation.AuthorisationPresenter;
import ru.android.monstrici.monstrici.presentation.presenter.main.PupilMenuPresenter;
import ru.android.monstrici.monstrici.presentation.presenter.main.TeacherMenuPresenter;
import ru.android.monstrici.monstrici.presentation.presenter.monster.MonsterPresenter;
import ru.android.monstrici.monstrici.presentation.presenter.pupil.StarPresenter;
import ru.android.monstrici.monstrici.presentation.presenter.settings.SettingsPresenter;
import ru.android.monstrici.monstrici.presentation.presenter.sweets.SweetsPresenter;
import ru.android.monstrici.monstrici.presentation.presenter.teacher.JournalPresenter;
import ru.android.monstrici.monstrici.ui.view.application.ApplicationCore;
import ru.android.monstrici.monstrici.ui.view.authorisation.AuthorisationActivity;
import ru.android.monstrici.monstrici.ui.view.authorisation.SplashActivity;
import ru.android.monstrici.monstrici.ui.view.base.BaseActivity;
import ru.android.monstrici.monstrici.ui.view.base.BaseFragment;

/**
 * Created by elisiumGusev
 *
 * @Date 21/10/2017
 * @Author Andrei Gusev
 */

@Singleton
@Component(modules = {
        AppModule.class,
        CoreModule.class,
        NetModule.class,
        RepositoryModule.class
})
public interface AppComponent extends
        CoreComponent,
        NetComponent {

    void inject(ApplicationCore application);

    void inject(AuthorisationActivity activity);

    void inject(SplashActivity activity);

    void inject(BaseActivity activity);

    void inject(BaseFragment fragment);

    void inject(AuthorisationPresenter presenter);

    void inject(TeacherMenuPresenter presenter);

    void inject(JournalPresenter presenter);

    void inject(SettingsPresenter presenter);

    void inject(MonsterPresenter presenter);

    void inject(StarPresenter presenter);

    void inject(PupilMenuPresenter presenter);

    //MAIN PUPIL'S PRESENTERS
    void inject(SweetsPresenter presenter);
    //MAIN PUPIL'S PRESENTERS

    UserRepositoryImpl getUserRepository();
}