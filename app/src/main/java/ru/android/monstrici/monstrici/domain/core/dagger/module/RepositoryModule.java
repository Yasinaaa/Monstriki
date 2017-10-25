package ru.android.monstrici.monstrici.domain.core.dagger.module;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import ru.android.monstrici.monstrici.data.repository.IUserRepository;
import ru.android.monstrici.monstrici.data.repository.local.LocalUserRepository;
import ru.android.monstrici.monstrici.data.repository.remote.RemoteUserRepository;
import ru.android.monstrici.monstrici.domain.core.dagger.scope.Local;
import ru.android.monstrici.monstrici.domain.core.dagger.scope.Remote;

/**
 * Created by elisiumGusev
 *
 * @Date 21/10/2017
 * @Author Andrei Gusev
 */

@Module
public abstract class RepositoryModule {
    @Singleton
    @Binds
    @Local
    abstract IUserRepository provideUserLocalDataSource(LocalUserRepository dataSource);

    @Singleton
    @Binds
    @Remote
    abstract IUserRepository provideUserRemoteDataSource(RemoteUserRepository dataSource);
}
