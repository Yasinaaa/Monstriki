package ru.android.monstrici.monstrici.data.repository;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;

import java.util.List;

import io.reactivex.Flowable;
import ru.android.monstrici.monstrici.data.model.Monster;
import ru.android.monstrici.monstrici.data.model.Star;
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

    void getMonster(String id, @NonNull IDataCallback<Monster> callback);

    void getMonsters(@NonNull IDataCallback<Monster> callback);

    void getStars(String id, @NonNull IDataCallback<Star> callback);

    void getUsers(@NonNull IDataCallback<User> callback);

    void checkLogin(String login, String password, @NonNull IDataCallback<User> callback);

    void saveMonster(Monster monster);

    void saveUser(User user);
}