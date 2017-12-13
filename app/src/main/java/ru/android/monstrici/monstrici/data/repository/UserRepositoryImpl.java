package ru.android.monstrici.monstrici.data.repository;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import ru.android.monstrici.monstrici.data.model.ListCallbacks;
import ru.android.monstrici.monstrici.data.model.Monster;
import ru.android.monstrici.monstrici.data.model.Response;
import ru.android.monstrici.monstrici.data.model.SchoolClass;
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
    private List<Monster> mCachedMonsterList;
    private List<SchoolClass> mCachedClassList;
    private String mCurrentUserId;
    private String mCurrentClassId;
    private boolean isFirstTime = true;
    private boolean isFirstTimeClass = true;
    private boolean mCacheIsDirty = false;
    private final Map<String, ListCallbacks> requests = new HashMap<>();

    @Inject
    public UserRepositoryImpl() {
        mCachedUserMap = new HashMap<>();
        mCachedMonsterList = new ArrayList<>();
        mCachedClassList = new ArrayList<>();
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
                            requests.get("getUser")
                                    .getIDataCallbacks()
                                    .get(id)
                                    .onReceiveDataSuccess(response);
//
                            removeRequest("getUser", id);
                        }

                        @Override
                        public void onReceiveDataFailure(Message message) {
                            callback.onReceiveDataFailure(message);
                        }
                    });
                } else {
                    requests.get("getUser")
                            .getIDataCallbacks()
                            .get(id)
                            .onReceiveDataSuccess(response);
                    removeRequest("getUser", id);
                }
            }

            @Override
            public void onReceiveDataFailure(Message message) {
                callback.onReceiveDataFailure(message);
            }
        };

        if (!mCachedUserMap.containsKey(id)) {
            if (!requests.containsKey("getUser") ||
                    requests.get("getUser").getIDataCallbacks().containsKey(id)) {
                if (!requests.containsKey("getUser"))
                    requests.put("getUser", new ListCallbacks());
                mRemoteUserRepository.getUser(id, repCallback);
            }
            requests.put("getUser", requests.get("getUser").add(id, callback));
        } else {
            callback.onReceiveDataSuccess(new Response<User>().setBody(mCachedUserMap.get(id)));
        }
    }

    @Override
    public User getCurrentUser() {
        return mCachedUserMap.get(mCurrentUserId);
    }

    @Override
    public void getMonster(String monsterId, String userId, @NonNull IDataCallback<Monster> callback) {
        IDataCallback<Monster> monsterCallback = new IDataCallback<Monster>() {
            @Override
            public void onReceiveDataSuccess(Response<Monster> response) {
                if (mCachedUserMap.containsKey(userId))
                    mCachedUserMap.get(userId).setMonster(response.getBody());
                requests.get("getAllUserInformation")
                        .getIDataCallbacks()
                        .get(monsterId)
                        .onReceiveDataSuccess(response);
                removeRequest("getAllUserInformation", monsterId);
            }

            @Override
            public void onReceiveDataFailure(Message message) {

            }
        };
        if (mCachedUserMap.get(mCurrentUserId).getMonster().getBody() == null) {
            if (!requests.containsKey("getAllUserInformation") ||
                    !requests.get("getAllUserInformation").getIDataCallbacks().containsKey(monsterId)) {
                if (!requests.containsKey("getAllUserInformation"))
                    requests.put("getAllUserInformation", new ListCallbacks());
                mRemoteUserRepository.getMonster(monsterId, userId, monsterCallback);
            }
            requests.put("getAllUserInformation", requests.get("getAllUserInformation").add(monsterId, callback));
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

                StarStorage starStorage = new StarStorage();
                starStorage.setId(starId);
                starStorage.setStars(response.getBodyMap());
                mCachedUserMap.get(userId).setStars(starStorage);
                requests.get("getStar")
                        .getIDataCallbacks()
                        .get(starId)
                        .onReceiveDataSuccess(response);
                removeRequest("getStar", starId);
            }

            @Override
            public void onReceiveDataFailure(Message message) {
                callback.onReceiveDataFailure(message);
            }
        };
        if (mCachedUserMap.get(userId).getStarStorage().getStars().size() == 0) {
            if (!requests.containsKey("getStar") ||
                    !requests.get("getStar").getIDataCallbacks().containsKey(starId)) {
                if (!requests.containsKey("getStar"))
                    requests.put("getStar", new ListCallbacks());
                mRemoteUserRepository.getStar(starId, userId, starsCallback);
            }
            requests.put("getStar", requests.get("getStar").add(starId, callback));

        } else {
            callback.onReceiveDataSuccess(new Response<Star>()
                    .setBody(mCachedUserMap.get(userId).getStarStorage().getStars()));
        }
    }

    @Override
    public void getStars(@NonNull IDataCallback<Star> callback) {
        IDataCallback<Star> starsCallback = new IDataCallback<Star>() {
            @Override
            public void onReceiveDataSuccess(Response<Star> response) {
                callback.onReceiveDataSuccess(response);
                removeRequest("getStars", "getStars");
            }

            @Override
            public void onReceiveDataFailure(Message message) {
                callback.onReceiveDataFailure(message);
            }
        };
        if (!requests.containsKey("getStars")) {
            if (!requests.containsKey("getStars"))
                requests.put("getStars", new ListCallbacks());
            mRemoteUserRepository.getStars(starsCallback);
        }
        requests.put("getStars", requests.get("getStars").add("getStars", callback));
    }

    public User getCachedUser() {
        for (Map.Entry<String, User> entry : mCachedUserMap.entrySet()) {
            return entry.getValue();
        }
        return null;
    }

    @Override
    public void getMonsters(String requestId, @NonNull IDataCallback<Monster> callback) {
        IDataCallback<Monster> monsterIDataCallback = new IDataCallback<Monster>() {
            @Override
            public void onReceiveDataSuccess(Response<Monster> response) {
                mCachedMonsterList = response.getBodyList();
                for (Map.Entry<String, IDataCallback> reqCall :
                        requests.get("getMonsters").getIDataCallbacks().entrySet()) {
                    reqCall.getValue().onReceiveDataSuccess(response);
                }
                removeRequest("getMonsters", response.getRequestId());
            }

            @Override
            public void onReceiveDataFailure(Message message) {

            }
        };
        if (mCachedMonsterList.size() == 0) {
            if (!requests.containsKey("getMonsters")) {
                requests.put("getMonsters", new ListCallbacks());
            }
            mRemoteUserRepository.getMonsters(requestId, monsterIDataCallback);
            requests.put("getMonsters", requests.get("getMonsters").add(requestId, callback));
        } else {
            callback.onReceiveDataSuccess(new Response<Monster>().setBody(mCachedMonsterList));
        }

    }

    @Override
    public void getUsers(@NonNull IDataCallback<User> callback) {
        IDataCallback<User> repCallback = new IDataCallback<User>() {
            @Override
            public void onReceiveDataSuccess(Response<User> response) {
                for (User user : response.getBodyList()) {
                    mCachedUserMap.put(user.getId(), user);
                }
                for (Map.Entry<String, IDataCallback> reqCall :
                        requests.get("getUsers").getIDataCallbacks().entrySet())
                    reqCall.getValue().onReceiveDataSuccess(response);
                removeRequest("getUsers", "getUsers");
            }

            @Override
            public void onReceiveDataFailure(Message message) {
                callback.onReceiveDataFailure(message);
            }
        };
        if (!requests.containsKey("getUsers")) {
            if (!requests.containsKey("getUsers"))
                requests.put("getUsers", new ListCallbacks());
            mRemoteUserRepository.getUsers(repCallback);
        }
        requests.put("getUsers", requests.get("getUsers").add("getUsers", callback));
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
                mCurrentClassId = response.getBody().getSchoolClass().getId();
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
    public void addMonster(Monster monster) {
        if (!mCachedMonsterList.contains(monster)) {
            mCachedMonsterList.add(monster);
            getCurrentUser().setMonster(monster);
            mRemoteUserRepository.addMonster(monster);
        }
    }

    @Override
    public void getClassList(@NonNull IDataCallback<SchoolClass> callback) {
        IDataCallback<SchoolClass> classIDataCallback = new IDataCallback<SchoolClass>() {
            @Override
            public void onReceiveDataSuccess(Response<SchoolClass> response) {
                mCachedClassList = response.getBodyList();
                callback.onReceiveDataSuccess(response);
            }

            @Override
            public void onReceiveDataFailure(Message message) {

            }
        };
        if (mCachedMonsterList.size() == 0) {
            mRemoteUserRepository.getClassList(classIDataCallback);
        } else {
            callback.onReceiveDataSuccess(new Response<SchoolClass>().setBody(mCachedClassList));
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

    @Override
    public void removeStar(Star star, String userId) {
        if (mCachedUserMap.get(userId).getStarStorage().getStars().containsKey(star)) {
            mCachedUserMap.get(userId).getStarStorage().getStars().remove(star.getId());
        }
        mRemoteUserRepository.removeStar(star, userId);
    }

    private void removeRequest(String requestId, String itemId) {
        if (requests.containsKey(requestId)) {
            if (requests.get(requestId).getIDataCallbacks().containsKey(itemId)) {
                requests.get(requestId).getIDataCallbacks().remove(itemId);
                if (requests.get(requestId).getIDataCallbacks().size() == 0) {
                    requests.remove(requestId);
                }
            }
        }

    }
}
