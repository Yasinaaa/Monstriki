package ru.android.monstrici.monstrici.domain;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import javax.inject.Singleton;

import ru.android.monstrici.monstrici.domain.core.dagger.scope.ApplicationContext;

/**
 * Created by elisium
 *
 * @Date 09/11/2017
 * @Author Andrei Gusev
 */
@Singleton
public class ConnectionManager {


    @ApplicationContext
    private Context mContext;

    public ConnectionManager(Context context) {
        mContext = context;
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            return netInfo != null && netInfo.isConnectedOrConnecting();
        } else {
            return false;
        }
    }
}
