package ru.android.monstrici.monstrici.domain.core.dagger.module;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ru.android.monstrici.monstrici.data.repository.IUserRepository;
import ru.android.monstrici.monstrici.data.repository.local.LocalUserRepository;
import ru.android.monstrici.monstrici.data.repository.remote.RemoteUserRepository;

/**
 * Created by elisiumGusev
 *
 * @Date 23/10/2017
 * @Author Andrei Gusev
 */
@Module
public class NetModule {

    @Named("Remote")
    @Singleton
    @Provides
    IUserRepository getRemoteUserRepository() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        DatabaseReference database = FirebaseDatabase.getInstance().getReference();
        return new RemoteUserRepository(auth, database);
    }

    @Named("Local")
    @Singleton
    @Provides
    IUserRepository getLocalUserRepository() {
        return new LocalUserRepository();
    }
}

