package ru.android.monstrici.monstrici.data.repository.localdb;

import android.content.Context;

import org.greenrobot.greendao.database.Database;

import ru.android.monstrici.monstrici.data.model.DaoMaster;
import ru.android.monstrici.monstrici.data.model.DaoSession;
import ru.android.monstrici.monstrici.data.model.User;
import ru.android.monstrici.monstrici.data.model.UserDao;

/**
 * Created by elisiumGusev
 *
 * @Date 14/10/2017
 * @Author Andrei Gusev
 */

public class DaoDbHelper {
    private static DaoDbHelper instance;
    private static DaoSession daoSession;
    private static Database db;

    public static void initializeContext(Context context) {
        instance = new DaoDbHelper();
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(context, "monstrici-db");
        db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public static DaoDbHelper getInstance() {
        if (instance == null) {
            throw new NullPointerException("Forgot to initialize DaoDbHelper");
        }
        return instance;
    }


    public User get(long id) {
        return daoSession.getUserDao().queryBuilder()
                .where(UserDao.Properties.Id.eq(id))
                .unique();
    }

    public void add(User model) {
        daoSession.getUserDao().insert(model);
    }

    public void update(User model) {
        UserDao modelExampleDao = daoSession.getUserDao();
        if (modelExampleDao.queryBuilder()
                .where(UserDao.Properties.Id.eq(model.getId())).count() == 1)
            modelExampleDao.update(model);
    }

    void onStop() {
        daoSession.clear();
        db.close();
        instance = null;
    }

}
