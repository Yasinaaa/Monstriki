package ru.android.monstrici.monstrici.domain.core.dagger.module;

import android.app.Activity;
import android.content.Context;

import dagger.Module;
import dagger.Provides;
import ru.android.monstrici.monstrici.domain.core.dagger.scope.ActivityContext;

/**
 * Created by elisiumGusev
 *
 * @Date 21/10/2017
 * @Author Andrei Gusev
 */
@Module
public class CoreModule {
    private Activity mActivity;

    public CoreModule(Activity activity) {
        mActivity = activity;
    }

    @Provides
    @ActivityContext
    Context provideContext() {
        return mActivity;
    }

    @Provides
    Activity provideActivity() {
        return mActivity;
    }
}
