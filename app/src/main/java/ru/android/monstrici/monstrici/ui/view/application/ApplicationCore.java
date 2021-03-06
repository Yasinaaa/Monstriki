package ru.android.monstrici.monstrici.ui.view.application;

import android.content.Context;
import android.support.multidex.MultiDexApplication;

import com.crashlytics.android.Crashlytics;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.FirebaseDatabase;

import io.fabric.sdk.android.Fabric;
import ru.android.monstrici.monstrici.R;
import ru.android.monstrici.monstrici.domain.core.dagger.component.AppComponent;
import ru.android.monstrici.monstrici.domain.core.dagger.component.DaggerAppComponent;
import ru.android.monstrici.monstrici.domain.core.dagger.component.IHasComponent;
import ru.android.monstrici.monstrici.domain.core.dagger.module.AppModule;
import ru.android.monstrici.monstrici.domain.core.dagger.module.CoreModule;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

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
        Fabric.with(this, new Crashlytics());
        firebaseSetup();
        mAppComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .coreModule(new CoreModule())
                .build();
        mAppComponent.inject(this);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(getResources().getString(R.string.main_font))
                .setFontAttrId(R.attr.fontPath)
                .build()
        );
    }

    private void firebaseSetup() {
        FirebaseApp.initializeApp(this);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }

    @Override
    public AppComponent getComponent() {
        return mAppComponent;
    }
}
