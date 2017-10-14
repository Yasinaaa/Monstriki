package ru.android.monstrici.monstrici.data.repository.server;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by elisiumGusev
 *
 * @Date 14/10/2017
 * @Author Andrei Gusev
 */

interface IAPICallback {
    void onSuccess(JSONObject json) throws JSONException;

    void onFailure(Throwable t);
}
