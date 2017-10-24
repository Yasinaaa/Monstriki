package ru.android.monstrici.monstrici.data.repository.remote;

import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import ru.android.monstrici.monstrici.data.model.User;
import ru.android.monstrici.monstrici.data.repository.IUserRepository;
import ru.android.monstrici.monstrici.domain.base.IDataCallback;

/**
 * Created by elisiumGusev
 *
 * @Date 20/10/2017
 * @Author Andrei Gusev
 */
@Singleton
public class RemoteUserRepository implements IUserRepository {

    @Inject
    public RemoteUserRepository() {
    }


    @Override
    public void getUser(String id, @NonNull IDataCallback<User> callback) {

    }

    @Override
    public void getUsers(@NonNull String userId, @NonNull IDataCallback<User> callback) {

    }

    @Override
    public void checkLogin(String login, String password, @NonNull IDataCallback<User> callback) {

    }
}
