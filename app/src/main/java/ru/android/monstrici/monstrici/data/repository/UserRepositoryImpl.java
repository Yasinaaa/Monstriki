package ru.android.monstrici.monstrici.data.repository;

import android.support.annotation.NonNull;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import ru.android.monstrici.monstrici.data.model.User;
import ru.android.monstrici.monstrici.domain.base.IDataCallback;

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

    private Map<String, User> mCachedUserMap;
    private boolean mCacheIsDirty = false;

    @Inject
    public UserRepositoryImpl() {
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
        if (mCachedUserMap == null || mCachedUserMap.size() == 0) {
            mRemoteUserRepository.getUsers(callback);
        } else {

        }
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
