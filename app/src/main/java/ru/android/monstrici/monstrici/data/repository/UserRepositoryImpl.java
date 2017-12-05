package ru.android.monstrici.monstrici.data.repository;

import android.support.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import ru.android.monstrici.monstrici.data.model.Monster;
import ru.android.monstrici.monstrici.data.model.Response;
import ru.android.monstrici.monstrici.data.model.Star;
import ru.android.monstrici.monstrici.data.model.StarStorage;
import ru.android.monstrici.monstrici.data.model.User;
import ru.android.monstrici.monstrici.domain.base.IDataCallback;
import ru.android.monstrici.monstrici.utils.Message;

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
    private String mCurrentUserId;
    private boolean isFirstTime = true;
    private boolean mCacheIsDirty = false;

    @Inject
    public UserRepositoryImpl() {
        mCachedUserMap = new HashMap<>();
    }

    @Override
    public void getUser(String id, @NonNull IDataCallback<User> callback) {
        if (isFirstTime) {
            mCurrentUserId = id;
            isFirstTime = false;
        }

        IDataCallback<User> repCallback = new IDataCallback<User>() {
            @Override
            public void onReceiveDataSuccess(Response<User> response) {
                callback.onReceiveDataSuccess(response);
                mCachedUserMap.put(response.getBody().getId(), response.getBody());
            }

            @Override
            public void onReceiveDataFailure(Message message) {
                callback.onReceiveDataFailure(message);
            }
        };

        if (mCachedUserMap == null || mCachedUserMap.size() == 0 || !mCachedUserMap.containsKey(id)) {
            mRemoteUserRepository.getUser(mCurrentUserId, repCallback);
        } else {
            callback.onReceiveDataSuccess(new Response<User>().setBody(mCachedUserMap.get(id)));
        }
    }

    @Override
    public void getMonster(String id, @NonNull IDataCallback<Monster> callback) {
        IDataCallback<Monster> monsterCallback = new IDataCallback<Monster>() {
            @Override
            public void onReceiveDataSuccess(Response<Monster> response) {
                mCachedUserMap.get(mCurrentUserId).setMonster(response.getBody());
                callback.onReceiveDataSuccess(response);
            }

            @Override
            public void onReceiveDataFailure(Message message) {

            }
        };
        if (mCachedUserMap != null && mCachedUserMap.size() != 0) {
            if (mCachedUserMap.get(mCurrentUserId).getMonster().getBody() == null) {
                mRemoteUserRepository.getMonster(mCachedUserMap.get(mCurrentUserId).getMonster().getId(), monsterCallback);
            } else {
                callback.onReceiveDataSuccess(new Response<Monster>()
                        .setBody(mCachedUserMap.get(mCurrentUserId).getMonster()));
            }

        }
    }

    @Override
    public void getStars(String id, @NonNull IDataCallback<Star> callback) {
        IDataCallback<Star> starsCallback = new IDataCallback<Star>() {
            @Override
            public void onReceiveDataSuccess(Response<Star> response) {
                StarStorage starStorage = new StarStorage();
                starStorage.setStars(response.getBodyMap());
                mCachedUserMap.get(mCurrentUserId).setStars(starStorage);
                callback.onReceiveDataSuccess(response);
            }

            @Override
            public void onReceiveDataFailure(Message message) {
                callback.onReceiveDataFailure(message);
            }
        };
        if (mCachedUserMap != null && mCachedUserMap.size() != 0) {
            if (mCachedUserMap.get(mCurrentUserId).getStarStorage() == null) {
                mRemoteUserRepository.getStars(id, starsCallback);
            } else {
                callback.onReceiveDataSuccess(new Response<Star>()
                        .setBody(mCachedUserMap.get(mCurrentUserId).getStarStorage().getStars()));
            }

        }
    }

    @Override
    public void getMonsters(@NonNull IDataCallback<Monster> callback) {

    }

    @Override
    public void getUsers(@NonNull IDataCallback<User> callback) {
        if (mCachedUserMap != null || mCachedUserMap.size() != 0) {
            mRemoteUserRepository.getUsers(callback);
        } else {

        }
    }

    @Override
    public void checkLogin(String login, String password, @NonNull IDataCallback<User> callback) {
        mRemoteUserRepository.checkLogin(login, password, new IDataCallback<User>() {
            @Override
            public void onReceiveDataSuccess(Response<User> response) {
                callback.onReceiveDataSuccess(response);
                mCachedUserMap.put(response.getBody().getId(), response.getBody());
                mCurrentUserId = response.getBody().getId();
            }

            @Override
            public void onReceiveDataFailure(Message message) {
                callback.onReceiveDataFailure(message);
            }
        });

    }

    @Override
    public void saveMonster(Monster monster) {
        if (!monster.equals(mCachedUserMap.get(mCurrentUserId).getMonster())) {
            mCachedUserMap.get(mCurrentUserId).setMonster(monster);
            //  mRemoteUserRepository.saveMonster(monster);
        }
    }

    @Override
    public void saveUser(User user) {
        if (!user.equals(mCachedUserMap.get(user.getId()))) {
            mCachedUserMap.put(user.getId(), user);
            //  mRemoteUserRepository.saveUser(user);
        }
    }

    @Override
    public void saveStar(Star star, String userId) {
        Star cacheStar = mCachedUserMap.get(userId).getStarStorage().getStar(star.getId());
        if (!star.equals(cacheStar)) {
            mCachedUserMap.get(userId).getStarStorage().updateStar(star);
            //  mRemoteUserRepository.saveStar(star);
        }
    }

    @Override
    public void addStar(Star star, String userId) {
        if (!mCachedUserMap.get(userId).getStarStorage().getStars().containsKey(star)) {
            mCachedUserMap.get(userId).getStarStorage().getStars().put(star.getId(), star);
            mRemoteUserRepository.addStar(star, userId);
        }
    }

}
