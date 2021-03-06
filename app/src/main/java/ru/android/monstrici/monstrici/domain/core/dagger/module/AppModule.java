package ru.android.monstrici.monstrici.domain.core.dagger.module;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.android.monstrici.monstrici.domain.core.dagger.scope.ApplicationContext;
import ru.android.monstrici.monstrici.ui.view.application.ApplicationCore;

/**
 * Created by elisiumGusev
 *
 * @Date 21/10/2017
 * @Author Andrei Gusev
 */

@Module
public class AppModule {
    private final ApplicationCore mApplication;

    public AppModule(ApplicationCore application) {
        mApplication = application;
    }

    @ApplicationContext
    @Provides
    protected Context provideContext() {
        return mApplication;
    }

    @Provides
    @Singleton
    protected ApplicationCore provideApplication() {
        return mApplication;
    }

    @Provides
    SharedPreferences provideSharedPrefs() {
        return mApplication.getSharedPreferences("prefs", Context.MODE_PRIVATE);
    }
}
