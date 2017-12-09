package ru.android.monstrici.monstrici.data.model;

import java.util.HashMap;

import ru.android.monstrici.monstrici.domain.base.IDataCallback;

/**
 * Created by elisium
 *
 * @Date 06/12/2017
 * @Author Andrei Gusev
 */

public class ListCallbacks {
    private HashMap<String, IDataCallback> mIDataCallbacks;

    public ListCallbacks() {
        mIDataCallbacks = new HashMap<>();
    }

    public ListCallbacks add(String id, IDataCallback callback) {
        mIDataCallbacks.put(id, callback);
        return this;
    }

    public HashMap<String, IDataCallback> getIDataCallbacks() {
        return mIDataCallbacks;
    }
}

