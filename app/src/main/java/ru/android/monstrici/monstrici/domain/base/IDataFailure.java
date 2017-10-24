package ru.android.monstrici.monstrici.domain.base;

import ru.android.monstrici.monstrici.utils.ErrorMessage;

/**
 * Created by elisiumGusev
 *
 * @Date 23/10/2017
 * @Author Andrei Gusev
 */

interface IDataFailure {
    void onReceiveDataFailure(ErrorMessage message);
}
