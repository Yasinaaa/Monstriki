package ru.android.monstrici.monstrici.data.repository;

import android.support.annotation.NonNull;

import java.util.ArrayList;
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

    private final Map<String, User> mCachedUserMap;
    private String mCurrentUserId;
    private String mCurrentClassId;
    private boolean isFirstTime = true;
    private boolean isFirstTimeClass = true;
    private boolean mCacheIsDirty = false;
    private final Map<String, IDataCallback> requests = new HashMap<>();

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
                mCachedUserMap.put(response.getBody().getId(), response.getBody());
                if (isFirstTimeClass) {
                    mCurrentClassId = response.getBody().getSchoolClass().getId();
                    isFirstTimeClass = false;
                }
                if (response.getBody().getPosition().equals("pupil")) {
                    getStar(response.getBody().getStarId(), response.getBody().getId(), new IDataCallback<Star>() {
                        @Override
                        public void onReceiveDataSuccess(Response<Star> starResponse) {
                            requests.get("getUser").onReceiveDataSuccess(response);
                            requests.remove("getUser");
                        }

                        @Override
                        public void onReceiveDataFailure(Message message) {
                            callback.onReceiveDataFailure(message);
                        }
                    });
                } else {
                    requests.get("getUser").onReceiveDataSuccess(response);
                    requests.remove("getUser");
                }
            }

            @Override
            public void onReceiveDataFailure(Message message) {
                callback.onReceiveDataFailure(message);
            }
        };

        if (!mCachedUserMap.containsKey(mCurrentUserId)) {
            if (!requests.containsKey("getUser")) {
                mRemoteUserRepository.getUser(mCurrentUserId, repCallback);
            }
            requests.put("getUser", callback);
        } else {
            callback.onReceiveDataSuccess(new Response<User>().setBody(mCachedUserMap.get(mCurrentUserId)));
        }
    }

    @Override
    public void getMonster(String id, @NonNull IDataCallback<Monster> callback) {
        IDataCallback<Monster> monsterCallback = new IDataCallback<Monster>() {
            @Override
            public void onReceiveDataSuccess(Response<Monster> response) {
                requests.remove("getMonster");
                mCachedUserMap.get(mCurrentUserId).setMonster(response.getBody());
                callback.onReceiveDataSuccess(response);
            }

            @Override
            public void onReceiveDataFailure(Message message) {

            }
        };
        if (mCachedUserMap.get(mCurrentUserId).getMonster().getBody() == null) {
            if (!requests.containsKey("getMonster")) {
                mRemoteUserRepository.getMonster(mCachedUserMap.get(mCurrentUserId).getMonster().getId(), monsterCallback);
            }
            requests.put("getMonster", callback);
        } else {
            callback.onReceiveDataSuccess(new Response<Monster>()
                    .setBody(mCachedUserMap.get(mCurrentUserId).getMonster()));
        }
    }

    @Override
    public void getStar(String starId, String userId, @NonNull IDataCallback<Star> callback) {
        IDataCallback<Star> starsCallback = new IDataCallback<Star>() {
            @Override
            public void onReceiveDataSuccess(Response<Star> response) {
                requests.remove("getStar");
                StarStorage starStorage = new StarStorage();
                starStorage.setStars(response.getBodyMap());
                mCachedUserMap.get(userId).setStars(starStorage);
                callback.onReceiveDataSuccess(response);
            }

            @Override
            public void onReceiveDataFailure(Message message) {
                callback.onReceiveDataFailure(message);
            }
        };
        if (mCachedUserMap.get(userId).getStarStorage().getStars().size() == 0) {
            if (!requests.containsKey("getStar")) {
                mRemoteUserRepository.getStar(starId, userId, starsCallback);
            }
            requests.put("getStar", callback);

        } else {
            callback.onReceiveDataSuccess(new Response<Star>()
                    .setBody(mCachedUserMap.get(userId).getStarStorage().getStars()));
        }
    }

    @Override
    public void getMonsters(@NonNull IDataCallback<Monster> callback) {

    }

    @Override
    public void getUsers(@NonNull IDataCallback<User> callback) {
        IDataCallback<User> repCallback = new IDataCallback<User>() {
            @Override
            public void onReceiveDataSuccess(Response<User> response) {
                for (User user : response.getBodyList()) {
                    mCachedUserMap.put(user.getId(), user);
                }
                requests.get("getUsers").onReceiveDataSuccess(response);
                requests.remove("getUsers");
            }

            @Override
            public void onReceiveDataFailure(Message message) {
                callback.onReceiveDataFailure(message);
            }
        };
        if (!requests.containsKey("getUsers")) {
            mRemoteUserRepository.getUsers(repCallback);
        }
        requests.put("getUsers", callback);
    }

    @Override
    public void getUsersByClass(@NonNull IDataCallback<User> callback) {
        ArrayList<User> users = new ArrayList<>();
        for (Map.Entry<String, User> user : mCachedUserMap.entrySet()) {
            if (user.getValue().getSchoolClass().getId().equals(mCurrentClassId) &&
                    user.getValue().getPosition().equals("pupil")) {
                users.add(user.getValue());
            }
        }
        callback.onReceiveDataSuccess(new Response<User>().setBody(users));
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
            mRemoteUserRepository.saveMonster(monster);
        }
    }

    @Override
    public void saveUser(User user) {
        if (!user.equals(mCachedUserMap.get(user.getId()))) {
            mCachedUserMap.put(user.getId(), user);
            mRemoteUserRepository.saveUser(user);
        }
    }

    @Override
    public void updateStar(Star star, String userId) {
        Star cacheStar = mCachedUserMap.get(userId).getStarStorage().getStar(star.getId());
        if (!star.equals(cacheStar)) {
            mCachedUserMap.get(userId).getStarStorage().updateStar(star);
            mRemoteUserRepository.updateStar(star, userId);
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
