package ru.android.monstrici.monstrici.domain.base;

import ru.android.monstrici.monstrici.data.model.Response;

/**
 * Created by elisiumGusev
 *
 * @Date 23/10/2017
 * @Author Andrei Gusev
 */

public interface IDataCallback<T> extends IDataFailure {
    void onReceiveDataSuccess(Response<T> response);
}
