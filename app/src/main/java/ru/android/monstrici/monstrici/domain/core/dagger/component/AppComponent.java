package ru.android.monstrici.monstrici.domain.core.dagger.component;

import javax.inject.Singleton;

import dagger.Component;
import ru.android.monstrici.monstrici.data.repository.UserRepositoryImpl;
import ru.android.monstrici.monstrici.domain.core.dagger.module.RepositoryModule;
import ru.android.monstrici.monstrici.domain.core.dagger.module.AppModule;
import ru.android.monstrici.monstrici.domain.core.dagger.module.CoreModule;
import ru.android.monstrici.monstrici.domain.core.dagger.module.NetModule;
import ru.android.monstrici.monstrici.presentation.presenter.authorisation.AuthorisationPresenter;
import ru.android.monstrici.monstrici.ui.view.application.ApplicationCore;
import ru.android.monstrici.monstrici.ui.view.authorisation.AuthorisationActivity;
import ru.android.monstrici.monstrici.ui.view.base.BaseActivity;

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
    void inject(BaseActivity activity);
    void inject(AuthorisationPresenter presenter);

    UserRepositoryImpl getUserRepository();
}