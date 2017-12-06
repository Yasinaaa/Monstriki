package ru.android.monstrici.monstrici.data.model;

import java.util.ArrayList;

import ru.android.monstrici.monstrici.domain.base.IDataCallback;

/**
 * Created by elisium
 *
 * @Date 06/12/2017
 * @Author Andrei Gusev
 */

public class ListCallbacks {
    private ArrayList<IDataCallback> mIDataCallbacks;

    public ListCallbacks() {
        mIDataCallbacks = new ArrayList<>();
    }

    public ListCallbacks add(IDataCallback callback) {
        mIDataCallbacks.add(callback);
        return this;
    }

    public ArrayList<IDataCallback> getIDataCallbacks() {
        return mIDataCallbacks;
    }
}

