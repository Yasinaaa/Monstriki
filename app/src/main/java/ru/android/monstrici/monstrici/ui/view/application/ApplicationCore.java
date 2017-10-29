package ru.android.monstrici.monstrici.ui.view.application;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.google.firebase.FirebaseApp;

import ru.android.monstrici.monstrici.domain.core.dagger.component.AppComponent;
import ru.android.monstrici.monstrici.domain.core.dagger.component.DaggerAppComponent;
import ru.android.monstrici.monstrici.domain.core.dagger.component.IHasComponent;
import ru.android.monstrici.monstrici.domain.core.dagger.module.AppModule;

/**
 * Created by yasina on 13.10.17.
 */

public class ApplicationCore extends MultiDexApplication implements IHasComponent {
    protected AppComponent mAppComponent;

    public static ApplicationCore get(Context context) {
        return (ApplicationCore) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
        mAppComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .build();
        mAppComponent.inject(this);
    }


    @Override
    public AppComponent getComponent() {
        return mAppComponent;
    }
}