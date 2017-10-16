package ru.android.monstrici.monstrici.data.repository.server;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by elisiumGusev
 *
 * @Date 14/10/2017
 * @Author Andrei Gusev
 */

public class JSONResponseParser {
    public static boolean isSuccessful(JSONObject response) {
        boolean successful = true;
        try {
            successful = response.getBoolean("success");
        } catch (JSONException e) {
            // no "success"-block means response is successful
        }
        return successful;
    }


    public static ResponseException getResponseException(JSONObject response) throws JSONException {
        JSONObject tmp = response.getJSONObject("exception");
        ResponseException exception = new ResponseException(tmp.getInt("code"), tmp.getString("message"));
        return exception;
    }

    public static long get(JSONObject response) throws JSONException {
        return 0;
    }

    public static String getStr(JSONObject response) throws JSONException {
        return "";
    }
}
