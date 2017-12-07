package ru.android.monstrici.monstrici.data.repository.local;

import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Flowable;
import ru.android.monstrici.monstrici.data.model.Monster;
import ru.android.monstrici.monstrici.data.model.Response;
import ru.android.monstrici.monstrici.data.model.Star;
import ru.android.monstrici.monstrici.data.model.StarStorage;
import ru.android.monstrici.monstrici.data.model.User;
import ru.android.monstrici.monstrici.data.repository.IUserRepository;
import ru.android.monstrici.monstrici.domain.base.IDataCallback;

/**
 * Created by elisiumGusev
 *
 * @Date 20/10/2017
 * @Author Andrei Gusev
 */

public class LocalUserRepository implements IUserRepository {

    public LocalUserRepository() {
    }

    @Override
    public void getUser(String id, @NonNull IDataCallback<User> callback) {

    }

    @Override
    public User getCurrentUser() {
        return null;
    }

    @Override
    public void getMonster(String monsterId,String userId, @NonNull IDataCallback<Monster> callback) {

    }

    @Override
    public void getMonsters(@NonNull IDataCallback<Monster> callback) {

    }

    @Override
    public void getStar(String starId, String userId, @NonNull IDataCallback<Star> callback) {

    }

    @Override
    public void getUsers(@NonNull IDataCallback<User> callback) {
    }

    @Override
    public void getUsersByClass(@NonNull IDataCallback<User> callback) {

    }

    @Override
    public void checkLogin(String login, String password, @NonNull IDataCallback<User> callback) {
//        Response<User> response = new Response<>();
//        User user = new User();
//        user.setId("1");
//        response.setBody(user);
//
//        callback.onReceiveDataSuccess(response);
    }

    @Override
    public void saveMonster(Monster monster) {

    }

    @Override
    public void saveUser(User user) {

    }

    @Override
    public void updateStar(Star star, String userId) {

    }

    @Override
    public void addStar(Star star, String userId) {

    }
}
