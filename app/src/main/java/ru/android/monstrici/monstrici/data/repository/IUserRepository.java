package ru.android.monstrici.monstrici.data.repository;

import android.support.annotation.NonNull;

import ru.android.monstrici.monstrici.data.model.User;
import ru.android.monstrici.monstrici.domain.base.IDataCallback;

/**
 * Created by elisiumGusev
 *
 * @Date 20/10/2017
 * @Author Andrei Gusev
 */

public interface IUserRepository {


    void getUser(String id, @NonNull IDataCallback<User> callback);

    void getUsers(@NonNull IDataCallback<User> callback);

    void checkLogin(String login, String password, @NonNull IDataCallback<User> callback);
}