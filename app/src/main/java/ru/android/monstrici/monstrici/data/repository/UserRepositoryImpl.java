package ru.android.monstrici.monstrici.data.repository;

import android.support.annotation.NonNull;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import ru.android.monstrici.monstrici.data.model.Response;
import ru.android.monstrici.monstrici.data.model.User;
import ru.android.monstrici.monstrici.domain.base.IDataCallback;
import ru.android.monstrici.monstrici.domain.core.dagger.scope.Local;
import ru.android.monstrici.monstrici.domain.core.dagger.scope.Remote;

import static ru.android.monstrici.monstrici.utils.Preconditions.checkNotNull;

/**
 * Created by elisiumGusev
 *
 * @Date 20/10/2017
 * @Author Andrei Gusev
 */
@Singleton
public class UserRepositoryImpl implements IUserRepository {
    @Inject
    @Named("Local")
    IUserRepository mLocalUserRepository;

    @Inject
    @Named("Remote")
    IUserRepository mRemoteUserRepository;

    private Map<Long, User> mCachedUserMap;
    private boolean mCacheIsDirty = false;

    @Inject
    public UserRepositoryImpl(
                              ) {
        //mRemoteUserRepository = checkNotNull(userRemoteRepository);
        //mLocalUserRepository = checkNotNull(userLocalRepository);
    }

    @Override
    public void getUser(String id, @NonNull IDataCallback<User> callback) {
        if (mCachedUserMap == null || mCachedUserMap.size() == 0) {
            mRemoteUserRepository.getUser(id, callback);
        } else {
        }
    }

    @Override
    public void getUsers(@NonNull IDataCallback<User> callback) {

    }

    @Override
    public void checkLogin(String login, String password, @NonNull IDataCallback<User> callback) {
        if (mCachedUserMap == null || mCachedUserMap.size() == 0) {
            mRemoteUserRepository.checkLogin(login, password, callback);
        } else {
            mLocalUserRepository.checkLogin(login, password, callback);
        }
    }
}
