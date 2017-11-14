package ru.android.monstrici.monstrici.domain.core.dagger.module;

import android.app.Activity;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.android.monstrici.monstrici.domain.ConnectionManager;
import ru.android.monstrici.monstrici.domain.core.dagger.scope.ActivityContext;
import ru.android.monstrici.monstrici.domain.core.dagger.scope.ApplicationContext;

/**
 * Created by elisiumGusev
 *
 * @Date 21/10/2017
 * @Author Andrei Gusev
 */
@Module
public class CoreModule {


    public CoreModule() {

    }

    @Provides
    @Singleton
    ConnectionManager provideConnectionManager(@ApplicationContext Context context) {
        return new ConnectionManager(context);
    }
}
