package ru.android.monstrici.monstrici.presentation.adapter;

import ru.android.monstrici.monstrici.data.model.Monster;
import ru.android.monstrici.monstrici.domain.base.IDataCallback;

/**
 * Created by elisium
 *
 * @Date 08/12/2017
 * @Author Andrei Gusev
 */

public interface MonsterCallback {
    void setCallback(IDataCallback<Monster> callback);
}
